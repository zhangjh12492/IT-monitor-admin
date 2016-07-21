package com.wfj.mq.entity;

import java.util.Date;

public class Message {

	private Integer sid;
	private String  messageId;
	private int 	messageType;
	private Date 	createTime;
	private Integer txInfoSid;
	private String    content;
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public int getMessageType() {
		return messageType;
	}
	public void setMessageType(int messageType) {
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
