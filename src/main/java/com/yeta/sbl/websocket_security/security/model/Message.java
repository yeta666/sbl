package com.yeta.sbl.websocket_security.security.model;

/**
 * Created by Administrator on 2017-9-27.
 */
public class Message {
    //
    private boolean success;
    //
    private String message;
    //
    private String url;

    public Message() {
    }

    public Message(boolean success, String message, String url) {
        this.success = success;
        this.message = message;
        this.url = url;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
