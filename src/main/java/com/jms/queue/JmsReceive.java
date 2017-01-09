package com.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Copyright © 2016年 author. All rights reserved.
 *
 * @Author 临江仙 hxqiuxm@163.com
 * @Date 2017/1/5 0005 18:37
 */
public class JmsReceive {

    public static void main(String[] args) throws Exception {

        ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://192.168.10.55:61616");
        Connection connection = cf.createConnection();
        connection.start();
        //开启事务，

        final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("my-queue");
        MessageConsumer consumer = session.createConsumer(destination);
        int i=0;
        while(i<3) {
            i++;
            TextMessage message = (TextMessage) consumer.receive();
            session.commit();
            System.out.println("收到消息：" + message.getText());
        }
        session.close();
        connection.close();
    }

}
