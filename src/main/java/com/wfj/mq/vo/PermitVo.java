package com.wfj.mq.vo;

public class PermitVo {

	private Integer sid;
	private Integer process; //权限标识  0所有操作 1只能查看
	private String  perDesc;
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getProcess() {
		return process;
	}
	public void setProcess(Integer process) {
		this.process = process;
	}
	public String getPerDesc() {
		return perDesc;
	}
	public void setPerDesc(String perDesc) {
		this.perDesc = perDesc;
	}
	
	
	
}
