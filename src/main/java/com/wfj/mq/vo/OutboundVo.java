package com.wfj.mq.vo;

public class OutboundVo {
	private Integer  sid;
	private String   outboundName;
	private String   outboundDesc;
	private String   wsType;
	private String   outboundUrl;
	private String   status;
	private int      timeout;
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
	public String getWsType() {
		return wsType;
	}
	public void setWsType(String wsType) {
		this.wsType = wsType;
	}
	public String getOutboundUrl() {
		return outboundUrl;
	}
	public void setOutboundUrl(String outboundUrl) {
		this.outboundUrl = outboundUrl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	
}
