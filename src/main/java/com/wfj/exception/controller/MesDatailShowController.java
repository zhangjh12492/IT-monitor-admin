package com.wfj.exception.controller;

import com.wfj.exception.common.HbasePage;
import com.wfj.exception.dal.cond.MesAllProcessReqCond;
import com.wfj.exception.dal.service.ExceptionInfoService;
import com.wfj.exception.util.DataTableJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller("/mesDatail")
public class MesDatailShowController {

	private static final Logger log= LoggerFactory.getLogger(MesDatailShowController.class);
	@Resource
	private ExceptionInfoService exceptionInfoService;
	
	/**
	 * 进入用户的异常展示界面
	 * @Title: intoMesList
	 * @author ZJHao
	 * @return
	 * @return String
	 * @throws
	 * @date 2015年9月23日 下午4:27:02
	 */
	@RequestMapping(params="intoMesList")
	public String intoMesList(Model model,@ModelAttribute MesAllProcessReqCond mesAllProcessReq){
		model.addAttribute("mesAllReqCond", mesAllProcessReq);
		return "/view/jsp/mesDetailShow/mesListShowForUserLogin";
	}
	
//	/**
//	 * 从hbase中查询当前登陆的用户的所有的异常信息列表
//	 * @Title: getUserMesDetailList
//	 * @author ZJHao
//	 * @return
//	 * @return String
//	 * @throws
//	 * @date 2015年9月23日 下午4:40:41
//	 */
//	@RequestMapping(params="getUserMesDetailList")
//	@ResponseBody
//	public DataTableJson getUserMesDetailList(HttpServletRequest request, @ModelAttribute MesAllProcessReqCond mesAllReqCond,@ModelAttribute HbasePage hbasePage){
//		DataTableJson json = new DataTableJson();
//		Page page = new Page();
//		DataTableDto dataTableDto = new DataTableDto(request);
//		System.out.println(request.getParameter("startRow"));
//		page.setStart(dataTableDto.getiDisplayStart());
//		page.setRows(dataTableDto.getiDisplayLength());
//		try {
//			json = exceptionInfoService.showMessListByUserNoProcess(page, hbasePage,request,mesAllReqCond);
//			json.setsEcho(dataTableDto.getsEcho());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return json;
//	}
	/**
	 * 从hbase中查询当前登陆的用户的所有的异常信息列表
	 * @Title: getUserMesDetailList
	 * @author ZJHao
	 * @return
	 * @return String
	 * @throws
	 * @date 2015年9月23日 下午4:40:41
	 */
	@RequestMapping(params="getUserMesDetailList")
	@ResponseBody
	public DataTableJson getUserMesDetailList(HttpServletRequest request, @ModelAttribute MesAllProcessReqCond mesAllReqCond,@ModelAttribute HbasePage hbasePage){
		DataTableJson json = null;
		try {
			json= exceptionInfoService.showMessListByUserNoProcess(hbasePage,request,mesAllReqCond);
			return json;
		} catch (Exception e) {
			log.error("从hbase中查询当前登陆的用户的所有的异常信息列表出错:{}",e.getMessage());
		}
		return json;
	}
}
