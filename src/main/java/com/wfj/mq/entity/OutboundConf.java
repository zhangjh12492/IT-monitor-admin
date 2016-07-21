package com.wfj.mq.entity;

public class OutboundConf {

	private Integer  sid;
	private String   outboundName;
	private String   outboundDesc;
	private Integer   wsType;
	private String   outboundUrl;
	private Integer   status;
	private Integer      timeout;
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getOutboundName() {
		return outboundName;
	}
	public void setOutboundName(String outboundName) {
		this.outboundName = outboundName;
	}
	public String getOutboundDesc() {
		return outboundDesc;
	}
	public void setOutboundDesc(String outboundDesc) {
		this.outboundDesc = outboundDesc;
	}
	public Integer getWsType() {
		return wsType;
	}
	public void setWsType(Integer wsType) {
		this.wsType = wsType;
	}
	public String getOutboundUrl() {
		return outboundUrl;
	}
	public void setOutboundUrl(String outboundUrl) {
		this.outboundUrl = outboundUrl;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	
	
}
