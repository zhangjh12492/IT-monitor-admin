package com.wfj.exception.dal.service;

import java.util.List;

import com.wfj.exception.dal.cond.EarlyWarnSysInfoCond;
import com.wfj.exception.dal.entity.MesEarlyWarnInfo;
import com.wfj.exception.util.DataTableJson;
import com.wfj.util.Page;

public interface MesEarlyWarnService {

	void insertMesEarlyWarn(MesEarlyWarnInfo param) throws Exception;
	
	void updateMesEarlyWarn(MesEarlyWarnInfo param) throws Exception;
	
	public DataTableJson findEarlyWarnInfoByPage(Page page) throws Exception;
	
	void deleteMesEarlyWarn(Integer id) throws Exception;
	
	void deleteEwSysRefByAllId(EarlyWarnSysInfoCond ewSys) throws Exception;
	
	/**
	 * 同一个ewId,多个sysId,插入多条系统预警配置关联信息
	 * @Title: insertMoreEwSys
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author ZJHao
	 * @param ewId
	 * @param sysIds
	 * @throws Exception
	 * @return void
	 * @throws
	 * @date 2015-8-27 上午11:49:31
	 */
	void insertMoreEwSys(Integer ewId,Integer [] sysIds) throws Exception;
	/**
	 * 根据系统id获取系统的预警配置信息
	 * @param sysId
	 * @return
	 * @throws Exception
	 */
	List<MesEarlyWarnInfo> findMesEarlyInfoBySysId(MesEarlyWarnInfo mesEarlyWarnInfo) throws Exception;
	
	/**
	 * 根据系统code查询系统的预警配置信息
	 * @Title: findMesEarlyInfoBySysCode
	 * @author ZJHao
	 * @param sysCode
	 * @return
	 * @throws Exception
	 * @return List<MesEarlyWarnInfo>
	 * @throws
	 * @date 2015年9月22日 下午4:54:28
	 */
	List<MesEarlyWarnInfo> findMesEarlyInfoBySysCode(String sysCode) throws Exception;


	/**
	 * 根据系统code查询系统的单个预警配置信息
	 * @Title: findMesEarlyInfoBySysCode
	 * @author ZJHao
	 * @param sysCode
	 * @return
	 * @throws Exception
	 * @return List<MesEarlyWarnInfo>
	 * @throws
	 * @date 2015年9月22日 下午4:54:28
	 */
	MesEarlyWarnInfo findOneMesEarlyInfoBySysCode(String sysCode) throws Exception;

}
