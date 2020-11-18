package com.xuecheng.test;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class ProducerHeader {
    //队列名称
    private static final String QUEUE_INFROM_EMAIL = "QUEUE_INFROM_EMAIL";
    private static final String QUEUE_INFROM_SMS = "QUEUE_INFROM_SMS";
    private static final String EXCHANGE_HEADER_INFORM = "EXCHANGE_HEADER_INFORM";

    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
            //创建一个与MQ的连接
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("192.168.197.128");
            factory.setPort(5672);
            factory.setUsername("leyou");
            factory.setPassword("leyou");
            factory.setVirtualHost("/leyou");//rabbitmq默认虚拟机名称为“/”，虚拟机相当于一个独立的mq服务器
            //创建一个连接
            connection = factory.newConnection();
            //创建与交换机的通道，每个通道代表一个会话
            channel = connection.createChannel();
            //声明交换机 String exchange, BuiltinExchangeType type
            /**
             * 参数明细
             * 1、交换机名称
             * 2、交换机类型，fanout、topic、direct、headers
             */
            channel.exchangeDeclare(EXCHANGE_HEADER_INFORM, BuiltinExchangeType.HEADERS);
            //声明队列
            Map<String, Object> headers_email = new Hashtable<String, Object>();
            headers_email.put("inform_type", "email");
            Map<String, Object> headers_sms = new Hashtable<String, Object>();
            headers_sms.put("inform_type", "sms");
            channel.queueBind(QUEUE_INFROM_EMAIL,EXCHANGE_HEADER_INFORM,"",headers_email);
            channel.queueBind(QUEUE_INFROM_SMS,EXCHANGE_HEADER_INFORM,"",headers_sms);
            //发送邮件消息
            for (int i = 0; i < 10; i++) {
                String message = "email inform to user"+i;
                Map<String,Object> headers = new Hashtable<String, Object>();
                headers.put("inform_type", "email");//匹配email通知消费者绑定的header
                //headers.put("inform_type", "sms");//匹配sms通知消费者绑定的header
                AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties.Builder();
                properties.headers(headers);
                //Email通知
                channel.basicPublish(EXCHANGE_HEADER_INFORM, "", properties.build(), message.getBytes());
                System.out.println("Send Message is:'" + message + "'");
            }
            //发送短信消息
            for (int i = 0; i < 10; i++) {
                String message = "sms inform to user"+i;
                Map<String,Object> headers = new Hashtable<>();
                //headers.put("inform_type", "email");//匹配email通知消费者绑定的header
                headers.put("inform_type", "sms");//匹配sms通知消费者绑定的header
                AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties.Builder();
                properties.headers(headers);
                //Email通知
                channel.basicPublish(EXCHANGE_HEADER_INFORM, "", properties.build(), message.getBytes());
                System.out.println("Send Message is:'" + message + "'");
            }
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException | TimeoutException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}