package com.wfj.exception.controller;

import com.wfj.exception.dal.cond.MesProcessTrackCond;
import com.wfj.exception.dal.entity.MesProcessTrack;
import com.wfj.exception.dal.hbase.dao.HbaseDataService;
import com.wfj.exception.dal.service.MesProcessTrackService;
import com.wfj.exception.dal.vo.ClientExceptionReq;
import com.wfj.exception.util.DataTableDto;
import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.DateUtils;
import com.wfj.exception.util.MsgReturnDto;
import com.wfj.sysmanager.httpModel.SessionInfo;
import com.wfj.sysmanager.httpModel.User;
import com.wfj.sysmanager.util.ResourceUtil;
import com.wfj.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@RequestMapping("/mesProcessTrack")
@Controller
public class MesProcessTrackController {

	private static final Logger log = LoggerFactory.getLogger(MesProcessTrackController.class);
	@Resource(name = "mesProcessTrackService")
	private MesProcessTrackService mesProcessTrackService;
	@Resource(name= "hbaseDataService")
	private HbaseDataService hbaseDataService;
	/**
	 * 分页查询信息处理轨迹
	 * @Title: getMesProcessTrackList
	 * @author ZJHao
	 * @param mesProcessTrack
	 * @param request
	 * @return
	 * @return DataTableJson
	 * @throws
	 * @date 2015-8-30 下午10:45:01
	 */
	@RequestMapping(params = "getMesProcessTrackList")
	@ResponseBody
	public DataTableJson getMesProcessTrackList(@ModelAttribute MesProcessTrackCond mesProcessTrack, HttpServletRequest request) {
		DataTableJson json = new DataTableJson();
		Page page = new Page();
		DataTableDto dataTableDto = new DataTableDto(request);
		page.setStart(dataTableDto.getiDisplayStart());
		page.setRows(dataTableDto.getiDisplayLength());
		mesProcessTrack.setOrderBy(" created_time desc");
		page.setCond(mesProcessTrack);
		try {
			json = mesProcessTrackService.findMesProcessTrackByPage(page);
			json.setsEcho(dataTableDto.getsEcho());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 进入信息处理轨迹查询页面
	 * @Title: toMesProcessTrackView
	 * @author ZJHao
	 * @return
	 * @return String
	 * @throws
	 * @date 2015-8-30 下午10:48:02
	 */
	@RequestMapping(params = "toMesProcessTrackView")
	public String toMesProcessTrackView() {
		return "view/jsp/mesProcessTrack/showMesProcessTrackInfo";
	}

	/**
	 * 新增信息处理轨迹
	 * @Title: addProcessTrackView
	 * @author ZJHao
	 * @param mesProcessTrack
	 * @return
	 * @throws Exception
	 * @return MsgReturnDto
	 * @throws
	 * @date 2015-8-31 上午10:26:36
	 */
	@RequestMapping(params = "addProcessTrackView")
	@ResponseBody
	public MsgReturnDto addProcessTrackView(@ModelAttribute MesProcessTrack mesProcessTrack,HttpServletRequest request) throws Exception {
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
		assert sessionInfo != null;
		User user = sessionInfo.getUser();
		ClientExceptionReq req=new ClientExceptionReq();
		req.setErrId(mesProcessTrack.getErrId());
		req.setProcessStatus(mesProcessTrack.getProcessStatus());
		log.info("修改hbase下的异常信息,req:{}",req.toString());
		String result=hbaseDataService.updateOneMes(req);
		log.info("修改结束,result:{}",result); 
		mesProcessTrack.setCreatedTime(DateUtils.getCurrentDate());
		log.info("开始插入信息处理轨迹,param:{}", mesProcessTrack.toString());
		mesProcessTrack.setUserId(user.getId());
		mesProcessTrack.setSysCode(mesProcessTrack.getErrId().substring(2,4));
		mesProcessTrackService.addMesProcessTrack(mesProcessTrack);
		log.info("插入信息处理轨迹完成...");
		return new MsgReturnDto("", "");
	}

	/**
	 * 根据errId查询异常信息处理轨迹的列表
	 */
	@RequestMapping(params = "getMesProcessTrackListByErrId")
	@ResponseBody
	public DataTableJson getMesProcessTrackListByErrId(HttpServletRequest request, @ModelAttribute MesProcessTrackCond mesProcessTrackCond) throws Exception {
		DataTableJson json = new DataTableJson();
		Page page = new Page();
		DataTableDto dataTableDto = new DataTableDto(request);
		page.setStart(dataTableDto.getiDisplayStart());
		page.setRows(dataTableDto.getiDisplayLength());
		mesProcessTrackCond.setOrderBy(" created_time desc");
		page.setCond(mesProcessTrackCond);
		try {
			log.info("根据errId查询异常信息出库轨迹,param:{}", page.toString());
			json = mesProcessTrackService.getMesProcessTrackListByErrId(page);
			json.setsEcho(dataTableDto.getsEcho());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("根据errId查询异常信息处理轨迹结束,result:{}", json.toString());
		return json;
	}
}
