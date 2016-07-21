package com.wfj.mq.dto;


/**
 * Created with IntelliJ IDEA.
 * User: MaYong
 * Date: 2015-04-19
 * Time: 12:32:45
 * To change this template use File | Settings | File Templates.
 */

public class MessageLogDto {
    private Long txInfoSid;  //
    private String messageId;  //
    private String content;  //
    private Integer messageType;  //
    private String createTime;  //
    private Long sid;  //

    public Long getTxInfoSid() {
        return txInfoSid;
    }

    public void setTxInfoSid(Long txInfoSid) {
        this.txInfoSid = txInfoSid;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

}