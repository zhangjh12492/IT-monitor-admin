package com.wfj.exception.dal.vo;

public class MesErrProcessReqVo {

	private int id;
	private int sysId;
	private int busiId;
	private String sysCode;
	private String busiCode;
	private String errCode;
	private String allCode;
	private int errErrCount;
	private int errWarnCount;
	private String createdTime;
	private String errLevel;
	private String processStatus;
	
	public String getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSysId() {
		return sysId;
	}
	public void setSysId(int sysId) {
		this.sysId = sysId;
	}
	public int getBusiId() {
		return busiId;
	}
	public void setBusiId(int busiId) {
		this.busiId = busiId;
	}
	public String getSysCode() {
		return sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	public String getBusiCode() {
		return busiCode;
	}
	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public int getErrErrCount() {
		return errErrCount;
	}
	public void setErrErrCount(int errErrCount) {
		this.errErrCount = errErrCount;
	}
	public int getErrWarnCount() {
		return errWarnCount;
	}
	public void setErrWarnCount(int errWarnCount) {
		this.errWarnCount = errWarnCount;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getErrLevel() {
		return errLevel;
	}
	public void setErrLevel(String errLevel) {
		this.errLevel = errLevel;
	}
	
	public String getAllCode() {
		return allCode;
	}
	public void setAllCode(String allCode) {
		this.allCode = allCode;
	}
	@Override
	public String toString() {
		return "MesErrProcessReq [id=" + id + ", sysId=" + sysId + ", busiId=" + busiId + ", sysCode=" + sysCode + ", busiCode=" + busiCode + ", errCode=" + errCode + ", allCode=" + allCode
				+ ", errErrCount=" + errErrCount + ", errWarnCount=" + errWarnCount + ", createdTime=" + createdTime + ", errLevel=" + errLevel + ", processStatus=" + processStatus + "]";
	}
	
	
	
}
