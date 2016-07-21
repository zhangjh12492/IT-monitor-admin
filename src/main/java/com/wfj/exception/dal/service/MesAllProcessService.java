package com.wfj.exception.dal.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wfj.exception.dal.cond.MesAllProcessReqCond;
import com.wfj.exception.dal.entity.MesAllProcessReq;

public interface MesAllProcessService {

	/**
	 * 一小时内的所有异常条数统计折线图
	 * @Title: selectOneHourLine
	 * @author ZJHao
	 * @param mesAllProcessReqCond
	 * @return
	 * @throws Exception
	 * @return List<MesAllProcessReq>
	 * @throws
	 * @date 2015-8-29 下午2:22:30
	 */
	public List<MesAllProcessReq> selectOneHourLine(MesAllProcessReqCond mesAllProcessReqCond)  throws Exception;
	/**
	 * 一小时内的所有异常条数统计饼图
	 * @Title: selectOneHourLine
	 * @author ZJHao
	 * @param mesAllProcessReqCond
	 * @return
	 * @throws Exception
	 * @return List<MesAllProcessReq>
	 * @throws
	 * @date 2015-8-29 下午2:22:30
	 */
	public List<MesAllProcessReq> selectOneHourPie(MesAllProcessReqCond mesAllProcessReqCond)  throws Exception;
	/**
	 * 查询根据系统码分组的处理结果
	 * @Title: selectProcessMesOrderBySysCode
	 * @author ZJHao
	 * @return
	 * @throws Exception
	 * @return List<MesAllProcessReq>
	 * @throws
	 * @date 2015年9月22日 下午8:50:09
	 */
	public List<MesAllProcessReq> selectProcessMesGroupBySysCode(HttpServletRequest request) throws Exception;
	/**
	 * 根据系统码查询系统下的业务的异常处理数量
	 * @Title: selectBusiProcessMesBySysCode
	 * @author ZJHao
	 * @return
	 * @throws Exception
	 * @return List<MesAllProcessReq>
	 * @throws
	 * @date 2015年9月22日 下午8:50:09
	 */
	public List<MesAllProcessReq> selectBusiProcessMesBySysCode(String sysCode,String processStatus) throws Exception;
	/**
	 * 根据系统码查询插入的最近一条信息
	 * @Title: selectBusiProcessMesBySysCode
	 * @author ZJHao
	 * @return
	 * @throws Exception
	 * @return List<MesAllProcessReq>
	 * @throws
	 * @date 2015年9月22日 下午8:50:09
	 */
	public MesAllProcessReq selectOneTopProcessMesBySysCode(Map<String, String> params) throws Exception;

	/**
	 * 查询所有的最近一条的系统的异常数据的统计结果，进行发送短信或者邮件
	 * @return
	 * @throws Exception
	 */
	public List<MesAllProcessReq> findAllMesProcessGroupBySysCodeToSms() throws Exception;
}
