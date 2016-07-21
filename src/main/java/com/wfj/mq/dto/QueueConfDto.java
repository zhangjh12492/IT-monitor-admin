package com.wfj.mq.dto;

import com.wfj.mq.entity.QueueConf;

/**
 * Created by MaYong on 2015/7/15.
 */
public class QueueConfDto extends QueueConf {

    private Integer sid;
    private String queueTypeHZ;  //队列类型

    private String groupName;  //组名

    private String outboundName;  //接出服务名称

    private String outboundUrl;  //接出服务url

    private Integer maxNum;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getOutboundUrl() {
        return outboundUrl;
    }

    public void setOutboundUrl(String outboundUrl) {
        this.outboundUrl = outboundUrl;
    }

    public String getOutboundName() {
        return outboundName;
    }

    public void setOutboundName(String outboundName) {
        this.outboundName = outboundName;
    }

    public String getQueueTypeHZ() {
        return queueTypeHZ;
    }

    public void setQueueTypeHZ(String queueTypeHZ) {
        this.queueTypeHZ = queueTypeHZ;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }
}
