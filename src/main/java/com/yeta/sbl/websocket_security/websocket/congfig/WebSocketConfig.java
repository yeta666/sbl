package com.yeta.sbl.websocket_security.websocket.congfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 配置WebSocket
 * Created by Administrator on 2017-9-13.
 */

@Configuration
@EnableWebSocketMessageBroker   //开启STOMP协议来传输基于代理（message broker）的消息，这时控制器支持@MessageMapping
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * 注册STOMP协议的节点（endpoint），并映射指定的URL
     * @param stompEndpointRegistry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //指定使用SocketJS协议
        stompEndpointRegistry.addEndpoint("/endpoint/broadcast").withSockJS();       //广播式
        stompEndpointRegistry.addEndpoint("/endpoint/point").withSockJS();        //点对点式
    }

    /**
     * 配置消息代理
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
        //广播式应配置一个/topic消息代理
        //点对点式应配置一个/queue消息代理
        registry.enableSimpleBroker("/topic", "/queue");
    }
}

