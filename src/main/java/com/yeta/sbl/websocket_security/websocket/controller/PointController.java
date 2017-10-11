package com.yeta.sbl.websocket_security.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Created by Administrator on 2017-9-29.
 */
@RestController
public class PointController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;    //通过该对象向浏览器发送消息

    /**
     * 点对点页面路径
     * @return
     */
    @GetMapping(value = "/point_page")
    public ModelAndView point_page(){
        return new ModelAndView("websocket_security/websocket/point.html");
    }


    @MessageMapping(value = "/point/send")
    public void point_chat(Principal principal,
                           @RequestParam(value = "message") String message){
        String username = principal.getName();
        if(username.equals("yeta")){
            messagingTemplate.convertAndSendToUser("ray", "/queue/chat", username + "-say: " + message);
        }else if(username.equals("ray")){
            messagingTemplate.convertAndSendToUser("yeta", "/queue/chat",username + "-say: " + message);
        }

    }
}
