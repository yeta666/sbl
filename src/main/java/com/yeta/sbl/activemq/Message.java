package com.yeta.sbl.activemq;

import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Session;

/**
 * 定义JMS发送的消息
 * Created by Administrator on 2017-10-9.
 */
public class Message implements MessageCreator {

    @Override
    public javax.jms.Message createMessage(Session session) throws JMSException {
        return session.createTextMessage("测试消息");
    }
}
