package com.wfj.mq.dto;

/**
 * Created by MaYong on 2015/9/11.
 */
public class InboundConfDto {

    private String systemNo;  //
    private String serviceNo;  //
    private String routeKey;  //
    private Integer groupSid;  //
    private String inboundDesc;  //
    private Integer exchangeType;  //
    private Integer sid;  //
    private Integer maxLength;  //
    private Integer exchangeConfSid; //

    private String groupName;
    private String exchangeType_hz;

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

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }

    public Integer getGroupSid() {
        return groupSid;
    }

    public void setGroupSid(Integer groupSid) {
        this.groupSid = groupSid;
    }

    public String getInboundDesc() {
        return inboundDesc;
    }

    public void setInboundDesc(String inboundDesc) {
        this.inboundDesc = inboundDesc;
    }

    public Integer getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(Integer exchangeType) {
        this.exchangeType = exchangeType;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public Integer getExchangeConfSid() {
        return exchangeConfSid;
    }

    public void setExchangeConfSid(Integer exchangeConfSid) {
        this.exchangeConfSid = exchangeConfSid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getExchangeType_hz() {
        return exchangeType_hz;
    }

    public void setExchangeType_hz(String exchangeType_hz) {
        this.exchangeType_hz = exchangeType_hz;
    }
}
