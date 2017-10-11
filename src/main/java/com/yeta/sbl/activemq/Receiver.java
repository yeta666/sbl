package com.yeta.sbl.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017-10-9.
 */
@Component
public class Receiver {

    @JmsListener(destination = "activemq")
    public void receiveMessage(String message){
        System.out.println("接收到： " + message);
    }
}
