package com.wfj.exception.dal.entity;

public class SysInfo {

	private Integer id;		//自增列
	private String sysCode;	//系统编码
	private String sysName;	//系统名称
	private String sysDesc;	//系统描述
	
	
	
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
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	@Override
	public String toString() {
		return "SysInfo [id=" + id + ", sysCode=" + sysCode + ", sysName=" + sysName + ", sysDesc=" + sysDesc + "]";
	}
	
}
