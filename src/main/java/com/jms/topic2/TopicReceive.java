package com.jms.topic2;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Copyright © 2016年 author. All rights reserved.
 *
 * @Author 临江仙 hxqiuxm@163.com
 * @Date 2017/1/9 0009 21:04
 * 持久化topic接收,先运行一次完成注册
 */
public class TopicReceive {

        public static void main(String[] args) throws Exception {
            ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://192.168.10.55:61616");
            Connection connection = cf.createConnection();
            //设置消费者的身份标示
            connection.setClientID("qxm001");

            //开启事务
            final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //设置订阅topic并创建订阅
            Topic destination = session.createTopic("my-topic");
            TopicSubscriber ts = session.createDurableSubscriber(destination,"T1");

            connection.start();

            Message message = ts.receive();
            while(message!=null) {
                TextMessage txtMsg = (TextMessage)message;
                session.commit();
                System.out.println("收到消息：" + txtMsg.getText());
                message = ts.receive(1000L);
            }

            session.close();
            connection.close();
        }
}
