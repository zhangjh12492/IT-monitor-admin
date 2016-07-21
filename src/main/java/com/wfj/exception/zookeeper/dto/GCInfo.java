/**
 * @Probject Name: netty-wfj-base
 * @Path: com.wfj.netty.monitor.dtoGCInfo.java
 * @Create By Jack
 * @Create In 2015年8月26日 下午9:15:42
 * TODO
 */
package com.wfj.exception.zookeeper.dto;

import java.io.Serializable;

/**
 * @Class Name GCInfo
 * 垃圾回收
 * @Author 
 * @Create In 2015年8月26日
 */
public class GCInfo implements Serializable{

	private String name;	//gc名称		新生代使用的是ParNew GC   老生代使用的是ConcurrentMarkSweep GC。
	
	private long collectionCount;	//采集数量
	
	private long collectionTime;	//(毫秒) 采集时间

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
	 * @Return the long collectionCount
	 */
	public long getCollectionCount() {
		return collectionCount;
	}

	/**
	 * @Param long collectionCount to set
	 */
	public void setCollectionCount(long collectionCount) {
		this.collectionCount = collectionCount;
	}

	/**
	 * @Return the long collectionTime
	 */
	public long getCollectionTime() {
		return collectionTime;
	}

	/**
	 * @Param long collectionTime to set
	 */
	public void setCollectionTime(long collectionTime) {
		this.collectionTime = collectionTime;
	}
}
