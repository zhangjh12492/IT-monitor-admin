package com.wfj.exception.dal.hbase.dao;

import com.wfj.exception.common.HbasePage;
import com.wfj.exception.dal.vo.ClientExceptionReq;

import java.util.List;

public interface HbaseDataService {

	ClientExceptionReq selectById(String id) throws Exception;
	
	List<ClientExceptionReq> getExceptionsByBusiId(HbasePage hbasePage) throws Exception;
	/**
	 * 查询当前登陆的用户的所有未处理的异常信息
	 * @Title: showMessListByUserNoProcess
	 * @author ZJHao
	 * @return
	 * @throws Exception
	 * @return List<ClientExceptionReq>
	 * @throws
	 * @date 2015年9月23日 下午5:16:05
	 */
	List<ClientExceptionReq> showMessListByUserNoProcess(HbasePage hbasePage) throws Exception;
	
	String updateMesProcessStatus(String sysCode,String beforeProcessStatus,String afterProcessStatus,String errLevel) throws Exception;
	
	public List<ClientExceptionReq> getExceptionsByErrCode(HbasePage hbasePage) throws Exception;
	
	public List findExceptionListOneHour(HbasePage hbasePage) throws Exception;
	/**
	 * 修改单条信息
	 * @Title: updateOneMes
	 * @author ZJHao
	 * @param req
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 * @date 2015-8-30 下午5:48:47
	 */
	String updateOneMes(ClientExceptionReq req ) throws Exception;
	
	/**
	 * 修改单条信息
	 * @Title: updateOneMes
	 * @author ZJHao
	 * @param req
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 * @date 2015-8-30 下午5:48:47
	 */
	public String updateMoreMesStatus(ClientExceptionReq req ) throws Exception;
	/**
	 * 查询异常信息用于在系统监控页面展示
	 * @Title: selectMessageInfoForSysMonitorShow
	 * @author ZJHao
	 * @return
	 * @throws Exception
	 * @return List<ClientExceptionReq>
	 * @throws
	 * @date 2015-9-15 上午9:44:23
	 */
	public List<ClientExceptionReq> selectMessageInfoForSysMonitorShow(HbasePage hbasePage) throws Exception;
}
