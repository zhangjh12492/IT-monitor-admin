package com.wfj.exception.dal.service;

import com.wfj.exception.common.HbasePage;
import com.wfj.exception.dal.cond.MesAllProcessReqCond;
import com.wfj.exception.dal.vo.ClientExceptionReq;
import com.wfj.exception.util.DataTableJson;
import com.wfj.util.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ExceptionInfoService {

	/**
	 * 查询以小时统计的数据
	 * @Title: showMesListFromChartOneHour
	 * @author ZJHao
	 * @param page
	 * @param mesAllReqCond
	 * @return
	 * @throws Exception
	 * @return DataTableJson
	 * @throws
	 * @date 2015-8-29 下午5:23:47
	 */
	public DataTableJson showMesListFromChartOneHour( MesAllProcessReqCond mesAllReqCond,HttpServletRequest request,HbasePage hbasePage) throws Exception;
//	/**
//	 * 查询当前登陆用户下的所有要处理的异常信息
//	 * @Title: showMesListByUser
//	 * @author ZJHao
//	 * @param page
//	 * @param mesAllReqCond
//	 * @return
//	 * @throws Exception
//	 * @return DataTableJson
//	 * @throws
//	 * @date 2015-8-29 下午5:23:47
//	 */
//	public DataTableJson showMessListByUserNoProcess(Page page, HbasePage hbasePage,HttpServletRequest request,MesAllProcessReqCond mesAllReqCond) throws Exception;
/**
	 * 查询当前登陆用户下的所有要处理的异常信息
	 * @Title: showMesListByUser
	 * @author ZJHao
	 * @param page
	 * @param mesAllReqCond
	 * @return
	 * @throws Exception
	 * @return DataTableJson
	 * @throws
	 * @date 2015-8-29 下午5:23:47
	 */
	public DataTableJson showMessListByUserNoProcess( HbasePage hbasePage,HttpServletRequest request,MesAllProcessReqCond mesAllReqCond) throws Exception;
	/**
	 * 根据errId获取单条异常信息
	 * @Title: findExceptionByErrId
	 * @author ZJHao
	 * @param errId
	 * @return
	 * @throws Exception
	 * @return ClientExceptionReq
	 * @throws
	 * @date 2015-8-29 下午9:47:05
	 */
	public ClientExceptionReq findExceptionByErrId(String errId ) throws Exception;
	/**
	 * 修改单条异常信息
	 * @Title: updateOneMes
	 * @author ZJHao
	 * @param req
	 * @throws Exception
	 * @return void
	 * @throws
	 * @date 2015-8-30 下午5:46:54
	 */
	public void updateOneMes(ClientExceptionReq req,HttpServletRequest request) throws Exception;
	
	/**
	 * 修改多条信息的状态
	 * @Title: updateOneMes
	 * @author ZJHao
	 * @param req
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 * @date 2015-8-30 下午5:48:47
	 */
	public String updateMoreMesStatus(ClientExceptionReq req,HttpServletRequest request) throws Exception;
	
	/**
	 * 查询详细的异常信息用于在系统监控页面展示
	 * @Title: selectMessageInfoForSysMonitorShow
	 * @author ZJHao
	 * @return
	 * @throws Exception
	 * @return List<ClientExceptionReq>
	 * @throws
	 * @date 2015-9-14 下午8:51:05
	 */
	public List<ClientExceptionReq> selectMessageInfoForSysMonitorShow(MesAllProcessReqCond mesAllProcessReqCound,HbasePage hbasePage) throws Exception;
	
	
	
}
