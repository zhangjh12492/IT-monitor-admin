/**
 * @Probject Name: netty-wfj-base
 * @Path: com.wfj.netty.monitor.dtoSystemInfo.java
 * @Create By Jack
 * @Create In 2015年8月26日 下午7:00:31
 * TODO
 */
package com.wfj.exception.zookeeper.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Class Name SystemInfo
 * @Author 
 * @Create In 2015年8月26日
 */
public class SystemInfo implements Serializable{
	
	/** 属性名称 */
	private static final long serialVersionUID = 1L;
	 /** 可使用内存. */
	private long totalMem;	//已用内存	(MB)
	 /** 剩余内存. */
	private long freeMem;	//可用内存  (MB)
	 /** 最大可使用内存. */
	private long maxMem;	//最大内存 (MB)
	/** 操作系统. */
	private String osName;	//系统名称
	
	private String sysArch;	//返回操作系统的架构
	
	private String version;	//返回操作系统的版本
	
	private int availableProcessors;	//返回 Java 虚拟机可以使用的处理器数目
	
	private long committedVirtualMemorySize;	// (byte)提交虚拟内存大小   
	
	private long processCpuTime;	// (纳秒) 返回所使用的CPU时间的进程Java虚拟机运行在纳秒   纳秒->秒  1,000,000,000 -> 1
	
	private long freeSwapSpaceSize;	//(MB)可用交换空间
	/** 剩余的物理内存. */
	private long freePhysicalMemorySize;	//(MB)可用物理内存
	 /** 总的物理内存. */
	private long totalPhysicalMemorySize;	//(MB)总使用物理内存
	
	private String hostName;	//计算机名称
	
	private List<String> ips;	//
	
	private MemoryUsageCustom heapMemoryUsage;	//堆内存
	
	private MemoryUsageCustom nonHeapMemoryUsage;	//非堆内存
	
	private int threadCount;	//线程数量
	
	private int peakThreadCount;	//峰值线程数量
	
	private int daemonThreadCount;	//(纳秒) 守护线程数量

	private long currentThreadCpuTime;	//(纳秒) 当前线程在cpu中的使用时间
	
	private long currentThreadUserTime;	//(纳秒) 当前线程在用户模式中执行的 CPU 时间
	
	private String vmName;	//java虚拟机名称
		 
	private String vmVendor;	//虚拟机供应商
	
	private String vmVersion;	//java虚拟机版本
	
	private String classPath;	//应用的类路径
	
	private String libraryPath;	//系统path值

	private String compliationName;	//编辑器名称
	
	private long totalCompliationTime;	//(毫秒)  java编辑器使用总时间
	
	private List<MemPoolInfo> memPoolInfos;	//内存池
	
	private List<GCInfo> GCInfos;	

	
	/**
	 * @Return the long committedVirtualMemorySize
	 */
	public long getCommittedVirtualMemorySize() {
		return committedVirtualMemorySize;
	}

	/**
	 * @Param long committedVirtualMemorySize to set
	 */
	public void setCommittedVirtualMemorySize(long committedVirtualMemorySize) {
		this.committedVirtualMemorySize = committedVirtualMemorySize;
	}

	/**
	 * @Return the long totalMem
	 */
	public long getTotalMem() {
		return totalMem;
	}

	/**
	 * @Param long totalMem to set
	 */
	public void setTotalMem(long totalMem) {
		this.totalMem = totalMem;
	}

	/**
	 * @Return the long freeMem
	 */
	public long getFreeMem() {
		return freeMem;
	}

	/**
	 * @Param long freeMem to set
	 */
	public void setFreeMem(long freeMem) {
		this.freeMem = freeMem;
	}

	/**
	 * @Return the long maxMem
	 */
	public long getMaxMem() {
		return maxMem;
	}

	/**
	 * @Param long maxMem to set
	 */
	public void setMaxMem(long maxMem) {
		this.maxMem = maxMem;
	}

	/**
	 * @Return the String osName
	 */
	public String getOsName() {
		return osName;
	}

	/**
	 * @Param String osName to set
	 */
	public void setOsName(String osName) {
		this.osName = osName;
	}

	/**
	 * @Return the String sysArch
	 */
	public String getSysArch() {
		return sysArch;
	}

	/**
	 * @Param String sysArch to set
	 */
	public void setSysArch(String sysArch) {
		this.sysArch = sysArch;
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
	 * @Return the int availableProcessors
	 */
	public int getAvailableProcessors() {
		return availableProcessors;
	}

	/**
	 * @Param int availableProcessors to set
	 */
	public void setAvailableProcessors(int availableProcessors) {
		this.availableProcessors = availableProcessors;
	}

	/**
	 * @Return the long processCpuTime
	 */
	public long getProcessCpuTime() {
		return processCpuTime;
	}

	/**
	 * @Param long processCpuTime to set
	 */
	public void setProcessCpuTime(long processCpuTime) {
		this.processCpuTime = processCpuTime;
	}

	/**
	 * @Return the long freeSwapSpaceSize
	 */
	public long getFreeSwapSpaceSize() {
		return freeSwapSpaceSize;
	}

	/**
	 * @Param long freeSwapSpaceSize to set
	 */
	public void setFreeSwapSpaceSize(long freeSwapSpaceSize) {
		this.freeSwapSpaceSize = freeSwapSpaceSize;
	}

	/**
	 * @Return the long freePhysicalMemorySize
	 */
	public long getFreePhysicalMemorySize() {
		return freePhysicalMemorySize;
	}

	/**
	 * @Param long freePhysicalMemorySize to set
	 */
	public void setFreePhysicalMemorySize(long freePhysicalMemorySize) {
		this.freePhysicalMemorySize = freePhysicalMemorySize;
	}

	/**
	 * @Return the long totalPhysicalMemorySize
	 */
	public long getTotalPhysicalMemorySize() {
		return totalPhysicalMemorySize;
	}

	/**
	 * @Param long totalPhysicalMemorySize to set
	 */
	public void setTotalPhysicalMemorySize(long totalPhysicalMemorySize) {
		this.totalPhysicalMemorySize = totalPhysicalMemorySize;
	}

	/**
	 * @Return the String hostName
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * @Param String hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * @Return the List<String> ips
	 */
	public List<String> getIps() {
		return ips;
	}

	/**
	 * @Param List<String> ips to set
	 */
	public void setIps(List<String> ips) {
		this.ips = ips;
	}
	public MemoryUsageCustom getHeapMemoryUsage() {
		return heapMemoryUsage;
	}
	public void setHeapMemoryUsage(MemoryUsageCustom heapMemoryUsage) {
		this.heapMemoryUsage = heapMemoryUsage;
	}
	public MemoryUsageCustom getNonHeapMemoryUsage() {
		return nonHeapMemoryUsage;
	}
	public void setNonHeapMemoryUsage(MemoryUsageCustom nonHeapMemoryUsage) {
		this.nonHeapMemoryUsage = nonHeapMemoryUsage;
	}

	/**
	 * @Return the int threadCount
	 */
	public int getThreadCount() {
		return threadCount;
	}

	/**
	 * @Param int threadCount to set
	 */
	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	/**
	 * @Return the int peakThreadCount
	 */
	public int getPeakThreadCount() {
		return peakThreadCount;
	}

	/**
	 * @Param int peakThreadCount to set
	 */
	public void setPeakThreadCount(int peakThreadCount) {
		this.peakThreadCount = peakThreadCount;
	}

	/**
	 * @Return the int daemonThreadCount
	 */
	public int getDaemonThreadCount() {
		return daemonThreadCount;
	}

	/**
	 * @Param int daemonThreadCount to set
	 */
	public void setDaemonThreadCount(int daemonThreadCount) {
		this.daemonThreadCount = daemonThreadCount;
	}

	/**
	 * @Return the long currentThreadCpuTime
	 */
	public long getCurrentThreadCpuTime() {
		return currentThreadCpuTime;
	}

	/**
	 * @Param long currentThreadCpuTime to set
	 */
	public void setCurrentThreadCpuTime(long currentThreadCpuTime) {
		this.currentThreadCpuTime = currentThreadCpuTime;
	}

	/**
	 * @Return the long currentThreadUserTime
	 */
	public long getCurrentThreadUserTime() {
		return currentThreadUserTime;
	}

	/**
	 * @Param long currentThreadUserTime to set
	 */
	public void setCurrentThreadUserTime(long currentThreadUserTime) {
		this.currentThreadUserTime = currentThreadUserTime;
	}

	/**
	 * @Return the String vmName
	 */
	public String getVmName() {
		return vmName;
	}

	/**
	 * @Param String vmName to set
	 */
	public void setVmName(String vmName) {
		this.vmName = vmName;
	}

	/**
	 * @Return the String vmVendor
	 */
	public String getVmVendor() {
		return vmVendor;
	}

	/**
	 * @Param String vmVendor to set
	 */
	public void setVmVendor(String vmVendor) {
		this.vmVendor = vmVendor;
	}

	/**
	 * @Return the String vmVersion
	 */
	public String getVmVersion() {
		return vmVersion;
	}

	/**
	 * @Param String vmVersion to set
	 */
	public void setVmVersion(String vmVersion) {
		this.vmVersion = vmVersion;
	}

	/**
	 * @Return the String classPath
	 */
	public String getClassPath() {
		return classPath;
	}

	/**
	 * @Param String classPath to set
	 */
	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	/**
	 * @Return the String libraryPath
	 */
	public String getLibraryPath() {
		return libraryPath;
	}

	/**
	 * @Param String libraryPath to set
	 */
	public void setLibraryPath(String libraryPath) {
		this.libraryPath = libraryPath;
	}

	/**
	 * @Return the String compliationName
	 */
	public String getCompliationName() {
		return compliationName;
	}

	/**
	 * @Param String compliationName to set
	 */
	public void setCompliationName(String compliationName) {
		this.compliationName = compliationName;
	}

	/**
	 * @Return the long totalCompliationTime
	 */
	public long getTotalCompliationTime() {
		return totalCompliationTime;
	}

	/**
	 * @Param long totalCompliationTime to set
	 */
	public void setTotalCompliationTime(long totalCompliationTime) {
		this.totalCompliationTime = totalCompliationTime;
	}

	/**
	 * @Return the List<MemPoolInfo> memPoolInfos
	 */
	public List<MemPoolInfo> getMemPoolInfos() {
		return memPoolInfos;
	}

	/**
	 * @Param List<MemPoolInfo> memPoolInfos to set
	 */
	public void setMemPoolInfos(List<MemPoolInfo> memPoolInfos) {
		this.memPoolInfos = memPoolInfos;
	}

	/**
	 * @Return the List<GCInfo> GCInfos
	 */
	public List<GCInfo> getGCInfos() {
		return GCInfos;
	}

	/**
	 * @Param List<GCInfo> gCInfos to set
	 */
	public void setGCInfos(List<GCInfo> gCInfos) {
		GCInfos = gCInfos;
	}

	@Override
	public String toString() {
		return "SystemInfo [totalMem=" + totalMem + ", freeMem=" + freeMem + ", maxMem=" + maxMem + ", osName=" + osName + ", sysArch=" + sysArch + ", version=" + version + ", availableProcessors="
				+ availableProcessors + ", committedVirtualMemorySize=" + committedVirtualMemorySize + ", processCpuTime=" + processCpuTime + ", freeSwapSpaceSize=" + freeSwapSpaceSize
				+ ", freePhysicalMemorySize=" + freePhysicalMemorySize + ", totalPhysicalMemorySize=" + totalPhysicalMemorySize + ", hostName=" + hostName + ", ips=" + ips + ", heapMemoryUsage="
				+ heapMemoryUsage + ", nonHeapMemoryUsage=" + nonHeapMemoryUsage + ", threadCount=" + threadCount + ", peakThreadCount=" + peakThreadCount + ", daemonThreadCount=" + daemonThreadCount
				+ ", currentThreadCpuTime=" + currentThreadCpuTime + ", currentThreadUserTime=" + currentThreadUserTime + ", vmName=" + vmName + ", vmVendor=" + vmVendor + ", vmVersion=" + vmVersion
				+ ", classPath=" + classPath + ", libraryPath=" + libraryPath + ", compliationName=" + compliationName + ", totalCompliationTime=" + totalCompliationTime + ", memPoolInfos="
				+ memPoolInfos + ", GCInfos=" + GCInfos + "]";
	}
	
	public static void main(String[] args) {
		System.out.println(new BigDecimal((14086890300f-187201200f)/1000000000/60*100).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue());
	}
	
}
