package com.wfj.exception.dal.vo;

import java.util.Arrays;

public class MesBusiProcessReqVo {

	private Integer id;
	private String busiCode;
	private Integer busiWarnCount;
	private Integer busiErrCount;
	private String sysCode;
	private Integer sysId;
	private String createdTime;
	private String errMessId;	
	private String warnMessId;
	private String errId;
	private String [] errIds;
	private String [] errMessIds;
	private String [] warnMessIds;
	private String errLevel;
	private String busiDesc;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getBusiWarnCount() {
		return busiWarnCount;
	}
	public void setBusiWarnCount(Integer busiWarnCount) {
		this.busiWarnCount = busiWarnCount;
	}
	public Integer getBusiErrCount() {
		return busiErrCount;
	}
	public void setBusiErrCount(Integer busiErrCount) {
		this.busiErrCount = busiErrCount;
	}
	public Integer getSysId() {
		return sysId;
	}
	public void setSysId(Integer sysId) {
		this.sysId = sysId;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getErrMessId() {
		return errMessId;
	}
	public void setErrMessId(String errMessId) {
		this.errMessId = errMessId;
	}
	public String getWarnMessId() {
		return warnMessId;
	}
	public void setWarnMessId(String warnMessId) {
		this.warnMessId = warnMessId;
	}
	public String[] getErrMessIds() {
		return errMessIds;
	}
	public void setErrMessIds(String[] errMessIds) {
		this.errMessIds = errMessIds;
	}
	public String[] getWarnMessIds() {
		return warnMessIds;
	}
	public void setWarnMessIds(String[] warnMessIds) {
		this.warnMessIds = warnMessIds;
	}
	public String getErrId() {
		return errId;
	}
	public void setErrId(String errId) {
		this.errId = errId;
	}
	public String[] getErrIds() {
		return errIds;
	}
	public void setErrIds(String[] errIds) {
		this.errIds = errIds;
	}
	public String getErrLevel() {
		return errLevel;
	}
	public void setErrLevel(String errLevel) {
		this.errLevel = errLevel;
	}
	public String getBusiDesc() {
		return busiDesc;
	}
	public void setBusiDesc(String busiDesc) {
		this.busiDesc = busiDesc;
	}
	@Override
	public String toString() {
		return "MesBusiProcessReqVo [id=" + id + ", busiCode=" + busiCode + ", busiWarnCount=" + busiWarnCount + ", busiErrCount=" + busiErrCount + ", sysCode=" + sysCode + ", sysId=" + sysId
				+ ", createdTime=" + createdTime + ", errMessId=" + errMessId + ", warnMessId=" + warnMessId + ", errId=" + errId + ", errIds=" + Arrays.toString(errIds) + ", errMessIds="
				+ Arrays.toString(errMessIds) + ", warnMessIds=" + Arrays.toString(warnMessIds) + ", errLevel=" + errLevel + ", busiDesc=" + busiDesc + "]";
	}
	
	
	
	
}
