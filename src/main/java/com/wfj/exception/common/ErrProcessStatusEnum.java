package com.wfj.exception.common;

public enum ErrProcessStatusEnum {

	UNDISPOSED("0"),		//the status is undisposed.
	PROCESSING("1"),	//the status is processed.
	PROCESSEND("2");	//2.process end
	
	private String code;
	
	private ErrProcessStatusEnum(String code){
		this.code=code;
	}
	public String getCode(){
		return code;
	}
}
