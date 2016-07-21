package com.wfj.exception.common;

public enum MesEarlyWarnStatusEnum {

	START("0"),	//开始
	STOP("1");	//停止
	
	private String code;
	private MesEarlyWarnStatusEnum(String code){
		this.code=code;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
