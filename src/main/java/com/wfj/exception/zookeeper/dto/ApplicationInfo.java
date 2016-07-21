/**
 * @Probject Name: netty-wfj-base
 * @Path: com.wfj.netty.monitor.dtoApplicationInfo.java
 * @Create By Jack
 * @Create In 2015年8月26日 下午7:03:40
 * TODO
 */
package com.wfj.exception.zookeeper.dto;

import java.io.Serializable;

/**
 * @Class Name ApplicationInfo
 * @Author
 * @Create In 2015年8月26日
 */
public class ApplicationInfo implements Serializable {

	/** 属性名称 */
	private static final long serialVersionUID = 1L;

	private String name; // 系统下每个节点的名称

	private String version; // 版本

	private String desc; // 描述

	private String status; // 状态

	private SystemInfo sysInfo;

	private String updateTime; // 数据修改时间

	private Long sumInboundReqCounts; // 请求总次数

	private Long sumOutboundReqCounts; // 响应的总次数

	private Long sumDealReqCounts; // 当段时间内请求总次数

	private Long sumDealReqTime; // (毫秒) 请求总时间

	private Long peerDealReqTime; // (毫秒) 当前周期内响应总时间

	private Long sumErrDealReqCounts; // 请求失败总次数

	private Long sumErrDealReqTime; // (毫秒) 请求失败总时间

	private String serverName;

	private String serverVersion;

	private Integer nodeCount;	//当前节点的存活数,虚拟字段

	private String nodeName;	//此节点在zookeepr中的节点名称


	public Integer getNodeCount() {
		return nodeCount;
	}

	public void setNodeCount(Integer nodeCount) {
		this.nodeCount = nodeCount;
	}

	/**
	 * @Return the Long sumErrDealReqCounts
	 */
	public Long getSumErrDealReqCounts() {
		return sumErrDealReqCounts;
	}

	/**
	 * @Param Long sumErrDealReqCounts to set
	 */
	public void setSumErrDealReqCounts(Long sumErrDealReqCounts) {
		this.sumErrDealReqCounts = sumErrDealReqCounts;
	}

	/**
	 * @Return the Long sumErrDealReqTime
	 */
	public Long getSumErrDealReqTime() {
		return sumErrDealReqTime;
	}

	/**
	 * @Param Long sumErrDealReqTime to set
	 */
	public void setSumErrDealReqTime(Long sumErrDealReqTime) {
		this.sumErrDealReqTime = sumErrDealReqTime;
	}

	/**
	 * @Return the String updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @Param String updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @Return the Long sumInboundReqCounts
	 */
	public Long getSumInboundReqCounts() {
		return sumInboundReqCounts;
	}

	/**
	 * @Param Long sumInboundReqCounts to set
	 */
	public void setSumInboundReqCounts(Long sumInboundReqCounts) {
		this.sumInboundReqCounts = sumInboundReqCounts;
	}

	/**
	 * @Return the Long sumOutboundReqCounts
	 */
	public Long getSumOutboundReqCounts() {
		return sumOutboundReqCounts;
	}

	/**
	 * @Param Long sumOutboundReqCounts to set
	 */
	public void setSumOutboundReqCounts(Long sumOutboundReqCounts) {
		this.sumOutboundReqCounts = sumOutboundReqCounts;
	}

	/**
	 * @Return the Long sumDealReqCounts
	 */
	public Long getSumDealReqCounts() {
		return sumDealReqCounts;
	}

	/**
	 * @Param Long sumDealReqCounts to set
	 */
	public void setSumDealReqCounts(Long sumDealReqCounts) {
		this.sumDealReqCounts = sumDealReqCounts;
	}

	/**
	 * @Return the Long sumDealReqTime
	 */
	public Long getSumDealReqTime() {
		return sumDealReqTime;
	}

	/**
	 * @Param Long sumDealReqTime to set
	 */
	public void setSumDealReqTime(Long sumDealReqTime) {
		this.sumDealReqTime = sumDealReqTime;
	}

	/**
	 * @Return the Long peerDealReqTime
	 */
	public Long getPeerDealReqTime() {
		return peerDealReqTime;
	}

	/**
	 * @Param Long peerDealReqTime to set
	 */
	public void setPeerDealReqTime(Long peerDealReqTime) {
		this.peerDealReqTime = peerDealReqTime;
	}

	/**
	 * @Return the String name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @Param String name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @Return the String version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @Param String version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @Return the String desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @Param String desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @Return the String status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @Param String status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @Return the SystemInfo sysInfo
	 */
	public SystemInfo getSysInfo() {
		return sysInfo;
	}

	/**
	 * @Param SystemInfo sysInfo to set
	 */
	public void setSysInfo(SystemInfo sysInfo) {
		this.sysInfo = sysInfo;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerVersion() {
		return serverVersion;
	}

	public void setServerVersion(String serverVersion) {
		this.serverVersion = serverVersion;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName){
		this.nodeName=nodeName;
	}

	@Override
	public String toString() {
		return "ApplicationInfo{" +
				"name='" + name + '\'' +
				", version='" + version + '\'' +
				", desc='" + desc + '\'' +
				", status='" + status + '\'' +
				", sysInfo=" + sysInfo +
				", updateTime='" + updateTime + '\'' +
				", sumInboundReqCounts=" + sumInboundReqCounts +
				", sumOutboundReqCounts=" + sumOutboundReqCounts +
				", sumDealReqCounts=" + sumDealReqCounts +
				", sumDealReqTime=" + sumDealReqTime +
				", peerDealReqTime=" + peerDealReqTime +
				", sumErrDealReqCounts=" + sumErrDealReqCounts +
				", sumErrDealReqTime=" + sumErrDealReqTime +
				", serverName='" + serverName + '\'' +
				", serverVersion='" + serverVersion + '\'' +
				", nodeCount=" + nodeCount +
				", nodeName='" + nodeName + '\'' +
				'}';
	}
}
