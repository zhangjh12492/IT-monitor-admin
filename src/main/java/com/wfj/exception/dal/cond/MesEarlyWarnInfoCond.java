package com.wfj.exception.dal.cond;

/**
 * 异常信息发送规则配置基础类
 * @ClassName: MesEarlyWarnInfo
 * @author ZJHao
 * @date 2015-8-17 下午4:13:21
 *
 */
public class MesEarlyWarnInfoCond extends BaseCond{

	private String id;	//主键
	private String description;	//描述
	private String url;	//路径
	private String flag;		//是否有效
	private String status;		//状态，是否开启
	private Integer count;		//预警数量
	private String sendType;	//发送方式,1.短信,2.邮件,3.短信+邮件
	private String errLevel;	
	
	
	
	
	
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public String getErrLevel() {
		return errLevel;
	}
	public void setErrLevel(String errLevel) {
		this.errLevel = errLevel;
	}
	
	
	
}
