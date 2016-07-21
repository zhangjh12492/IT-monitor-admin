package com.wfj.exception.common;

/**
 * 异常级别
 * @ClassName: ErrorLevel
 * @author Administrator
 * @date 2015-6-9 下午3:04:11
 *
 */
public enum ErrLevelEnum {
	
	WARNING("1"),	//1.代表警告
	ERROR("2");		//2.代表报错

	private ErrLevelEnum(String code){
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
