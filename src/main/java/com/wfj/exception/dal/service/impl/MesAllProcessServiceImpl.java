package com.wfj.exception.dal.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.wfj.exception.common.ErrProcessStatusEnum;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfj.exception.common.DateTypeEnum;
import com.wfj.exception.dal.cond.MesAllProcessReqCond;
import com.wfj.exception.dal.entity.MesAllProcessReq;
import com.wfj.exception.dal.entity.SysInfo;
import com.wfj.exception.dal.service.MesAllProcessService;
import com.wfj.exception.dal.service.SysInfoService;
import com.wfj.exception.util.DateUtils;
import com.wfj.exception.util.StringUtils;
import com.wfj.persist.SimpleGenericDAO;
import com.wfj.sysmanager.httpModel.SessionInfo;
import com.wfj.sysmanager.httpModel.User;
import com.wfj.sysmanager.util.ResourceUtil;

@Service("mesAllProcessService")
public class MesAllProcessServiceImpl implements MesAllProcessService {

	private static final Logger log=LoggerFactory.getLogger(MesAllProcessServiceImpl.class);
	private SimpleGenericDAO<MesAllProcessReq, Integer> mesAllProcessDao;
	@Resource
	private SysInfoService sysInfoService;
	
	/**
	 * 根据小时查询结果信息展示折线图
	 * @Title: selectOneHour
	 * @author ZJHao
	 * @param mesAllProcessReqCond
	 * @return
	 * @throws Exception 
	 * @throws
	 * @date 2015-8-29 下午1:45:30
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MesAllProcessReq> selectOneHourLine(MesAllProcessReqCond mesAllProcessReqCond) throws Exception {
		List<MesAllProcessReq> list=null;
		if(StringUtils.isBlank(mesAllProcessReqCond.getDateType())){
			mesAllProcessReqCond.setDateType(DateTypeEnum.WEEK.getCode());
		}
		mesAllProcessReqCond=setMesAllReqCondStartAndEndTime(mesAllProcessReqCond);
		if(mesAllProcessReqCond.getDateType().equals(DateTypeEnum.HOUR.getCode())){	//小时
			log.info("根据小时查询数据,MesAllprocessReqCond:{}",mesAllProcessReqCond.toString());
			list=mesAllProcessDao.findListByStatementCond("selectOneHourLine", mesAllProcessReqCond);
		}else if(mesAllProcessReqCond.getDateType().equals(DateTypeEnum.DAY.getCode())){	//天
			log.info("根据天查询数据,MesAllprocessReqCond:{}",mesAllProcessReqCond.toString());
			list=mesAllProcessDao.findListByStatementCond("selectOneDayLine", mesAllProcessReqCond);
		}else if(mesAllProcessReqCond.getDateType().equals(DateTypeEnum.WEEK.getCode())){	//周
			log.info("根据周查询数据,MesAllprocessReqCond:{}",mesAllProcessReqCond.toString());
			list=mesAllProcessDao.findListByStatementCond("selectOneWeekLine", mesAllProcessReqCond);
		}else if(mesAllProcessReqCond.getDateType().equals(DateTypeEnum.MONTH.getCode())){	//一个月
			log.info("根据月查询数据,MesAllprocessReqCond:{}",mesAllProcessReqCond.toString());
			list=mesAllProcessDao.findListByStatementCond("selectOneMonthLine", mesAllProcessReqCond);
		}else if(mesAllProcessReqCond.getDateType().equals(DateTypeEnum.QUARTER.getCode())){	//一个季度
			log.info("根据季度查询数据,MesAllprocessReqCond:{}",mesAllProcessReqCond.toString());
			list=mesAllProcessDao.findListByStatementCond("selectOneQuarterLine", mesAllProcessReqCond);
		}else if(mesAllProcessReqCond.getDateType().equals(DateTypeEnum.HALFYEAR.getCode())){	//半年
			log.info("根据半年查询数据,MesAllprocessReqCond:{}",mesAllProcessReqCond.toString());
			list=mesAllProcessDao.findListByStatementCond("selectHalfYearLine", mesAllProcessReqCond);
		}else if(mesAllProcessReqCond.getDateType().equals(DateTypeEnum.YEAR.getCode())){	//一年
			log.info("根据年查询数据,MesAllprocessReqCond:{}",mesAllProcessReqCond.toString());
			list=mesAllProcessDao.findListByStatementCond("selectOneYearLine", mesAllProcessReqCond);
		}
		return list;
	}
	/**
	 * 根据小时查询结果信息展示饼图
	 * @Title: selectOneHour
	 * @author ZJHao
	 * @param mesAllProcessReqCond
	 * @return
	 * @throws Exception 
	 * @throws
	 * @date 2015-8-29 下午1:45:30
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MesAllProcessReq> selectOneHourPie(MesAllProcessReqCond mesAllProcessReqCond) throws Exception {
		List<MesAllProcessReq> list=null;
		if(StringUtils.isBlank(mesAllProcessReqCond.getDateType())){
			mesAllProcessReqCond.setDateType(DateTypeEnum.WEEK.getCode());
		}
		mesAllProcessReqCond=setMesAllReqCondStartAndEndTime(mesAllProcessReqCond);
		if(mesAllProcessReqCond.getDateType().equals(DateTypeEnum.HOUR.getCode())){	//小时
			log.info("根据小时查询数据,MesAllprocessReqCond:{}",mesAllProcessReqCond.toString());
			list=mesAllProcessDao.findListByStatementCond("selectOneHourPie", mesAllProcessReqCond);
		}else if(mesAllProcessReqCond.getDateType().equals(DateTypeEnum.DAY.getCode())){	//天
			log.info("根据天查询数据,MesAllprocessReqCond:{}",mesAllProcessReqCond.toString());
			list=mesAllProcessDao.findListByStatementCond("selectOneDayPie", mesAllProcessReqCond);
		}else if(mesAllProcessReqCond.getDateType().equals(DateTypeEnum.WEEK.getCode())){	//周
			log.info("根据周查询数据,MesAllprocessReqCond:{}",mesAllProcessReqCond.toString());
			list=mesAllProcessDao.findListByStatementCond("selectOneWeekPie", mesAllProcessReqCond);
		}else if(mesAllProcessReqCond.getDateType().equals(DateTypeEnum.MONTH.getCode())){	//一个月
			log.info("根据月查询数据,MesAllprocessReqCond:{}",mesAllProcessReqCond.toString());
			list=mesAllProcessDao.findListByStatementCond("selectOneMonthPie", mesAllProcessReqCond);
		}else if(mesAllProcessReqCond.getDateType().equals(DateTypeEnum.QUARTER.getCode())){	//一个季度
			log.info("根据季度查询数据,MesAllprocessReqCond:{}",mesAllProcessReqCond.toString());
			list=mesAllProcessDao.findListByStatementCond("selectOneQuarterPie", mesAllProcessReqCond);
		}else if(mesAllProcessReqCond.getDateType().equals(DateTypeEnum.HALFYEAR.getCode())){	//半年
			log.info("根据半年查询数据,MesAllprocessReqCond:{}",mesAllProcessReqCond.toString());
			list=mesAllProcessDao.findListByStatementCond("selectHalfYearPie", mesAllProcessReqCond);
		}else if(mesAllProcessReqCond.getDateType().equals(DateTypeEnum.YEAR.getCode())){	//一年
			log.info("根据年查询数据,MesAllprocessReqCond:{}",mesAllProcessReqCond.toString());
			list=mesAllProcessDao.findListByStatementCond("selectOneYearPie", mesAllProcessReqCond);
		}
		return list;
	}
	
	private MesAllProcessReqCond setMesAllReqCondStartAndEndTime(MesAllProcessReqCond mesAllReqCond){
		Date date=new Date();
		mesAllReqCond.setEndTime(DateUtils.format(date, DateUtils.YYDDMMHHMMSS));
		if(mesAllReqCond.getDateType().equals(DateTypeEnum.HOUR.getCode())){
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.HOUR, -1);
			mesAllReqCond.setStartTime(DateUtils.format(calendar.getTime(), DateUtils.YYDDMMHHMMSS));
		}else if(mesAllReqCond.getDateType().equals(DateTypeEnum.DAY.getCode())){
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -1);
			mesAllReqCond.setStartTime(DateUtils.format(calendar.getTime(), DateUtils.YYDDMMHHMMSS));
		}else if(mesAllReqCond.getDateType().equals(DateTypeEnum.WEEK.getCode())){
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.WEDNESDAY, -1);
			mesAllReqCond.setStartTime(DateUtils.format(calendar.getTime(), DateUtils.YYDDMMHHMMSS));
		}else if(mesAllReqCond.getDateType().equals(DateTypeEnum.MONTH.getCode())){
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, -1);
			mesAllReqCond.setStartTime(DateUtils.format(calendar.getTime(), DateUtils.YYDDMMHHMMSS));
		}else if(mesAllReqCond.getDateType().equals(DateTypeEnum.QUARTER.getCode())){
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, -3);
			mesAllReqCond.setStartTime(DateUtils.format(calendar.getTime(), DateUtils.YYDDMMHHMMSS));
		}else if(mesAllReqCond.getDateType().equals(DateTypeEnum.HALFYEAR.getCode())){
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, -6);
			mesAllReqCond.setStartTime(DateUtils.format(calendar.getTime(), DateUtils.YYDDMMHHMMSS));
		}else if(mesAllReqCond.getDateType().equals(DateTypeEnum.YEAR.getCode())){
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.YEAR, -1);
			mesAllReqCond.setStartTime(DateUtils.format(calendar.getTime(), DateUtils.YYDDMMHHMMSS));
		}
		return mesAllReqCond;
	}
	@Override
	public List<MesAllProcessReq> selectProcessMesGroupBySysCode(HttpServletRequest request) throws Exception{
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
        assert sessionInfo != null;
        User user = sessionInfo.getUser();
        List<MesAllProcessReq> list=new ArrayList<MesAllProcessReq>();
        List<SysInfo> sysList=sysInfoService.selectSysByUser(user);
		String processStatus=request.getParameter("processStatus");
        for(SysInfo sysInfo:sysList){
        	if(sysInfo.getSysCode()!=null){
        		Map<String, String> params=new HashMap<String, String>();
        		params.put("code", sysInfo.getSysCode());
				params.put("processStatus",processStatus);
        		MesAllProcessReq mesAllProcessReq=(MesAllProcessReq) mesAllProcessDao.findByStatementCond("selectOneTopProcessMesBySysCode",params );
        		if(mesAllProcessReq!=null)
        			list.add(mesAllProcessReq);
        	}
        }
		return list;
	}
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
	public List<MesAllProcessReq> selectBusiProcessMesBySysCode(String sysCode,String processStatus) throws Exception{
		Map<String, String> params=new HashMap<String, String>();
		params.put("code", sysCode);
		params.put("processStatus", processStatus);
		List<MesAllProcessReq> listMesAll=mesAllProcessDao.findListByStatementCond("selectBusiProcessMesBySysCode", params);
		return listMesAll;
	}
	
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
	@Override
	public MesAllProcessReq selectOneTopProcessMesBySysCode(Map<String, String> params) throws Exception{
		MesAllProcessReq mesAllProcessReq=(MesAllProcessReq) mesAllProcessDao.findByStatementCond("selectOneTopProcessMesBySysCode", params);
		return mesAllProcessReq;
	}
	/**
	 * 查询所有的最近一条的系统的异常数据的统计结果，进行发送短信或者邮件
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<MesAllProcessReq> findAllMesProcessGroupBySysCodeToSms() throws Exception{
		MesAllProcessReq mesAllProcessReq=new MesAllProcessReq();
		mesAllProcessReq.setProcessStatus(ErrProcessStatusEnum.UNDISPOSED.getCode());
		List<MesAllProcessReq> mesAllProcessReqList=mesAllProcessDao.findListByStatementCond("findAllMesProcessGroupBySysCodeToSms",mesAllProcessReq);
		return mesAllProcessReqList;
	}
	
	@Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.mesAllProcessDao = new SimpleGenericDAO<MesAllProcessReq, Integer>(sqlSessionTemplate, MesAllProcessReq.class);
    }
	
}
