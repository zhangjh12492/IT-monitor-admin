package com.wfj.exception.dal.entity;

public class MesAllProcessReq {

	private Integer id;
	private String code;
	private Integer warnCount;
	private Integer errorCount;
	private String createdTime;
	private String sysCode;
	private String processStatus;
	
	public String getSysCode() {
		return sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getWarnCount() {
		return warnCount;
	}
	public void setWarnCount(Integer warnCount) {
		this.warnCount = warnCount;
	}
	public Integer getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getProcessStatus() {
		return processStatus;
	}
	public MesAllProcessReq setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
		return this;
	}

	@Override
	public String toString() {
		return "MesAllProcessReq{" +
				"id=" + id +
				", code='" + code + '\'' +
				", warnCount=" + warnCount +
				", errorCount=" + errorCount +
				", createdTime='" + createdTime + '\'' +
				", sysCode='" + sysCode + '\'' +
				", processStatus='" + processStatus + '\'' +
				'}';
	}
}
