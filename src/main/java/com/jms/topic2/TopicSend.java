package com.jms.topic2;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Copyright © 2016年 author. All rights reserved.
 *
 * @Author 临江仙 hxqiuxm@163.com
 * @Date 2017/1/9 0009 21:04
 * 持久化topic发送,也是要先运行消费者先注册（订阅）
 */
public class TopicSend {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.10.55:61616");
        Connection connection = connectionFactory.createConnection();

        //开启事务，消息确认方式为自动
        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        //创建队列
        Destination destination = session.createTopic("my-topic");
        //生产者把消息传送到队列中
        MessageProducer producer = session.createProducer(destination);

        //设置持久化投递方式后再启动
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        connection.start();

        for(int i=0; i<3; i++) {
            TextMessage message = session.createTextMessage("message persister11--"+i);
            //通过消息生产者发出消息
            producer.send(message);
        }
        session.commit();
        session.close();
        connection.close();
    }
}
