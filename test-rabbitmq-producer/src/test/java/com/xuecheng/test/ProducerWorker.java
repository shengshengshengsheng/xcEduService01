package com.xuecheng.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * description:
 *
 * @author xuqiangsheng
 * @date 2020/11/18 15:02
 */
public class ProducerWorker {
    private static final String QUEUE = "HELLO_WORLD";

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
            channel.queueDeclare(QUEUE,true,false,false,null);
            for (int i = 0; i < 50; i++) {
                String message = "hello 你好呀 " + i;
                channel.basicPublish("",QUEUE,null,message.getBytes());
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
