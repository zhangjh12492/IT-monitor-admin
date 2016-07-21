package com.wfj.exception.dal.cond;

public class EarlyWarnSysInfoCond {

	private Integer id;
	private Integer sysId;
	private Integer ewId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSysId() {
		return sysId;
	}
	public void setSysId(Integer sysId) {
		this.sysId = sysId;
	}
	public Integer getEwId() {
		return ewId;
	}
	public void setEwId(Integer ewId) {
		this.ewId = ewId;
	}
	@Override
	public String toString() {
		return "EarlyWarnSysInfoCond [id=" + id + ", sysId=" + sysId + ", ewId=" + ewId + "]";
	}
	
	
}
