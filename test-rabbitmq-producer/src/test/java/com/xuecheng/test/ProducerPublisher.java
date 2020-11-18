package com.xuecheng.test;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * description:
 *
 * @author xuqiangsheng
 * @date 2020/11/18 15:02
 */
public class ProducerPublisher {
    private static final String QUEUE_INFROM_EMAIL = "QUEUE_INFROM_EMAIL";
    private static final String QUEUE_INFROM_SMS = "QUEUE_INFROM_SMS";
    private static final String EXCHANGE_FANOUT_INFORM = "EXCHANGE_FANOUT_INFORM";

    public static void main(String[] args) throws Exception {
        Connection connection = null ;
        Channel channel = null;
        try{
            ConnectionFactory factory = new ConnectionFactory();
            factory.setVirtualHost("/leyou");
            factory.setUsername("leyou");
            factory.setPassword("leyou");
            factory.setHost("192.168.197.128");
            factory.setPort(5672);
            connection = factory.newConnection();
            channel = connection.createChannel();
            //声明交换机 String exchange, BuiltinExchangeType type
            /**
            * 参数明细
            * 1、交换机名称
            * 2、交换机类型，fanout、topic、direct、headers
            */
            channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM, BuiltinExchangeType.FANOUT);
            //声明队列
            // (String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String,Object> arguments)
            /**
             * 参数明细：
             * 1、队列名称
             * 2、是否持久化
             * 3、是否独占此队列
             * 4、队列不用是否自动删除
             * 5、参数
             */
            channel.queueDeclare(QUEUE_INFROM_EMAIL,true,false,false,null);
            channel.queueDeclare(QUEUE_INFROM_SMS,true,false,false,null);
            //交换机和队列绑定String queue, String exchange, String routingKey
            /**
             * 参数明细
             * 1、队列名称
             * 2、交换机名称
             * 3、路由key
             */
            channel.queueBind(QUEUE_INFROM_EMAIL,EXCHANGE_FANOUT_INFORM,"");
            channel.queueBind(QUEUE_INFROM_SMS,EXCHANGE_FANOUT_INFORM,"");
            for (int i = 0; i < 50; i++) {
                String message = "hello 你好呀 " + i;
                channel.basicPublish(EXCHANGE_FANOUT_INFORM,"",null,message.getBytes());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(channel != null){
                channel.close();
            }
            if(connection != null){
                connection.close();
            }
        }
    }
}
