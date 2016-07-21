package com.wfj.exception.zookeeper.util;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.dal.cond.ApplicationMonitorCond;
import com.wfj.exception.dal.entity.MesEarlyWarnInfo;
import com.wfj.exception.dal.entity.ZkNodeDataInfo;
import com.wfj.exception.dal.service.MesEarlyWarnService;
import com.wfj.exception.dal.service.SysInfoService;
import com.wfj.exception.dal.vo.ApplicationShowDataVo;
import com.wfj.exception.util.DateUtils;
import com.wfj.exception.util.StringUtils;
import com.wfj.exception.zookeeper.dto.ApplicationInfo;
import com.wfj.exception.zookeeper.dto.GCInfo;
import com.wfj.exception.zookeeper.dto.MemPoolInfo;
import com.wfj.exception.zookeeper.dto.MemoryUsageCustom;
import com.wfj.sysmanager.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SysMetronicUtil {
	
	private static final Logger log=LoggerFactory.getLogger(SysMetronicUtil.class);
	@Resource(name="sysInfoService")
	private SysInfoService sysInfoService;
	
	@Resource
	private MesEarlyWarnService mesEarlyWarnService;
	/**
	 * 转换从zookeepr中拿取的系统应用的数据
	 * 获取的是信息的平均值
	 * @Title: convertSysMonitorData
	 * @author ZJHao
	 * @return void
	 * @throws
	 * @date 2015-9-10 下午9:15:57
	 */
	public static List<ApplicationShowDataVo> convertSysMonitorData(Map<String, List<ApplicationInfo>> mapApp,MesEarlyWarnInfo mesEarlyWarnInfo) {

		int mapSize = mapApp.size();

		String createTime; //创建日期

		String timeQuantum; //数据时间段

		List<ApplicationShowDataVo> listShowData = new ArrayList<ApplicationShowDataVo>();
		
		for (String key : mapApp.keySet()) {
			List<ApplicationInfo> appInfoList = mapApp.get(key);

//			log.info("appInfoList.size:-------------------------{}",appInfoList.size());
			if (appInfoList.size() > 2) {
				for (int j = 1; j < appInfoList.size() ; j++) {
					if(appInfoList.get(j).getSysInfo()==null){
						continue;
					}
					ApplicationShowDataVo appShowDataVo = new ApplicationShowDataVo();

					String name; //子系统名称
					long startServerResTime = 0L; //服务器响应时间
					long endServerResTime = 0L; //服务器响应时间
					float startErrRate  =0.0f;; //错误率
					float endErrRate =0.0f;; //错误率
//					float startThroughputRate = 0F; //吞吐率
					float endThroughputRate =0.0f;; //吞吐率=sumdealReqCounts/sumDealReqTimes/1000 (秒/次)

					float endSumDealReqTime=0.0f;;	//请求总时间

					float startCpuRate=0.0f;; //cup使用率
					float endCpuRate =0.0f;; //cup使用率
					float satisfactionApdex=0.0f;;	//apdex指标	满意
					float tolerateAdpex=0.0f;;	//apdex指标	容忍
					float apdexIndex=0f;	//apdex指标
					
					Long startSumDealReqCounts = 0L; //请求总次数
					Long endSumDealReqCounts = 0L; //请求总次数
					long startSumErrReqCounts = 0L; //错误量
					long endSumErrReqCounts = 0L; //错误量
					Long startUsedPhysicalMemorySize = 0L; //物理内存使用量
					Long endUsedPhysicalMemorySize = 0L; //物理内存使用量
 
					Long startSumInboundReqCounts = 0L; //请求次数
					Long endSumInboundReqCounts = 0L; //请求次数
					for (String k : mapApp.keySet()) {
						try {
							mapApp.get(k).get(j);
						} catch (Exception e) {
							break;
						}

//						startServerResTime += mapApp.get(k).get(j-1).getPeerDealReqTime();
						endServerResTime += mapApp.get(k).get(j).getPeerDealReqTime();

						startErrRate += mapApp.get(k).get(j-1).getSumErrDealReqCounts();
						endErrRate += mapApp.get(k).get(j ).getSumErrDealReqCounts();

//						startThroughputRate += mapApp.get(k).get(j-1).getSumInboundReqCounts();
						endThroughputRate += mapApp.get(k).get(j).getSumInboundReqCounts();

						startCpuRate += mapApp.get(k).get(j-1).getSysInfo().getProcessCpuTime();
						endCpuRate += mapApp.get(k).get(j).getSysInfo().getProcessCpuTime();

						startSumDealReqCounts += mapApp.get(k).get(j-1).getSumInboundReqCounts();
						endSumDealReqCounts += mapApp.get(k).get(j ).getSumInboundReqCounts();

						startSumErrReqCounts += mapApp.get(k).get(j-1).getSumErrDealReqCounts();
						endSumErrReqCounts += mapApp.get(k).get(j ).getSumErrDealReqCounts();

//						startTotalPhysicalMemorySize += mapApp.get(k).get(j).getSysInfo().getTotalPhysicalMemorySize();
						endUsedPhysicalMemorySize += mapApp.get(k).get(j).getSysInfo().getTotalPhysicalMemorySize()-(mapApp.get(k).get(j).getSysInfo().getFreePhysicalMemorySize());

						startSumInboundReqCounts += mapApp.get(k).get(j-1).getSumInboundReqCounts();
						endSumInboundReqCounts += mapApp.get(k).get(j ).getSumInboundReqCounts();

						endSumDealReqTime+=mapApp.get(k).get(j).getSumDealReqTime();

						if(mesEarlyWarnInfo==null){
							mesEarlyWarnInfo=new MesEarlyWarnInfo(true);
						}
						//满意数量
						if(mapApp.get(k).get(j).getPeerDealReqTime()<=mesEarlyWarnInfo.getSysReqApdexThresholdMin()){
							satisfactionApdex+=1;
						}else if(mapApp.get(k).get(j).getPeerDealReqTime()<=mesEarlyWarnInfo.getSysReqApdexThresholdMax()&&mapApp.get(k).get(j).getPeerDealReqTime()>mesEarlyWarnInfo.getSysReqApdexThresholdMin()){
							tolerateAdpex+=1;
						}
						
						//						name=k;
					}

					endServerResTime = (endServerResTime / mapSize); //响应时间
					endErrRate = (endSumInboundReqCounts - startSumInboundReqCounts) == 0 ? 0 : (new BigDecimal((float) (endErrRate - startErrRate) / (endSumInboundReqCounts - startSumInboundReqCounts)
							* 100 / mapSize).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue()); //错误率
					endThroughputRate =(endSumDealReqTime==0.0)?0.0f:(new BigDecimal(endSumDealReqCounts/(endSumDealReqTime/1000) / mapSize).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue()); //吞吐率
					endCpuRate = new BigDecimal((float) (endCpuRate - startCpuRate) / 1000000000 / 60 * 100 / mapSize).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue(); //cup使用率
					endSumErrReqCounts = (endSumErrReqCounts - startSumErrReqCounts) / mapSize; //访问错误量
					endSumDealReqCounts = (endSumDealReqCounts - startSumDealReqCounts) / mapSize; //访问量
					endUsedPhysicalMemorySize = endUsedPhysicalMemorySize / mapSize; //物理内存
					createTime = appInfoList.get(j).getUpdateTime(); //创建日期
					createTime = createTime.substring(createTime.indexOf(" ") + 1); //创建时间,转成hh:mm格式
//					timeQuantum = getTimeQuqntum(appInfoList.get(j-1).getUpdateTime(), appInfoList.get(j).getUpdateTime()); //展示时间段
					timeQuantum = appInfoList.get(j-1).getUpdateTime()+"-"+ appInfoList.get(j).getUpdateTime(); //展示时间段
					apdexIndex=((satisfactionApdex+tolerateAdpex*0.5)/mapSize==1)?100.0f:new BigDecimal((float)(satisfactionApdex+tolerateAdpex*0.5)/mapSize).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue()*100;	//apdex指标
					
					appShowDataVo.setCreateTime(createTime);
					appShowDataVo.setTimeQuantum(timeQuantum);
					appShowDataVo.setServerResTime(endServerResTime);
					appShowDataVo.setErrRate(endErrRate);
					appShowDataVo.setThroughputRate(endThroughputRate);
					appShowDataVo.setCpuRate(endCpuRate);
					appShowDataVo.setSumErrReqCounts(endSumErrReqCounts);
					appShowDataVo.setSumdealReqCounts(endSumDealReqCounts);
					appShowDataVo.setUsedPhysicalSpaceSize(endUsedPhysicalMemorySize);
					appShowDataVo.setErrRate(endErrRate);
					appShowDataVo.setApdexIndex(apdexIndex);
					listShowData.add(appShowDataVo);
				}
				break;
			}
		}
		return listShowData;
	}
	
	/**
	 * 将缓存中获取的系统监控信息的map集合的最近一条数据拿出来转为list集合,在页面中展示信息
	 * @Title: getSysMonitorListData
	 * @author ZJHao
	 * @return
	 * @return List<ApplicationShowDataVo>
	 * @throws
	 * @date 2015-9-14 下午3:32:33
	 */
	public static List<ApplicationShowDataVo> getSysMonitorListData(List<ApplicationInfo> applicationInfos,String sysNameCode,MesEarlyWarnInfo mesEarlyWarnInfo){
		if(mesEarlyWarnInfo==null)
			mesEarlyWarnInfo=new MesEarlyWarnInfo(true);
		List<ApplicationShowDataVo> appInfoShowDataList=new ArrayList<ApplicationShowDataVo>();
		if(applicationInfos!=null&&applicationInfos.size()>0){
				
			for(ApplicationInfo appInfo:applicationInfos){

				ApplicationShowDataVo appShowDataVo=new ApplicationShowDataVo();
				
				String createTime = appInfo.getUpdateTime(); //创建日期
				createTime = createTime.substring(createTime.indexOf(" ") + 1); //创建时间,转成hh:mm格式
				float apdexIndex=0.0f;
				long serverResTime = appInfo.getPeerDealReqTime(); //服务器响应时间
				float sumDealReqCounts=appInfo.getSumDealReqCounts();	//请求总次数
				float sumDealReqTime=appInfo.getSumDealReqTime();	//请求总时间 毫秒
				float throughputRate =(sumDealReqTime==0.0f)?0.0f:(new BigDecimal(sumDealReqCounts/(sumDealReqTime/1000f)).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue()); //吞吐率  请求总次数/请求总时间/1000(毫秒)
				float errRate = new BigDecimal((float)appInfo.getSumInboundReqCounts()==0?0.0f:appInfo.getSumErrDealReqCounts()/appInfo.getSumInboundReqCounts()).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue(); //错误率
				float cpuRate = new BigDecimal((float)appInfo.getSysInfo().getProcessCpuTime()/1000000000/60).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue(); //cup使用率
				Long usedPhysicalMemorySize = appInfo.getSysInfo().getTotalPhysicalMemorySize()-appInfo.getSysInfo().getFreePhysicalMemorySize(); //物理内存使用量

				float satisfactionApdex=0.0f;
				float tolerateAdpex=0.0f;


				//满意数量
				if(appInfo.getPeerDealReqTime()<=mesEarlyWarnInfo.getSysReqApdexThresholdMin()){
					satisfactionApdex=1;
				}else if(appInfo.getPeerDealReqTime()<=mesEarlyWarnInfo.getSysReqApdexThresholdMax()&&appInfo.getPeerDealReqTime()>mesEarlyWarnInfo.getSysReqApdexThresholdMin()){
					tolerateAdpex=1;
				}
				
				apdexIndex=(float)(satisfactionApdex+tolerateAdpex*0.5);
				appShowDataVo.setName(appInfo.getNodeName());
				appShowDataVo.setCreateTime(createTime);
				appShowDataVo.setServerResTime(serverResTime);
				appShowDataVo.setErrRate(errRate);
				appShowDataVo.setThroughputRate(throughputRate);
				appShowDataVo.setCpuRate(cpuRate);
				appShowDataVo.setUsedPhysicalSpaceSize(usedPhysicalMemorySize);
				appShowDataVo.setApdexIndex(apdexIndex);
				appShowDataVo.setParentName(sysNameCode);
				appShowDataVo.setRootName(appInfo.getName());
				appShowDataVo.setCode(sysNameCode.substring(sysNameCode.lastIndexOf("-") + 1, sysNameCode.length()));
				appShowDataVo.setServerName(appInfo.getServerName());
				appShowDataVo.setServerVersion(appInfo.getServerVersion());
				appShowDataVo.setNodeDesc(appInfo.getDesc());
				if(appInfo.getSysInfo()!=null&&appInfo.getSysInfo().getIps()!=null&&appInfo.getSysInfo().getIps().size()>0){
					appShowDataVo.setNodeIp(appInfo.getSysInfo().getIps().get(0));
				}else{
					appShowDataVo.setNodeIp("127.0.0.1");
				}
				appInfoShowDataList.add(appShowDataVo);
				
			}
		}
		return appInfoShowDataList;
	}
	
	/**
	 * 获取系统下单个节点的监控信息
	 * @Title: getSysOneNodeMonitorInfo
	 * @author ZJHao
	 * @param mapApp
	 * @param appMonitorCond
	 * @return
	 * @return ApplicationShowDataVo
	 * @throws
	 * @date 2015-9-15 下午7:04:24
	 */
	public static ApplicationShowDataVo getSysOneNodeMonitorInfo(Map<String, List<ApplicationInfo>> mapApp,ApplicationMonitorCond appMonitorCond,MesEarlyWarnInfo mesEarlyWarnInfo){
		ApplicationShowDataVo appShowDataVo=new ApplicationShowDataVo();
		List<ApplicationInfo> appList=mapApp.get(appMonitorCond.getName());
		if(appList!=null&&appList.size()>0){
				ApplicationInfo appInfo=appList.get(appList.size()-1);
//				ApplicationInfo appInfo1=appList.get(appList.size()-2);
				String createTime = appInfo.getUpdateTime(); //创建日期
				createTime = createTime.substring(createTime.indexOf(" ") + 1); //创建时间,转成hh:mm格式
				float apdexIndex=0.0f;
				long serverResTime = appInfo.getPeerDealReqTime(); //服务器响应时间
				float sumDealReqCounts=appInfo.getSumDealReqCounts();	//请求总次数
				float sumDealReqTime=appInfo.getSumDealReqTime();	//请求总时间 毫秒
				float throughputRate =(sumDealReqTime==0.0)?0.0f:( new BigDecimal(sumDealReqCounts/(sumDealReqTime/1000f)).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue()); //吞吐率  请求总次数/请求总时间/1000(毫秒)
				float errRate = new BigDecimal((float)appInfo.getSumInboundReqCounts()==0?0.0f:appInfo.getSumErrDealReqCounts()/appInfo.getSumInboundReqCounts()).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue(); //错误率
				float cpuRate = new BigDecimal((float)appInfo.getSysInfo().getProcessCpuTime()/1000000000/60).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue(); //cup使用率
				Long usedPhysicalMemorySize = appInfo.getSysInfo().getTotalPhysicalMemorySize()-appInfo.getSysInfo().getFreePhysicalMemorySize(); //物理内存使用量
				float satisfactionApdex=0.0f;
				float tolerateAdpex=0.0f;
				//满意数量
				if(appInfo.getPeerDealReqTime()<=mesEarlyWarnInfo.getSysReqApdexThresholdMin()){
					satisfactionApdex=1;
				}else if(appInfo.getPeerDealReqTime()<=mesEarlyWarnInfo.getSysReqApdexThresholdMax()&&appInfo.getPeerDealReqTime()>mesEarlyWarnInfo.getSysReqApdexThresholdMin()){
					tolerateAdpex=1;
				}
				
				apdexIndex=(float)(satisfactionApdex+tolerateAdpex*0.5);
				
				
				appShowDataVo.setName(appMonitorCond.getName());
				appShowDataVo.setCreateTime(createTime);
				appShowDataVo.setServerResTime(serverResTime);
				appShowDataVo.setErrRate(errRate);
				appShowDataVo.setThroughputRate(throughputRate);
				appShowDataVo.setCpuRate(cpuRate);
				appShowDataVo.setUsedPhysicalSpaceSize(usedPhysicalMemorySize);
				appShowDataVo.setApdexIndex(apdexIndex);
				appShowDataVo.setParentName(appMonitorCond.getParentName());
				appShowDataVo.setRootName(appInfo.getName());
				appShowDataVo.setCode(appMonitorCond.getParentName().substring(appMonitorCond.getParentName().lastIndexOf("-")+1,appMonitorCond.getParentName().length()));
				appShowDataVo.setServerName(appInfo.getServerName());
				appShowDataVo.setServerVersion(appInfo.getServerVersion());
				appShowDataVo.setNodeDesc(appInfo.getDesc());
				if(appInfo.getSysInfo()!=null&&appInfo.getSysInfo().getIps()!=null&&appInfo.getSysInfo().getIps().size()>0){
					appShowDataVo.setNodeIp(appInfo.getSysInfo().getIps().get(0));
				}else{
					appShowDataVo.setNodeIp("127.0.0.1");
				}
		}
		return appShowDataVo;
	}
	
	/**
	 * 根据节点名称以及code码等,获取系统下单个节点的堆内存使用信息
	 * @Title: getMemoryUsageCustomByOneNode
	 * @author ZJHao
	 * @param appMonitorCond
	 * @return
	 * @return List<ApplicationShowDataVo>
	 * @throws
	 * @date 2015-9-15 下午7:07:08
	 */
	public static List<ApplicationShowDataVo> getMemoryUsageCustomByOneNode(List<ApplicationInfo> applicationInfos,ApplicationMonitorCond appMonitorCond){
		List<ApplicationShowDataVo> appShowDataList=new ArrayList<ApplicationShowDataVo>();
		if(applicationInfos!=null&&applicationInfos.size()>=2){
			for(int i=1;i<applicationInfos.size();i++){
				if(applicationInfos.get(i).getSysInfo()==null)
					continue;
				ApplicationShowDataVo appShowDataVo=new ApplicationShowDataVo();
				String createTime = applicationInfos.get(i).getUpdateTime(); //创建日期
				createTime = createTime.substring(createTime.indexOf(" ") + 1); //创建时间,转成hh:mm格式
				
				appShowDataVo.setCreateTime(createTime);
				appShowDataVo.setTimeQuantum(applicationInfos.get(i-1).getUpdateTime()+"-"+applicationInfos.get(i).getUpdateTime());
				appShowDataVo.setHeapMemoryUsage(applicationInfos.get(i).getSysInfo().getHeapMemoryUsage());	//堆内存
				//JVM内存区域划分 EDEN SPACE、SURVIVOR SPACE、TENURED GEN
				for(MemPoolInfo memPoolInfo:applicationInfos.get(i).getSysInfo().getMemPoolInfos()){
					switch(memPoolInfo.getMemoryManagerNames()){
					case "Code Cache":
						appShowDataVo.setCodeCache(memPoolInfo.getMemoryUsage());
						break;
					case "PS Eden Space":
						appShowDataVo.setPsEdenSpace(memPoolInfo.getMemoryUsage());
						break;
					case "PS Survivor Space":
						appShowDataVo.setPsSurvivorSpace(memPoolInfo.getMemoryUsage());
						break;
					case "PS Old Gen":
						appShowDataVo.setPsOldGen(memPoolInfo.getMemoryUsage());
						break;
					case "PS Perm Gen":
						appShowDataVo.setPsPermGen(memPoolInfo.getMemoryUsage());
						break;
					default:
						appShowDataVo.setPsPermGen(new MemoryUsageCustom());
						break;
					}
				}
				//gc collection cpu time
				for(GCInfo gcInfo:applicationInfos.get(i).getSysInfo().getGCInfos()){
					switch (gcInfo.getName()) {
					case "PS MarkSweep":
						appShowDataVo.setGcPsMarkSweep(gcInfo);
						break;
					case "PS Scavenge":
						appShowDataVo.setGcPsScavenge(gcInfo);
						break;
					default:
						appShowDataVo.setGcPsMarkSweep(new GCInfo());
						appShowDataVo.setGcPsScavenge(new GCInfo());
						break;
					}
				}
				
				appShowDataVo.setClassPath(applicationInfos.get(i).getSysInfo().getClassPath());
				appShowDataVo.setLibraryPath(applicationInfos.get(i).getSysInfo().getLibraryPath());
				appShowDataVo.setThreadCount(applicationInfos.get(i).getSysInfo().getThreadCount());
				appShowDataVo.setDaemonThreadCount(applicationInfos.get(i).getSysInfo().getDaemonThreadCount());
				appShowDataVo.setPeakThreadCount(applicationInfos.get(i).getSysInfo().getPeakThreadCount());
				try {
					appShowDataList.add(appShowDataVoSizeUnitConvert(appShowDataVo));
				} catch (Exception e) {
					log.error("内存等数据处理异常..,{}",e.getMessage());
				}
			}
		}
		return appShowDataList;
	}
	


	/**
	 * 将zk节点中的data数据转换为zkNodeData对象
	 * @Title: zkNodeDataSysAppInfoConvert
	 * @author ZJHao
	 * @param applicationInfo
	 * @param rootName
	 * @param parentName
	 * @param nodeName
	 * @return
	 * @return ZkNodeData
	 * @throws
	 * @date 2015年9月25日 上午11:47:35
	 */
	public static ZkNodeDataInfo zkNodeDataSysAppInfoConvert(ApplicationInfo applicationInfo,String rootName,String parentName,String nodeName,Integer nodeCount){
		ZkNodeDataInfo zkNodeData=new ZkNodeDataInfo();
		if(applicationInfo!=null){
			try {
				zkNodeData.setClassPath(applicationInfo.getSysInfo().getClassPath());
				zkNodeData.setLibraryPath(applicationInfo.getSysInfo().getLibraryPath());
				applicationInfo.getSysInfo().setClassPath("");
				applicationInfo.getSysInfo().setLibraryPath("");
				applicationInfo.setDesc(applicationInfo.getName());
				if(applicationInfo.getSysInfo().getIps()!=null&&applicationInfo.getSysInfo().getIps().size()>0)
					zkNodeData.setNodeIp(applicationInfo.getSysInfo().getIps().get(0));
				else
					zkNodeData.setNodeIp("127.0.0.1");
			} catch (Exception e) {
				log.error("系统【{}】获取到SysInfo信息有误,{}", parentName, e.getMessage());
			}
			zkNodeData.setUpdateTime(applicationInfo.getUpdateTime());
			if( StringUtils.isBlank(applicationInfo.getDesc())){
				zkNodeData.setNodeDesc(applicationInfo.getName());
			}else{
				zkNodeData.setNodeDesc(applicationInfo.getDesc());
			}

			zkNodeData.setNodeData(JSONObject.toJSONString(applicationInfo));
		}
		zkNodeData.setCreatedTime(new Date());
		zkNodeData.setId(UuidUtil.getUuid());
		zkNodeData.setCreatedTimeFormat(DateUtils.format(new Date(),DateUtils.YMD_DASH_WITH_HHMM_LOLON));
		zkNodeData.setNodeCount(nodeCount);
		zkNodeData.setNodeName(nodeName);
		zkNodeData.setParentName(parentName);
		zkNodeData.setRootName(rootName);
		return zkNodeData;
	}
	
	public static String getTimeQuqntum(String startTime, String endTime) {
		String time;
		time = startTime.substring(0, startTime.lastIndexOf(":")) + " -- " + endTime.subSequence(0, endTime.lastIndexOf(":"));
		return time;
	}
	
	public static void main(String[] args) {
		float sumDealReqCount=702;
		float sumDealReqTime=25265;

		String sysNameCode="netty-test-00";
		System.out.println(sysNameCode.lastIndexOf("-"));
		System.out.println(sysNameCode.substring(sysNameCode.lastIndexOf("-") + 1, sysNameCode.length()));
		System.out.println(DateUtils.format(new Date(), DateUtils.YMD_DASH_WITH_HHMM_LOLON));
		System.out.println("2015-09-25 23:24".split(" ")[1]);

	}
	/**
	 * 将applicationShowDataVo中内存数据的单位进行换算
	 * @param applicationDataVo
	 * @return
	 */
	public static ApplicationShowDataVo appShowDataVoSizeUnitConvert(ApplicationShowDataVo applicationDataVo){
		applicationDataVo.getCodeCache().setCommitted(applicationDataVo.getCodeCache().getCommitted()/1024/1024);
		applicationDataVo.getCodeCache().setInit(applicationDataVo.getCodeCache().getInit()/1024/1024);
		applicationDataVo.getCodeCache().setMax(applicationDataVo.getCodeCache().getMax()/1024/1024);
		applicationDataVo.getCodeCache().setUsed(applicationDataVo.getCodeCache().getUsed()/1024/1024);
		
		applicationDataVo.getPsEdenSpace().setCommitted(applicationDataVo.getPsEdenSpace().getCommitted()/1024/1024);
		applicationDataVo.getPsEdenSpace().setInit(applicationDataVo.getPsEdenSpace().getInit()/1024/1024);
		applicationDataVo.getPsEdenSpace().setMax(applicationDataVo.getPsEdenSpace().getMax()/1024/1024);
		applicationDataVo.getPsEdenSpace().setUsed(applicationDataVo.getPsEdenSpace().getUsed()/1024/1024);
		
		applicationDataVo.getPsSurvivorSpace().setCommitted(applicationDataVo.getPsSurvivorSpace().getCommitted()/1024/1024);
		applicationDataVo.getPsSurvivorSpace().setInit(applicationDataVo.getPsSurvivorSpace().getInit()/1024/1024);
		applicationDataVo.getPsSurvivorSpace().setMax(applicationDataVo.getPsSurvivorSpace().getMax()/1024/1024);
		applicationDataVo.getPsSurvivorSpace().setUsed(applicationDataVo.getPsSurvivorSpace().getUsed()/1024/1024);
		
		applicationDataVo.getPsOldGen().setCommitted(applicationDataVo.getPsOldGen().getCommitted()/1024/1024);
		applicationDataVo.getPsOldGen().setInit(applicationDataVo.getPsOldGen().getInit()/1024/1024);
		applicationDataVo.getPsOldGen().setMax(applicationDataVo.getPsOldGen().getMax()/1024/1024);
		applicationDataVo.getPsOldGen().setUsed(applicationDataVo.getPsOldGen().getUsed()/1024/1024);
		
		applicationDataVo.getPsPermGen().setCommitted(applicationDataVo.getPsPermGen().getCommitted()/1024/1024);
		applicationDataVo.getPsPermGen().setInit(applicationDataVo.getPsPermGen().getInit()/1024/1024);
		applicationDataVo.getPsPermGen().setMax(applicationDataVo.getPsPermGen().getMax()/1024/1024);
		applicationDataVo.getPsPermGen().setUsed(applicationDataVo.getPsPermGen().getUsed()/1024/1024);
		
		applicationDataVo.getHeapMemoryUsage().setCommitted(applicationDataVo.getHeapMemoryUsage().getCommitted()/1024/1024);
		applicationDataVo.getHeapMemoryUsage().setInit(applicationDataVo.getHeapMemoryUsage().getInit()/1024/1024);
		applicationDataVo.getHeapMemoryUsage().setMax(applicationDataVo.getHeapMemoryUsage().getMax()/1024/1024);
		applicationDataVo.getHeapMemoryUsage().setUsed(applicationDataVo.getHeapMemoryUsage().getUsed()/1024/1024);
		
		
		
		
		return applicationDataVo;
	}
	

}
