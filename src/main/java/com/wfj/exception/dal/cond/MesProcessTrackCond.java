package com.wfj.exception.dal.cond;

public class MesProcessTrackCond extends BaseCond{

	private Integer id;
	private Integer userId;
	private String errId;
	private String processStatus;
	private String createdTime;
	private String description;
	private String sysCode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getErrId() {
		return errId;
	}
	public void setErrId(String errId) {
		this.errId = errId;
	}
	public String getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MesProcessTrackCond{" +
				"id=" + id +
				", userId=" + userId +
				", errId='" + errId + '\'' +
				", processStatus='" + processStatus + '\'' +
				", createdTime='" + createdTime + '\'' +
				", description='" + description + '\'' +
				", sysCode='" + sysCode + '\'' +
				'}';
	}
}
