package com.wfj.exception.dal.entity;

/**
 * 异常信息发送规则配置基础类
 * @ClassName: MesEarlyWarnInfo
 * @author ZJHao
 * @date 2015-8-17 下午4:13:21
 *
 */
public class MesEarlyWarnInfo {

	private Integer id;	//主键
	private String description;	//描述
	private String flag;		//是否有效
	private String status;		//状态，是否开启
	private String sendType;	//发送方式,1.短信,2.邮件,3.短信+邮件
	private Integer warnCountMin;	//警告允许达到的数量
	private Integer warnCountMax;	//警告允许达到的数量
	private Integer errorCountMin;	//异常允许达到的数量
	private Integer errorCountMax;	//异常允许达到的数量
	private Integer sysId;	//系统id
	private Integer busiId;	//业务id
	private Integer sysChildActiveThresholdMaxCount;	//系统下存活实例容忍区间的最大值
	private Integer sysChildActiveThresholdMinCount;	//系统下存活实例容忍区间的最小值
	private Integer sysReqApdexThresholdMax;	//Apdex容忍请求时长的最大阀值,默认是3*4秒
	private Integer sysReqApdexThresholdMin;	//Apdex容忍请求时长的最小阀值,默认是3秒
	
	/*预留字段*/
	private String reserved1;
	private String reserved2;
	private String reserved3;
	private String reserved4;
	
	
	public MesEarlyWarnInfo(boolean flag){
		if(flag){
			this.warnCountMin=10;
			this.warnCountMax=20;
			this.errorCountMin=5;
			this.errorCountMax=10;
			this.sysChildActiveThresholdMinCount=3;
			this.sysChildActiveThresholdMaxCount=6;
			this.sysReqApdexThresholdMin=3;
			this.sysReqApdexThresholdMax=12;
		}
	}
	
	public MesEarlyWarnInfo(){}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public Integer getWarnCountMin() {
		return warnCountMin;
	}
	public void setWarnCountMin(Integer warnCountMin) {
		this.warnCountMin = warnCountMin;
	}
	public Integer getErrorCountMax() {
		return errorCountMax;
	}
	public void setErrorCountMax(Integer errorCountMax) {
		this.errorCountMax = errorCountMax;
	}
	public Integer getSysId() {
		return sysId;
	}
	public void setSysId(Integer sysId) {
		this.sysId = sysId;
	}
	public Integer getBusiId() {
		return busiId;
	}
	public void setBusiId(Integer busiId) {
		this.busiId = busiId;
	}
	public String getReserved1() {
		return reserved1;
	}
	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}
	public String getReserved2() {
		return reserved2;
	}
	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}
	public String getReserved3() {
		return reserved3;
	}
	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}
	public String getReserved4() {
		return reserved4;
	}
	public void setReserved4(String reserved4) {
		this.reserved4 = reserved4;
	}
	public Integer getErrorCountMin() {
		return errorCountMin;
	}
	public void setErrorCountMin(Integer errorCountMin) {
		this.errorCountMin = errorCountMin;
	}
	
	public Integer getSysChildActiveThresholdMaxCount() {
		return sysChildActiveThresholdMaxCount;
	}
	public void setSysChildActiveThresholdMaxCount(Integer sysChildActiveThresholdMaxCount) {
		this.sysChildActiveThresholdMaxCount = sysChildActiveThresholdMaxCount;
	}
	public Integer getSysChildActiveThresholdMinCount() {
		return sysChildActiveThresholdMinCount;
	}
	public void setSysChildActiveThresholdMinCount(Integer sysChildActiveThresholdMinCount) {
		this.sysChildActiveThresholdMinCount = sysChildActiveThresholdMinCount;
	}
	public Integer getSysReqApdexThresholdMax() {
		return sysReqApdexThresholdMax;
	}
	public void setSysReqApdexThresholdMax(Integer sysReqApdexThresholdMax) {
		this.sysReqApdexThresholdMax = sysReqApdexThresholdMax;
	}
	public Integer getSysReqApdexThresholdMin() {
		return sysReqApdexThresholdMin;
	}
	public void setSysReqApdexThresholdMin(Integer sysReqApdexThresholdMin) {
		this.sysReqApdexThresholdMin = sysReqApdexThresholdMin;
	}
	public Integer getWarnCountMax() {
		return warnCountMax;
	}
	public void setWarnCountMax(Integer warnCountMax) {
		this.warnCountMax = warnCountMax;
	}
	@Override
	public String toString() {
		return "MesEarlyWarnInfo [id=" + id + ", description=" + description + ", flag=" + flag + ", status=" + status + ", sendType=" + sendType + ", warnCountMin=" + warnCountMin
				+ ", warnCountMax=" + warnCountMax + ", errorCountMin=" + errorCountMin + ", errorCountMax=" + errorCountMax + ", sysId=" + sysId + ", busiId=" + busiId
				+ ", sysChildActiveThresholdMaxCount=" + sysChildActiveThresholdMaxCount + ", sysChildActiveThresholdMinCount=" + sysChildActiveThresholdMinCount + ", sysReqApdexThresholdMax="
				+ sysReqApdexThresholdMax + ", sysReqApdexThresholdMin=" + sysReqApdexThresholdMin + ", reserved1=" + reserved1 + ", reserved2=" + reserved2 + ", reserved3=" + reserved3
				+ ", reserved4=" + reserved4 + "]";
	}

	
	
	
	
	
	
	
	
}
