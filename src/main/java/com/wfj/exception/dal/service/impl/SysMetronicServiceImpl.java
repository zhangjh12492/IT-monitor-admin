package com.wfj.exception.dal.service.impl;

import com.wfj.exception.dal.entity.MesEarlyWarnInfo;
import com.wfj.exception.dal.entity.SysInfo;
import com.wfj.exception.dal.entity.ZkNodeDataInfo;
import com.wfj.exception.dal.service.MesEarlyWarnService;
import com.wfj.exception.dal.service.SysInfoService;
import com.wfj.exception.dal.service.SysMetronicService;
import com.wfj.exception.dal.service.ZkNodeDataService;
import com.wfj.exception.dal.vo.ApplicationShowDataVo;
import com.wfj.exception.zookeeper.dto.ApplicationInfo;
import com.wfj.exception.zookeeper.util.SysMetronicUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("sysMetronicService")
public class SysMetronicServiceImpl implements SysMetronicService{

	@Resource
	private SysInfoService sysInfoService;
	@Resource
	private MesEarlyWarnService mesEarlyWarnService;
	@Resource
	private ZkNodeDataService zkNodeDataService;
	
	private Map<String, List<ApplicationInfo>> applicationMaps; //根据子系统的名称存放一个系统下所有的子系统数据
	

	@Override
	public List<ApplicationShowDataVo> initAppShowData() throws Exception{
		List<SysInfo> sysList = sysInfoService.selectAllSys("00");
		if (sysList != null && sysList.size() > 0) {
			List<String> sysNameCodes = new ArrayList<String>();
			for (SysInfo sysInfo : sysList) {
				sysNameCodes.add(sysInfo.getSysName() + "-" + sysInfo.getSysCode());
				//1.首先从缓存中拿取map数据
				ZkNodeDataInfo zkNodeDataInfo=new ZkNodeDataInfo(sysInfo.getSysName() + "-" + sysInfo.getSysCode());
				applicationMaps = zkNodeDataService.processZkNodeDataByTimeHM(zkNodeDataInfo);
				MesEarlyWarnInfo mesEarlyWarnInfo=mesEarlyWarnService.findOneMesEarlyInfoBySysCode(sysInfo.getSysCode());
				List<ApplicationShowDataVo> listAppShowData = SysMetronicUtil.convertSysMonitorData(applicationMaps,mesEarlyWarnInfo);
				return listAppShowData;
			}
			
		}
		return null;
	}

	
	
	
}
