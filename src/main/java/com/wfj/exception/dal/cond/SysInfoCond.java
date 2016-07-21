package com.wfj.exception.dal.cond;

public class SysInfoCond extends BaseCond{

	private Integer id;
	private String sysCode;
	private String sysDesc;
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
	public String getSysDesc() {
		return sysDesc;
	}
	public void setSysDesc(String sysDesc) {
		this.sysDesc = sysDesc;
	}
	@Override
	public String toString() {
		return "SysInfo [id=" + id + ", sysCode=" + sysCode + ", sysDesc=" + sysDesc + "]";
	}
	
}
