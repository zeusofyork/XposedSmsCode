package com.tianma.xsmscode.data.http.entity;

/**
 * 请求实体
 * @author niushuai233
 * @date 2022年8月18日15:56:34
 */
public class ShuaiPushMessageReq {

    private String platform;
    private String messageType;
    private String message;
    private String toUser;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }
}
