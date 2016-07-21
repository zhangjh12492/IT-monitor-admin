package com.wfj.mq.entity;

/**
 * Created with IntelliJ IDEA.
 * User: MaYong
 * Date: 2015-04-17
 * Time: 17:43:45
 * To change this template use File | Settings | File Templates.
 */

public class InboundQueueRef {
    private String routeKey;  //
    private String memo;  //
    private Integer inboundConfSid;  //
    private Integer queueConfSid;  //
    private Integer exchangeConfSid;
    private Integer sid;  //
    private Integer status;

    
    public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getExchangeConfSid() {
		return exchangeConfSid;
	}

	public void setExchangeConfSid(Integer exchangeConfSid) {
		this.exchangeConfSid = exchangeConfSid;
	}

	public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }


    public Integer getInboundConfSid() {
        return inboundConfSid;
    }

    public void setInboundConfSid(Integer inboundConfSid) {
        this.inboundConfSid = inboundConfSid;
    }

    public Integer getQueueConfSid() {
        return queueConfSid;
    }

    public void setQueueConfSid(Integer queueConfSid) {
        this.queueConfSid = queueConfSid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
     

}