package com.wfj.mq.vo;

import java.util.Date;

public class MessageVo {
	private Long sid;
	private String  messageId;
	private Integer 	messageType;
	private Date 	createTime;
	private Integer txInfoSid;
	private String    content;
	
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public Integer getMessageType() {
		return messageType;
	}
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getTxInfoSid() {
		return txInfoSid;
	}
	public void setTxInfoSid(Integer txInfoSid) {
		this.txInfoSid = txInfoSid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
