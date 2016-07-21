package com.wfj.exception.controller;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.dal.cond.EarlyWarnSysInfoCond;
import com.wfj.exception.dal.cond.MesEarlyWarnInfoCond;
import com.wfj.exception.dal.entity.MesEarlyWarnInfo;
import com.wfj.exception.dal.service.MesEarlyWarnService;
import com.wfj.exception.util.DataTableDto;
import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.MsgReturnDto;
import com.wfj.util.Page;

@Controller
@RequestMapping("/mesEarlyWarn")
public class MesEarlyWarnController {

	private static final Logger log=LoggerFactory.getLogger(MesEarlyWarnController.class);
	
	@Resource(name="mesEarlyWarnService")
	private MesEarlyWarnService mesEarlyWarnService;
	
	/**
	 * 添加预警配置信息
	 * @Title: insertMesEarlyWarn
	 * @author ZJHao
	 * @param mes
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 * @date 2015-8-18 下午3:25:36
	 */
	@RequestMapping(params="insertMesEarlyWarn")
	public @ResponseBody MsgReturnDto insertMesEarlyWarn(@ModelAttribute MesEarlyWarnInfo mes) {
		try {
			if(mes.getId()!=null){
                mesEarlyWarnService.updateMesEarlyWarn(mes);
            }else{
                mesEarlyWarnService.insertMesEarlyWarn(mes);
            }
		} catch (Exception e) {
			log.error("添加预警配置信息异常:{}", e.getMessage());
		}
		return new MsgReturnDto("", "");
	}
	
	@RequestMapping(params="showViewEarlyWarn")
	public String showViewEarlyWarn() throws Exception{
		return "view/jsp/mesEarlyWarn/showMesEarlyWarnInfo";
	}
	
	@RequestMapping(params="pageEarlyWarnInfoAjax")
	public @ResponseBody DataTableJson pageEarlyWarnInfoAjax(HttpServletRequest request, @ModelAttribute MesEarlyWarnInfoCond mesEarlyWarnInfo)  {
		log.info("sysInfo:{}",mesEarlyWarnInfo.toString());
		DataTableJson json = new DataTableJson();
		Page page = new Page();
		DataTableDto dataTableDto = new DataTableDto(request);
		page.setStart(dataTableDto.getiDisplayStart());
		page.setRows(dataTableDto.getiDisplayLength());
		page.setCond(mesEarlyWarnInfo);
		try {
			json = mesEarlyWarnService.findEarlyWarnInfoByPage(page);
			json.setsEcho(dataTableDto.getsEcho());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 通过系统id获取此系统下的所有异常规则信息
	 * @return
	 */
	@RequestMapping(params="getAllEarlyWarnBySysIdAjax")
	@ResponseBody
	public String getAllEarlyWarnBySysIdAjax(@ModelAttribute MesEarlyWarnInfo mesEarlyWarnInfo) {
		try {
			List<MesEarlyWarnInfo> list=mesEarlyWarnService.findMesEarlyInfoBySysId(mesEarlyWarnInfo);
			return JSONObject.toJSONString(list);
		} catch (Exception e) {
			log.error("通过系统id获取此系统下的所有异常规划信息异常:{}", e.getMessage());
		}
		return "";
	}
	
	@RequestMapping(params="deleteEarlyWarnInfo")
	public @ResponseBody MsgReturnDto deleteEarlyWarnInfo(@RequestParam Integer id) {
		try {
			log.info("删除预警配置信息,id:{}",id);
			mesEarlyWarnService.deleteMesEarlyWarn(id);
			return new MsgReturnDto("", "");
		} catch (Exception e) {
			log.error("删除预警配置信息异常:{}", e.getMessage());
		}
		return null;
	}
	
	/**
	 * 进入预警关联系统配置信息界面
	 * @Title: showEarlyWarnSysInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author ZJHao
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 * @date 2015-8-27 上午11:35:06
	 */
	@RequestMapping(params="showEarlyWarnSysInfo")
	public String showEarlyWarnSysInfo() throws Exception{
		return "view/jsp/mesEarlyWarn/earlyWarnSysInfo";
	}
	
	/**
	 * 根据预警id和系统id删除预警与系统中间表信息
	 * @Title: deleteEwSysByAllId
	 * @author ZJHao
	 * @return
	 * @return MsgReturnDto
	 * @throws
	 * @date 2015-8-27 上午11:36:23
	 */
	@RequestMapping(params="deleteEwSysByAllId")
	@ResponseBody
	public MsgReturnDto deleteEwSysByAllId(@ModelAttribute EarlyWarnSysInfoCond ewSys) {
		try {
			log.info("删除预警与系统关联信息,id:{}",ewSys.toString());
			mesEarlyWarnService.deleteEwSysRefByAllId(ewSys);
		} catch (Exception e) {
			log.error("删除预警与系统中间数据异常:{}", e.getMessage());
		}
		return new MsgReturnDto("", "");
	}
	@RequestMapping(params="insertMoreEwSys")
	@ResponseBody
	public MsgReturnDto insertMoreEwSys(@RequestParam Integer ewId,@RequestParam Integer [] sysIds)  {
		try {
			mesEarlyWarnService.insertMoreEwSys(ewId, sysIds);
		} catch (Exception e) {
			log.error("添加预警配置信息异常:{}", e.getMessage());
		}
		return new MsgReturnDto("", "");
	}
	
	
}
