package com.yeta.sbl.websocket_security.websocket.controller;

import com.yeta.sbl.websocket_security.websocket.model.Response;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017-9-13.
 */
@RestController
public class BroadcostController {

    /**
     * 广播页面路径
     * @return
     */
    @GetMapping(value = "/broadcast_page")
    public ModelAndView point_page(){
        return new ModelAndView("websocket_security/websocket/broadcast.html");
    }

    @MessageMapping(value = "/broadcast/send")     //浏览器向服务端发送请求地址
    @SendTo(value = "/topic/chat")       //服务端有消息时，会对订阅了@SendTo中的路径的浏览器发送消息
    public Response broadcast_response(@RequestParam(value = "name") String name) throws Exception{
        Thread.sleep(3000);
        return new Response("Welcome, " + name + " !");
    }


}
