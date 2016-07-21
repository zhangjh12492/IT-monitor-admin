package com.wfj.mq.dto;



public class InboundDto {

	    private String routeKey;  //
	    private String memo;  //
	    private Integer inboundConfSid;  //
	    private Integer queueConfSid;  //
	    private Integer exchangeConfSid;
	    private Integer sid;  //
	    private Integer status;
	    private String systemNo;  //
	    private String serviceNo;  //
	    private Integer groupSid;  //
	    private String inboundDesc;  //
	    private Integer exchangeType;  //
	    private Integer maxLength;  //
		public String getRouteKey() {
			return routeKey;
		}
		public void setRouteKey(String routeKey) {
			this.routeKey = routeKey;
		}
		public String getMemo() {
			return memo;
		}
		public void setMemo(String memo) {
			this.memo = memo;
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
		public Integer getExchangeConfSid() {
			return exchangeConfSid;
		}
		public void setExchangeConfSid(Integer exchangeConfSid) {
			this.exchangeConfSid = exchangeConfSid;
		}
		public Integer getSid() {
			return sid;
		}
		public void setSid(Integer sid) {
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
		public Integer getMaxLength() {
			return maxLength;
		}
		public void setMaxLength(Integer maxLength) {
			this.maxLength = maxLength;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
	  
}
