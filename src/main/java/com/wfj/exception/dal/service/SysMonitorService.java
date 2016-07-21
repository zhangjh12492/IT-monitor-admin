package com.wfj.exception.dal.service;

import java.util.List;

import com.wfj.exception.dal.cond.ApplicationMonitorCond;
import com.wfj.exception.dal.vo.ApplicationShowDataVo;

public interface SysMonitorService {

	/**
	 * 获取初始化系统监控信息的数据
	 * @Title: initAppShowData
	 * @author ZJHao
	 * @return
	 * @return List<ApplicationShowDataVo>
	 * @throws
	 * @date 2015-9-14 上午12:47:42
	 */
	public List<ApplicationShowDataVo> initAppShowDataAvg(String sysCode) throws Exception;
	
	/**
	 * 获取监控界面的系统下的所有系统的监控信息列表
	 * @Title: sysMoniterDataList
	 * @author ZJHao
	 * @return
	 * @throws Exception
	 * @return List<ApplicationShowDataVo>
	 * @throws
	 * @date 2015-9-14 下午4:09:08
	 */
	public List<ApplicationShowDataVo> sysMoniterDataList(String sysCode) throws Exception;
	
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
	public ApplicationShowDataVo getSysOneNodeMonitorInfoByNameCode(ApplicationMonitorCond appMonitorCond) throws Exception;
	
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
	public List<ApplicationShowDataVo> getSysOneNodeUsageMemory(ApplicationMonitorCond appMonitorCond) throws Exception;
}
