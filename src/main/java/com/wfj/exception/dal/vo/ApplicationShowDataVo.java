package com.wfj.exception.dal.vo;

import com.wfj.exception.zookeeper.dto.GCInfo;
import com.wfj.exception.zookeeper.dto.MemPoolInfo;
import com.wfj.exception.zookeeper.dto.MemoryUsageCustom;


/**
 * 计算过后需要在页面中展示系统访问情况信息的类
 * @ClassName: ApplicationShowDataInfo
 * @author ZJHao
 * @date 2015-9-10 下午9:22:21
 *
 */
public class ApplicationShowDataVo {

	private String createTime; //创建时间	
	private long serverResTime;	//服务器响应时间
	private float errRate;	//错误率
	private float throughputRate; //吞吐率
	private float cpuRate;	//cpu使用率
	private Long sumdealReqCounts;	//访问量
	private Long sumErrReqCounts;	//错误量
	private Long usedPhysicalSpaceSize;	//物理内存使用量
	private Long sumInboundReqCounts;	//请求次数
	private String timeQuantum;	//时间段
	private String name;		//子系统名称
	private float apdexIndex;	//apdex指标          Apdex指数 ＝（1 × 满意样本 ＋ 0.5 × 容忍样本）÷ 样本总数
	private String parentName;	//父节点名称
	private String code;	//系统编码
	private String rootName;	//根节点名称
	private String serverName;	//服务器名称
	private String serverVersion;	//服务器版本
	
	private MemoryUsageCustom codeCache;	//	(代码缓存区)
	private MemoryUsageCustom psEdenSpace;	//伊甸园
	private MemoryUsageCustom psSurvivorSpace;	//幸存者区
	private MemoryUsageCustom psOldGen;	//老年代-养老区
	private MemoryUsageCustom psPermGen;	//永久代
	private GCInfo gcPsScavenge;	//gc在cpu运行时间
	private GCInfo gcPsMarkSweep;	//gc在cpu运行时间
	private String libraryPath;	//
	private String classPath;	//
	
	private Integer threadCount;	//线程数量
	private Integer peakThreadCount;	//峰值线程数量
	private Integer daemonThreadCount;	//(纳秒) 守护线程数量
	
	
	private String nodeDesc;	//系统下实例的描述
	private String nodeIp;		//系统下实例的ip地址
	
	private MemoryUsageCustom heapMemoryUsage;	//堆内存
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public long getServerResTime() {
		return serverResTime;
	}
	public void setServerResTime(long serverResTime) {
		this.serverResTime = serverResTime;
	}
	public float getErrRate() {
		return errRate;
	}
	public void setErrRate(float errRate) {
		this.errRate = errRate;
	}
	public float getThroughputRate() {
		return throughputRate;
	}
	public void setThroughputRate(float throughputRate) {
		this.throughputRate = throughputRate;
	}
	public String getTimeQuantum() {
		return timeQuantum;
	}
	public void setTimeQuantum(String timeQuantum) {
		this.timeQuantum = timeQuantum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getCpuRate() {
		return cpuRate;
	}
	public void setCpuRate(float cpuRate) {
		this.cpuRate = cpuRate;
	}
	public Long getSumdealReqCounts() {
		return sumdealReqCounts;
	}
	public void setSumdealReqCounts(Long sumdealReqCounts) {
		this.sumdealReqCounts = sumdealReqCounts;
	}
	public Long getSumErrReqCounts() {
		return sumErrReqCounts;
	}
	public void setSumErrReqCounts(Long sumErrReqCounts) {
		this.sumErrReqCounts = sumErrReqCounts;
	}
	public Long getUsedPhysicalSpaceSize() {
		return usedPhysicalSpaceSize;
	}
	public void setUsedPhysicalSpaceSize(Long usedPhysicalSpaceSize) {
		this.usedPhysicalSpaceSize = usedPhysicalSpaceSize;
	}
	public Long getSumInboundReqCounts() {
		return sumInboundReqCounts;
	}
	public void setSumInboundReqCounts(Long sumInboundReqCounts) {
		this.sumInboundReqCounts = sumInboundReqCounts;
	}
	public float getApdexIndex() {
		return apdexIndex;
	}
	public void setApdexIndex(float apdexIndex) {
		this.apdexIndex = apdexIndex;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRootName() {
		return rootName;
	}
	public void setRootName(String rootName) {
		this.rootName = rootName;
	}
	public MemoryUsageCustom getHeapMemoryUsage() {
		return heapMemoryUsage;
	}
	public void setHeapMemoryUsage(MemoryUsageCustom heapMemoryUsage) {
		this.heapMemoryUsage = heapMemoryUsage;
	}
	public MemoryUsageCustom getCodeCache() {
		return codeCache;
	}
	public void setCodeCache(MemoryUsageCustom codeCache) {
		this.codeCache = codeCache;
	}
	public MemoryUsageCustom getPsEdenSpace() {
		return psEdenSpace;
	}
	public void setPsEdenSpace(MemoryUsageCustom psEdenSpace) {
		this.psEdenSpace = psEdenSpace;
	}
	public MemoryUsageCustom getPsSurvivorSpace() {
		return psSurvivorSpace;
	}
	public void setPsSurvivorSpace(MemoryUsageCustom psSurvivorSpace) {
		this.psSurvivorSpace = psSurvivorSpace;
	}
	public MemoryUsageCustom getPsOldGen() {
		return psOldGen;
	}
	public void setPsOldGen(MemoryUsageCustom psOldGen) {
		this.psOldGen = psOldGen;
	}
	public MemoryUsageCustom getPsPermGen() {
		return psPermGen;
	}
	public void setPsPermGen(MemoryUsageCustom psPermGen) {
		this.psPermGen = psPermGen;
	}
	public GCInfo getGcPsScavenge() {
		return gcPsScavenge;
	}
	public void setGcPsScavenge(GCInfo gcPsScavenge) {
		this.gcPsScavenge = gcPsScavenge;
	}
	public GCInfo getGcPsMarkSweep() {
		return gcPsMarkSweep;
	}
	public void setGcPsMarkSweep(GCInfo gcPsMarkSweep) {
		this.gcPsMarkSweep = gcPsMarkSweep;
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
	public String getLibraryPath() {
		return libraryPath;
	}
	public void setLibraryPath(String libraryPath) {
		this.libraryPath = libraryPath;
	}
	public String getClassPath() {
		return classPath;
	}
	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
	public Integer getThreadCount() {
		return threadCount;
	}
	public void setThreadCount(Integer threadCount) {
		this.threadCount = threadCount;
	}
	public Integer getPeakThreadCount() {
		return peakThreadCount;
	}
	public void setPeakThreadCount(Integer peakThreadCount) {
		this.peakThreadCount = peakThreadCount;
	}
	public Integer getDaemonThreadCount() {
		return daemonThreadCount;
	}
	public void setDaemonThreadCount(Integer daemonThreadCount) {
		this.daemonThreadCount = daemonThreadCount;
	}
	public String getNodeDesc() {
		return nodeDesc;
	}
	public void setNodeDesc(String nodeDesc) {
		this.nodeDesc = nodeDesc;
	}
	public String getNodeIp() {
		return nodeIp;
	}
	public void setNodeIp(String nodeIp) {
		this.nodeIp = nodeIp;
	}
	
	
	
	
}
