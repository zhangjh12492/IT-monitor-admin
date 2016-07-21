package com.wfj.mq.dto;

import com.wfj.mq.entity.InboundQueueRef;

/**
 * Created by MaYong on 2015/7/17.
 */
public class InboundQueueRefDto extends InboundQueueRef {
    private String systemNo;
    private String serviceNo;
    private String queueName;
    private String groupName;

    @Override
    public String getMemo() {
        return super.getMemo();
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

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
