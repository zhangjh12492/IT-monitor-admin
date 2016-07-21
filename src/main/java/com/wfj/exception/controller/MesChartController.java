package com.wfj.exception.controller;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.common.HbasePage;
import com.wfj.exception.common.RequestResult;
import com.wfj.exception.dal.cond.MesAllProcessReqCond;
import com.wfj.exception.dal.entity.MesAllProcessReq;
import com.wfj.exception.dal.service.ExceptionInfoService;
import com.wfj.exception.dal.service.MesAllProcessService;
import com.wfj.exception.dal.vo.ClientExceptionReq;
import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.MsgReturnDto;
import com.wfj.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/mesChart")
public class MesChartController {

	private static final Logger log=LoggerFactory.getLogger(MesChartController.class);
	
	@Resource(name="mesAllProcessService")
	private MesAllProcessService mesAllProcessService;
	@Resource(name="exceptionInfoService")
	private ExceptionInfoService exceptionInfoService;
	
	/**
	 * 查询一小时内的展示数据折线图
	 * @Title: selectOneHour
	 * @author ZJHao
	 * @param mesAllReqCond
	 * @return
	 * @throws Exception
	 * @return List<MesAllProcessReq>
	 * @throws
	 * @date 2015-8-29 下午1:59:45
	 */
	@RequestMapping(params="selectOneHourLine")
	@ResponseBody
	public String selectOneHourLine(@ModelAttribute MesAllProcessReqCond mesAllReqCond) {
		try {
			log.info("查询{}内数据列表,展示折线图,mesAllreqCond:{}",mesAllReqCond.getDateType(),mesAllReqCond.toString());
			List<MesAllProcessReq> list=mesAllProcessService.selectOneHourLine(mesAllReqCond);
			log.info("查询{}内数据列表结束,list:{}",mesAllReqCond.getDateType(),list.toString());
			return JSONObject.toJSONString(list);
		} catch (Exception e) {
			log.error("查询一小时内的展示数据折线图异常:{}",e.getMessage());
		}
		return "";
	}
	
	
	/**
	 * 查询周期内的展示数据饼图
	 * @Title: selectOneHourPie
	 * @author ZJHao
	 * @param mesAllReqCond
	 * @return
	 * @throws Exception
	 * @return List<MesAllProcessReq>
	 * @throws
	 * @date 2015-8-29 下午1:59:45
	 */
	@RequestMapping(params="selectOneHourPie")
	@ResponseBody
	public String selectOneHourPie(@ModelAttribute MesAllProcessReqCond mesAllReqCond) {
		try {
			log.info("查询周期数据列表,展示饼图,mesAllreqCond:{}",mesAllReqCond.toString());
			List<MesAllProcessReq> list=mesAllProcessService.selectOneHourPie(mesAllReqCond);
			log.info("查询周期数据列表结束,list:{}",list.toString());
			return JSONObject.toJSONString(list);
		} catch (Exception e) {
			log.error("查询周期内的展示数据饼图异常:{}", e.getMessage());
		}
		return "";
	}
	
	/**
	 * 根据统计后信息获取信息列表
	 * @Title: showMessListFromChart
	 * @author ZJHao
	 * @param request
	 * @param mesAllReqCond
	 * @return
	 * @throws Exception
	 * @return DataTableJson
	 * @throws
	 * @date 2015-8-29 下午5:33:34
	 */
	@RequestMapping(params="showMessListFromChart")
	@ResponseBody
	public DataTableJson showMessListFromChart(HttpServletRequest request,@ModelAttribute MesAllProcessReqCond mesAllReqCond,@ModelAttribute HbasePage hbasePage) {
		log.info("showMessListFromChart,mesAllReqCond:{}",mesAllReqCond.toString());
		DataTableJson json = new DataTableJson();
		Page page = new Page();

		try {
			json = exceptionInfoService.showMesListFromChartOneHour(mesAllReqCond,request,hbasePage);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 从图表链接进入异常详细信息展示页面
	 * @Title: chartToMesListView
	 * @author ZJHao
	 * @param mesAllReqCond
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 * @date 2015-8-29 下午7:26:51
	 */
	@RequestMapping(params="chartToMesListView")
	public String chartToMesListView(Model model,@ModelAttribute MesAllProcessReqCond mesAllReqCond) throws Exception{
		model.addAttribute("mesAllReqCond",mesAllReqCond);
		return "view/jsp/messageListFromChart";
	}
	
	/**
	 * 根据errId获取单条异常信息
	 * @Title: findMessByErrId
	 * @author ZJHao
	 * @param errId
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 * @date 2015-8-29 下午9:51:25
	 */
	@RequestMapping(params="findMessByErrId")
	@ResponseBody
	public ClientExceptionReq findMessByErrId(@RequestParam String errId) {
		try {
			ClientExceptionReq req=exceptionInfoService.findExceptionByErrId(errId);
			return req;
		} catch (Exception e) {
			log.error("根据errId获取单条异常异常:{}", e.getMessage());
		}
		return null;
	}
	/**
	 * 修改一条异常信息的内容
	 * @Title: updateMesOneStatus
	 * @author ZJHao
	 * @return
	 * @return MsgReturnDto
	 * @throws
	 * @date 2015-8-30 下午5:44:33
	 */
	@RequestMapping(params="updateMesOneStatus")
	@ResponseBody
	public MsgReturnDto updateMesOneStatus(@ModelAttribute ClientExceptionReq clientReq,HttpServletRequest request) {
		try {
			exceptionInfoService.updateOneMes(clientReq,request);
		} catch (Exception e) {
			log.error("修改一条异常信息报错:{}", e.getMessage());
		}
		return new MsgReturnDto("", "");
	}
	
	/**
	 * 修改多条异常信息的状态
	 * @Title: updateMesOneStatus
	 * @author ZJHao
	 * @return
	 * @return MsgReturnDto
	 * @throws
	 * @date 2015-8-30 下午5:44:33
	 */
	@RequestMapping(params="updateMoreMesStatus")
	@ResponseBody
	public MsgReturnDto updateMoreMesStatus(@ModelAttribute ClientExceptionReq clientReq,HttpServletRequest request) {

		try {
			String result=exceptionInfoService.updateMoreMesStatus(clientReq,request);
			RequestResult<String> reqRes=JSONObject.parseObject(result,RequestResult.class);
			if(reqRes.isSuccess()){
                return new MsgReturnDto("", "");
            }else{
                return new MsgReturnDto("false", "有误");
            }
		} catch (Exception e) {
			log.error("修改多条异常信息的状态异常:{}",e.getMessage());
		}
		return null;

	}
}
