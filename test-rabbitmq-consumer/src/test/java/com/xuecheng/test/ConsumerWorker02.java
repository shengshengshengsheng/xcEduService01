package com.xuecheng.test;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

/**
 * description:
 *
 * @author xuqiangsheng
 * @date 2020/11/18 15:11
 */
public class ConsumerWorker02 {
    private static final String QUEUE = "HELLO_WORLD";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setVirtualHost("/leyou");
        factory.setUsername("leyou");
        factory.setPassword("leyou");
        factory.setHost("192.168.197.128");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE,true,false,false,null);
        DefaultConsumer consumer = new DefaultConsumer(channel){
            /**
             * 消费者接收消息调用此方法
             * @param consumerTag 消费者的标签，在channel.basicConsume()去指定
             * @param envelope 消息包的内容，可从中获取消息id，消息routingkey，交换机，消息和重传标志
            (收到消息失败后是否需要重新发送)
             * @param properties
             * @param body 消息体
             * @return void
            */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) {
                String msg = new String(body, StandardCharsets.UTF_8);
                System.out.println(msg);
            }
        };
        /**
         * 监听队列String queue, boolean autoAck,Consumer callback
         * 参数明细
         * queue 1、队列名称
         * autoAck 2、是否自动回复，设置为true为表示消息接收到自动向mq回复接收到了，mq接收到回复会删除消息，设置
         为false则需要手动回复
         * callback 3、消费消息的方法，消费者接收到消息后调用此方法
         */
        channel.basicConsume(QUEUE,true,consumer);
    }
}
