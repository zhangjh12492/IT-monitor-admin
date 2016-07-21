/**
 * @Probject Name: netty-wfj-base
 * @Path: com.wfj.netty.monitor.dtoMemPoolInfo.java
 * @Create By Jack
 * @Create In 2015年8月26日 下午9:13:24
 * TODO
 */
package com.wfj.exception.zookeeper.dto;

import java.io.Serializable;


/**
 * @Class Name MemPoolInfo
 * @Author 
 * @Create In 2015年8月26日
 */
public class MemPoolInfo implements Serializable{

	/** 属性名称 */
	private static final long serialVersionUID = 1L;

	private String memoryManagerNames;	//内存管理名称
	
	private MemoryUsageCustom memoryUsage;	//系统内存使用

	/**
	 * @Return the String memoryManagerNames
	 */
	public String getMemoryManagerNames() {
		return memoryManagerNames;
	}

	/**
	 * @Param String memoryManagerNames to set
	 */
	public void setMemoryManagerNames(String memoryManagerNames) {
		this.memoryManagerNames = memoryManagerNames;
	}

	public MemoryUsageCustom getMemoryUsage() {
		return memoryUsage;
	}

	public void setMemoryUsage(MemoryUsageCustom memoryUsage) {
		this.memoryUsage = memoryUsage;
	}

	@Override
	public String toString() {
		return "MemPoolInfo [memoryManagerNames=" + memoryManagerNames + ", memoryUsage=" + memoryUsage + "]";
	}

	
	
}
