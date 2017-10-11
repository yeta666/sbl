package com.yeta.sbl.websocket_security.websocket.model;

/**
 * 服务器向浏览器发送的消息
 * Created by Administrator on 2017-9-13.
 */
public class Response {

    private String response;

    public Response(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
