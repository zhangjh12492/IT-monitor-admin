package com.wfj.exception.dal.cond;

public class MesAllProcessReqCond {

	private String code;	//系统、业务、异常编码
	private String dateType;	//日期类型，详见DateTypeEnum
	private String codeType;	//编码类型CodeTypeEnum
	private String startTime;	//开始时间
	private String endTime;		//结束时间
	private String currentTime;	//当前时间
	private String errLevel;	//异常等级，详见ErrLevelEnum
	private String processStatus;	//处理状态,详见ProcessStatusEnum
	
	
	public String getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(String currentTime) {
		this.currentTime=currentTime;
	}
	public String getErrLevel() {
		return errLevel;
	}
	public void setErrLevel(String errLevel) {
		this.errLevel = errLevel;
	}
	@Override
	public String toString() {
		return "MesAllProcessReqCond [code=" + code + ", dateType=" + dateType + ", codeType=" + codeType + ", startTime=" + startTime + ", endTime=" + endTime + ", currentTime=" + currentTime
				+ ", errLevel=" + errLevel + ", processStatus=" + processStatus + "]";
	}
	
	
}
