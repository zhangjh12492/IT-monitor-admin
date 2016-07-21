package com.wfj.exception.dal.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.common.HbasePage;
import com.wfj.exception.dal.cond.MesAllProcessReqCond;
import com.wfj.exception.dal.entity.MesProcessTrack;
import com.wfj.exception.dal.entity.SysInfo;
import com.wfj.exception.dal.hbase.dao.HbaseDataService;
import com.wfj.exception.dal.service.ExceptionInfoService;
import com.wfj.exception.dal.service.MesProcessTrackService;
import com.wfj.exception.dal.service.SysInfoService;
import com.wfj.exception.dal.vo.ClientExceptionReq;
import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.DateUtils;
import com.wfj.exception.util.StringUtils;
import com.wfj.sysmanager.httpModel.SessionInfo;
import com.wfj.sysmanager.httpModel.User;
import com.wfj.sysmanager.util.ResourceUtil;
import com.wfj.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service("exceptionInfoService")
public class ExceptionInfoServiceImpl implements ExceptionInfoService {

	private static final Logger log = LoggerFactory.getLogger(ExceptionInfoServiceImpl.class);
	@Resource(name = "hbaseDataService")
	private HbaseDataService hbaseDataService;
	@Resource
	private SysInfoService sysInfoService;
	@Autowired
	private MesProcessTrackService mesProcessTrackService;

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
	@Override
	public DataTableJson showMesListFromChartOneHour( MesAllProcessReqCond mesAllReqCond,HttpServletRequest request,HbasePage hbasePage) throws Exception {
		log.info("mesAllReqCond:{},methodName:{},page:{}", mesAllReqCond.toString());
		hbasePage.setObject(mesAllReqCond);
		List listReq = hbaseDataService.findExceptionListOneHour(hbasePage);
		DataTableJson json = new DataTableJson();
		if(listReq.size()==(hbasePage.getPageSize())){
			String lastReqStr= listReq.get(hbasePage.getPageSize()-1).toString();
			ClientExceptionReq lastReq=JSONObject.parseObject(lastReqStr,ClientExceptionReq.class);
			json.setHasNext(true);
			json.setStartRow(lastReq.getErrId());
			listReq.remove(listReq.size()-1);
		}
		json.setAaData(listReq);
		log.info("分页查询系统信息结束,count:{}", listReq.size());
		return json;
		
	}
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
//	@Override
//	public DataTableJson showMessListByUserNoProcess(Page page, HbasePage hbasePage,HttpServletRequest request,MesAllProcessReqCond mesAllReqCond) throws Exception{
//		log.info("mesAllReqCond:{},page:{}", hbasePage.toString(),  JSONObject.toJSONString(page));
//		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
//        assert sessionInfo != null;
//        User user = sessionInfo.getUser();
//        List<SysInfo> sysInfoList=sysInfoService.selectSysByUser(user);
//        if(sysInfoList!=null&&sysInfoList.size()>0){
//        	String code="";
//            for(SysInfo sysInfo:sysInfoList){
//            	code+=sysInfo.getSysCode()+",";
//            }
//            if(code.length()>0)
//            	code=code.substring(0,code.length()-1);
//            mesAllReqCond.setCode(code);
//            hbasePage.setObject(mesAllReqCond);
////            hbasePage.setStartRow((String) request.getSession().getAttribute("showMessListByUserNoProcess_startRow"));
//            log.info("查询当前登陆的用户的所有未处理的异常信息:{},page:{}", hbasePage.toString(),  JSONObject.toJSONString(page));
//    		List listReq = hbaseDataService.showMessListByUserNoProcess(hbasePage);
//    		DataTableJson json = new DataTableJson();
////    		json.setiTotalRecords(listReq.size());
//    		String clientStr= listReq.get(listReq.size()-1).toString();
//    		ClientExceptionReq clientReq=JSONObject.parseObject(clientStr,ClientExceptionReq.class);
////    		json.setStartRow(clientReq.getErrId());
//    		json.setiTotalRecords(listReq.size());
//    		json.setAaData(pageClientReq(listReq, page));
//    		request.getSession().setAttribute("showMessListByUserNoProcess_startRow", clientReq.getErrId());
//
//    		log.info("查询当前登陆的用户的所有未处理的异常信息,count:{}", listReq.size());
//    		return json;
//        }else{
//        	return null;
//        }
//
//
//	}

	/**
	 * 查询当前登陆用户下的所有要处理的异常信息
	 * @Title: showMesListByUser
	 * @author ZJHao
	 * @param mesAllReqCond
	 * @return
	 * @throws Exception
	 * @return DataTableJson
	 * @throws
	 * @date 2015-8-29 下午5:23:47
	 */
	@Override
	public DataTableJson showMessListByUserNoProcess( HbasePage hbasePage,HttpServletRequest request,MesAllProcessReqCond mesAllReqCond) throws Exception{
		log.info("mesAllReqCond:{},page:{}", hbasePage.toString());
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
		assert sessionInfo != null;
		User user = sessionInfo.getUser();
		List<SysInfo> sysInfoList=sysInfoService.selectSysByUser(user);
		if(sysInfoList!=null&&sysInfoList.size()>0){
			String code="";
			for(SysInfo sysInfo:sysInfoList){
				code+=sysInfo.getSysCode()+",";
			}
			if(code.length()>0)
				code=code.substring(0,code.length()-1);
			mesAllReqCond.setCode(code);
			hbasePage.setObject(mesAllReqCond);
			log.info("查询当前登陆的用户的所有未处理的异常信息:{},page:{}", hbasePage.toString());
			List listReq = hbaseDataService.showMessListByUserNoProcess(hbasePage);
			DataTableJson json = new DataTableJson();
			if(listReq.size()==(hbasePage.getPageSize())){
				String lastReqStr= listReq.get(hbasePage.getPageSize()-1).toString();
				ClientExceptionReq lastReq=JSONObject.parseObject(lastReqStr,ClientExceptionReq.class);
				json.setHasNext(true);
				json.setStartRow(lastReq.getErrId());
				listReq.remove(listReq.size()-1);
			}
			json.setAaData(listReq);
			log.info("查询当前登陆的用户的所有未处理的异常信息,count:{}", listReq.size());
			return json;
		}else{
			return null;
		}


	}
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
	@Override
	public ClientExceptionReq findExceptionByErrId(String errId) throws Exception {
		log.info("errId:{}", errId);
		ClientExceptionReq clientReq = hbaseDataService.selectById(errId);
		log.info("根据errId获取单条异常信息结束,result:{}", clientReq.toString());
		return clientReq;
	}
	/**
	 * 修改单条信息
	 * @Title: updateOneMes
	 * @author ZJHao
	 * @param req
	 * @throws Exception
	 * @throws
	 * @date 2015-8-30 下午6:12:50
	 */
	@Override
	public void updateOneMes(ClientExceptionReq req,HttpServletRequest request) throws Exception {

		log.info("修改hbase下的异常信息,req:{}", req.toString());
		String result=hbaseDataService.updateOneMes(req);
		log.info("修改结束,result:{}", result);
		log.info("开始插入修改轨迹信息表...");

		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
		assert sessionInfo != null;
		User user = sessionInfo.getUser();
		MesProcessTrack mesTrack=new MesProcessTrack();
		mesTrack.setSysCode(req.getErrId().substring(2,4));
		mesTrack.setErrId(req.getErrId());
		mesTrack.setUserId(user.getId());
		mesTrack.setProcessStatus(req.getProcessStatus());
		mesTrack.setCreatedTime(DateUtils.getCurrentDate());
		mesTrack.setDescription("修改信息操作状态为正在处理");
		mesProcessTrackService.addMesProcessTrack(mesTrack);
		log.info("插入修改轨迹信息表完成...");
	}
	
	/**
	 * 修改多条信息
	 * @Title: updateOneMes
	 * @author ZJHao
	 * @param req
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 * @date 2015-8-30 下午5:48:47
	 */
	@Override
	public String updateMoreMesStatus(ClientExceptionReq req,HttpServletRequest request) throws Exception{
		log.info("开始修改多条异常信息的状态");
		String result=hbaseDataService.updateMoreMesStatus(req);
		log.info("修改多条异常信息的状态成功!");
		if(StringUtils.isNotBlank(req.getErrId())){
			SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
			assert sessionInfo != null;
			User user = sessionInfo.getUser();

			String [] errIds=req.getErrId().split(",");
			List<MesProcessTrack> mesProcessTracks=new ArrayList<>();
			for(int i=0;i<errIds.length;i++){
				MesProcessTrack mesTrack=new MesProcessTrack();
				mesTrack.setSysCode(errIds[i].substring(2, 4));
				mesTrack.setErrId(errIds[i]);
				mesTrack.setUserId(user.getId());
				mesTrack.setProcessStatus(req.getProcessStatus());
				mesTrack.setCreatedTime(DateUtils.getCurrentDate());
				mesTrack.setDescription("修改信息操作状态为正在处理");
				mesProcessTracks.add(mesTrack);
			}
			mesProcessTrackService.addMoreMesProcessTrack(mesProcessTracks);
			log.info("批量插入异常信息修改状态成功!");
		}

		return result;
	}
	
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
	public List<ClientExceptionReq> selectMessageInfoForSysMonitorShow(MesAllProcessReqCond mesAllProcessReqCound,HbasePage hbasePage) throws Exception{
		hbasePage.setObject(mesAllProcessReqCound);
		List<ClientExceptionReq> listClientException=hbaseDataService.selectMessageInfoForSysMonitorShow(hbasePage);
		return listClientException;
	}

	public List pageClientReq(List<ClientExceptionReq> reqList, Page page) {
		List reqs = new ArrayList();
		Integer start = page.getStart();
		Integer row = page.getRows();
		Integer lastPage = reqList.size() / row;
		if (reqList != null && reqList.size() > 0) {
			if(row>(reqList.size()-start-1)){
				row=reqList.size()-start;
			}
			for (int i = 0; i < row; i++) {
				if(reqList.get(start + i)!=null){
					reqs.add(reqList.get(start + i));
				}
			}

		}
		return reqs;
	}

	

}
