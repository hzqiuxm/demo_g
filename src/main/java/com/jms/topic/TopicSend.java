package com.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Copyright © 2016年 author. All rights reserved.
 *
 * @Author 临江仙 hxqiuxm@163.com
 * @Date 2017/1/9 0009 20:51
 * 非持久的topic发送，消费者先启动才行
 */
public class TopicSend {

    public static void main(String[] args) throws Exception{

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.10.55:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //开启事务，消息确认方式为自动
        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        //创建队列
        Destination destination = session.createTopic("hzqiuxm-topic");
        //生产者把消息传送到队列中
        MessageProducer producer = session.createProducer(destination);
        for(int i=0; i<3; i++) {
            TextMessage message = session.createTextMessage("message no persister--"+i);
//            Thread.sleep(1000);
            //通过消息生产者发出消息
            producer.send(message);
        }

        session.commit();
        session.close();
        connection.close();
    }

}
