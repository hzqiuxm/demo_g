package com.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Copyright © 2016年 author. All rights reserved.
 *
 * @Author 临江仙 hxqiuxm@163.com
 * @Date 2017/1/9 0009 20:51
 * 非持久的topic接收，注意必须要接收方在线，否则不在线时发的消息上线后也无法收到
 */
public class TopicReceive {

    public static void main(String[] args) throws Exception {
        ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://192.168.10.55:61616");
        Connection connection = cf.createConnection();
        connection.start();
        //开启事务，

        final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic("hzqiuxm-topic");
        MessageConsumer consumer = session.createConsumer(destination);

        Message message = consumer.receive();

        while(message!=null) {
            TextMessage txtMsg = (TextMessage)message;
            System.out.println("收到消息：" + txtMsg.getText());
            message = consumer.receive(1000L);
        }
        session.commit();
        session.close();
        connection.close();
    }

}
