package com.wfj.mq.entity;

/**
 * Created with IntelliJ IDEA.
 * User: MaYong
 * Date: 2015-04-17
 * Time: 17:43:44
 * To change this template use File | Settings | File Templates.
 */

public class QueueConf {
    private Integer status;  //
    private String queueName;  //
    private Integer outboundConfSid;  //
    private String queueDesc;  //
    private Integer sid;  //
    private Integer queueType;  //队列类型 0-direct 1-topic
    private Integer maxNum;
    private Integer groupSid;
    private Integer queueListenType;


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

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public Integer getGroupSid() {
        return groupSid;
    }

    public void setGroupSid(Integer groupSid) {
        this.groupSid = groupSid;
    }

    public Integer getQueueListenType() {
        return queueListenType;
    }

    public void setQueueListenType(Integer queueListenType) {
        this.queueListenType = queueListenType;
    }
}