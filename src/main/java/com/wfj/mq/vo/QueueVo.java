package com.wfj.mq.vo;

public class QueueVo {

    private Integer status;  //1-正常 0-禁用
    private String queueName;  //
    private Integer outboundConfSid;  //
    private String queueDesc;  //
    private Integer sid;  //
    private Integer queueType;  //0-direct 2-topic
    private Integer groupSid;  //

    public Integer getGroupSid() {
        return groupSid;
    }

    public void setGroupSid(Integer groupSid) {
        this.groupSid = groupSid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public Integer getOutboundConfSid() {
        return outboundConfSid;
    }

    public void setOutboundConfSid(Integer outboundConfSid) {
        this.outboundConfSid = outboundConfSid;
    }

    public String getQueueDesc() {
        return queueDesc;
    }

    public void setQueueDesc(String queueDesc) {
        this.queueDesc = queueDesc;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getQueueType() {
        return queueType;
    }

    public void setQueueType(Integer queueType) {
        this.queueType = queueType;
    }
}
