package com.wfj.mq.vo;

import java.util.Date;

public class TxInfoVo {
    private Date inTime;  //
    private String messageId;  //
    private Integer status;  //
    private Date outTime;  //
    private Integer queueConfSid;  //
    private Long sid;  //
    private String systemNo;
    private String serviceNo;
    private Integer retryTimes;
    private String[] messageIds;
    private String orderBy;

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Integer getQueueConfSid() {
        return queueConfSid;
    }

    public void setQueueConfSid(Integer queueConfSid) {
        this.queueConfSid = queueConfSid;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getSystemNo() {
        return systemNo;
    }

    public void setSystemNo(String systemNo) {
        this.systemNo = systemNo;
    }

    public String getServiceNo() {
        return serviceNo;
    }

    public void setServiceNo(String serviceNo) {
        this.serviceNo = serviceNo;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    public String[] getMessageIds() {
        return messageIds;
    }

    public void setMessageIds(String[] messageIds) {
        this.messageIds = messageIds;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
