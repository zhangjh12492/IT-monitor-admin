package com.wfj.exception.zookeeper.dto;

import java.io.Serializable;

/**
 * 内存池的内存使用情况的估计
 * @ClassName: MemoryUsageCustom
 * @author ZJHao
 * @date 2015-9-10 下午12:59:42
 *
 */
public class MemoryUsageCustom implements Serializable{
	
	/** 属性名称 */
	private static final long serialVersionUID = 1L;
	private long init;		//初始化内存大小	(b)
	private long used;		//使用内存大小	(b)
	private long committed;		//提交内存大小	(b)
	private long max;		//系统内存最大值	(b)
	public long getInit() {
		return init;
	}
	public void setInit(long init) {
		this.init = init;
	}
	public long getUsed() {
		return used;
	}
	public void setUsed(long used) {
		this.used = used;
	}
	public long getCommitted() {
		return committed;
	}
	public void setCommitted(long committed) {
		this.committed = committed;
	}
	public long getMax() {
		return max;
	}
	public void setMax(long max) {
		this.max = max;
	}
	@Override
	public String toString() {
		return "MemoryUsageCustom [init=" + init + ", used=" + used + ", committed=" + committed + ", max=" + max + "]";
	}
	

}
