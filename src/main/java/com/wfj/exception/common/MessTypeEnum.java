package com.wfj.exception.common;

public enum MessTypeEnum {

	ERROR("error"),
	WARN("warn");
	
	private MessTypeEnum(String code){
		this.code=code;
	}
	
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
