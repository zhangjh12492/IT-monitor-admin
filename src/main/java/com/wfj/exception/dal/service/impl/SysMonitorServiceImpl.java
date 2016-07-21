package com.wfj.exception.dal.service.impl;

import com.wfj.exception.dal.cond.ApplicationMonitorCond;
import com.wfj.exception.dal.entity.MesEarlyWarnInfo;
import com.wfj.exception.dal.entity.SysInfo;
import com.wfj.exception.dal.entity.ZkNodeDataInfo;
import com.wfj.exception.dal.service.MesEarlyWarnService;
import com.wfj.exception.dal.service.SysInfoService;
import com.wfj.exception.dal.service.SysMonitorService;
import com.wfj.exception.dal.service.ZkNodeDataService;
import com.wfj.exception.dal.vo.ApplicationShowDataVo;
import com.wfj.exception.zookeeper.dto.ApplicationInfo;
import com.wfj.exception.zookeeper.util.SysMetronicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("sysMonitorService")
public class SysMonitorServiceImpl implements SysMonitorService{

	
	private static final Logger log=LoggerFactory.getLogger(SysMonitorServiceImpl.class);
	
	@Resource
	private SysInfoService sysInfoService;
	@Resource
	private MesEarlyWarnService mesEarlyWarnService;
	private Map<String, List<ApplicationInfo>> applicationMaps; //根据子系统的名称存放一个系统下所有的子系统数据
	@Resource
	private ZkNodeDataService zkNodeDataService;
	
	/**
	 * 监控界面的所有子系统的平均数据
	 * @Title: initAppShowDataAvg
	 * @author ZJHao
	 * @return
	 * @throws Exception
	 * @throws
	 * @date 2015-9-15 下午4:36:17
	 */
	@Override
	public List<ApplicationShowDataVo> initAppShowDataAvg(String sysCode) throws Exception{
		List<SysInfo> sysList = sysInfoService.selectAllSys(sysCode);
		List<ApplicationShowDataVo> listAppShowData=null;
		if (sysList != null && sysList.size() > 0) {
			List<String> sysNameCodes = new ArrayList<String>();
			for (SysInfo sysInfo : sysList) {
				sysNameCodes.add(sysInfo.getSysName() + "-" + sysInfo.getSysCode());
				//1.首先从数据库中拿取数据
				ZkNodeDataInfo zkNodeData=new ZkNodeDataInfo(sysInfo.getSysName() + "-" + sysInfo.getSysCode());
				applicationMaps = zkNodeDataService.processZkNodeDataByTimeHM(zkNodeData);
				List<MesEarlyWarnInfo> earlyWarnInfos=mesEarlyWarnService.findMesEarlyInfoBySysCode(sysInfo.getSysCode());
				MesEarlyWarnInfo mesEarlyWarnInfo=null;
				if(earlyWarnInfos!=null&&earlyWarnInfos.size()>0){
					mesEarlyWarnInfo=earlyWarnInfos.get(0);
				}
				
				listAppShowData = SysMetronicUtil.convertSysMonitorData(applicationMaps,mesEarlyWarnInfo);
			}
			
		}
		log.info("获取数据库中的系统监控数据");
		return listAppShowData;
	}
	/**
	 * 获取监控界面的系统下的所有子系统的监控信息列表
	 * @Title: sysMoniterDataList
	 * @author ZJHao
	 * @return
	 * @throws Exception
	 * @return List<ApplicationShowDataVo>
	 * @throws
	 * @date 2015-9-14 下午4:09:08
	 */
	public List<ApplicationShowDataVo> sysMoniterDataList(String sysCode) throws Exception{
		List<SysInfo> sysList = sysInfoService.selectAllSys(sysCode);
		List<ApplicationShowDataVo> listAppShowData=null;
		if (sysList != null && sysList.size() > 0) {
			List<String> sysNameCodes = new ArrayList<String>();
			for (SysInfo sysInfo : sysList) {
				sysNameCodes.add(sysInfo.getSysName() + "-" + sysInfo.getSysCode());
				//1.首先从数据库中拿取list数据
				ZkNodeDataInfo zkNodeData=new ZkNodeDataInfo(sysInfo.getSysName() + "-" + sysInfo.getSysCode());
				List<ApplicationInfo> applicationInfos=zkNodeDataService.selectNearestZkOneDataByNode(zkNodeData);
				MesEarlyWarnInfo mesEarlyWarnInfo=mesEarlyWarnService.findOneMesEarlyInfoBySysCode(sysInfo.getSysCode());
				listAppShowData = SysMetronicUtil.getSysMonitorListData(applicationInfos,sysInfo.getSysName()+"-"+sysInfo.getSysCode(),mesEarlyWarnInfo);
			}
		}
		log.info("获取数据库中的系统监控数据列表完成");
		return listAppShowData;
	}
	
	/**
	 * 获取单个系统下的单条的系统监控信息
	 * @Title: getSysOneNodeMonitorInfoByNameCode
	 * @author ZJHao
	 * @return
	 * @throws Exception
	 * @return ApplicationShowDataVo
	 * @throws
	 * @date 2015-9-15 下午3:16:59
	 */
	public ApplicationShowDataVo getSysOneNodeMonitorInfoByNameCode(ApplicationMonitorCond appMonitorCond) throws Exception{
		ApplicationShowDataVo applicationShowDataVo=new ApplicationShowDataVo();
		ZkNodeDataInfo zkNodeDataInfo=new ZkNodeDataInfo(appMonitorCond.getParentName());
		zkNodeDataInfo.setNodeName(appMonitorCond.getName());
		applicationMaps = zkNodeDataService.processZkNodeDataByTimeHM(zkNodeDataInfo);
		MesEarlyWarnInfo mesEarlyWarnInfo=mesEarlyWarnService.findOneMesEarlyInfoBySysCode(appMonitorCond.getCode());
		applicationShowDataVo=SysMetronicUtil.getSysOneNodeMonitorInfo(applicationMaps, appMonitorCond,mesEarlyWarnInfo);
		return applicationShowDataVo;
	}
	/**
	 * 查询系统下单个节点的内存使用情况
	 * @Title: getSysOneNodeUsageMemory
	 * @author ZJHao
	 * @param appMonitorCond
	 * @return
	 * @throws Exception
	 * @return List<ApplicationShowDataVo>
	 * @throws
	 * @date 2015-9-15 下午7:40:02
	 */
	public List<ApplicationShowDataVo> getSysOneNodeUsageMemory(ApplicationMonitorCond appMonitorCond) throws Exception{
		//1.首先从数据库中拿取map数据
		ZkNodeDataInfo zkNodeDataInfo=new ZkNodeDataInfo(appMonitorCond.getParentName());
		zkNodeDataInfo.setNodeName(appMonitorCond.getName());
		List<ApplicationInfo> zkNodeDataInfos = zkNodeDataService.selectNearestZkDataByParentNameAndNode(zkNodeDataInfo);
		List<ApplicationShowDataVo> listAppShowData =SysMetronicUtil.getMemoryUsageCustomByOneNode(zkNodeDataInfos, appMonitorCond);
		return listAppShowData;
	}
	
}
