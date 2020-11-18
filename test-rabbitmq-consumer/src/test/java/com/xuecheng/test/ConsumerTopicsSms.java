package com.xuecheng.test;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

/**
 * description:
 *
 * @author xuqiangsheng
 * @date 2020/11/18 15:11
 */
public class ConsumerTopicsSms {
    private static final String QUEUE_INFROM_SMS = "QUEUE_INFROM_SMS";
    private static final String EXCHANGE_TOPICS_INFORM = "EXCHANGE_TOPICS_INFORM";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setVirtualHost("/leyou");
        factory.setUsername("leyou");
        factory.setPassword("leyou");
        factory.setHost("192.168.197.128");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //声明交换机 String exchange, BuiltinExchangeType type
        /**
         * 参数明细
         * 1、交换机名称
         * 2、交换机类型，fanout、topic、direct、headers
         */
        channel.exchangeDeclare(EXCHANGE_TOPICS_INFORM, BuiltinExchangeType.DIRECT);
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
        channel.queueDeclare(QUEUE_INFROM_SMS,true,false,false,null);
        //交换机和队列绑定String queue, String exchange, String routingKey
        /**
         * 参数明细
         * 1、队列名称
         * 2、交换机名称
         * 3、路由key
         */
        channel.queueBind(QUEUE_INFROM_SMS, EXCHANGE_TOPICS_INFORM,QUEUE_INFROM_SMS);
        //定义消费方法
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) {
                System.out.println(new String(body, StandardCharsets.UTF_8));
            }
        };
        /**
         * 监听队列String queue, boolean autoAck,Consumer callback
         * 参数明细
         * 1、队列名称
         * 2、是否自动回复，设置为true为表示消息接收到自动向mq回复接收到了，mq接收到回复会删除消息，设置
         为false则需要手动回复
         * 3、消费消息的方法，消费者接收到消息后调用此方法
         */
        channel.basicConsume(QUEUE_INFROM_SMS, true, consumer);
    }
}
