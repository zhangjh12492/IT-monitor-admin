package com.wfj.exception.controller;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.dal.cond.BusiInfoCond;
import com.wfj.exception.dal.entity.BusiInfo;
import com.wfj.exception.dal.entity.SysInfo;
import com.wfj.exception.dal.service.BusiInfoService;
import com.wfj.exception.dal.service.SysInfoService;
import com.wfj.exception.dal.service.UserInfoService;
import com.wfj.exception.dal.util.DataTableParam;
import com.wfj.exception.dal.util.LimitPageMap;
import com.wfj.exception.util.DataTableDto;
import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.HttpUtil;
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
import java.util.Map;

/**
 * 业务处理类
 */
@Controller
@RequestMapping("/busi")
public class BusiOperateController {

	private static final Logger log = LoggerFactory.getLogger(BusiOperateController.class);

	@Resource(name = "sysInfoService")
	private SysInfoService sysInfoService;
	@Resource(name = "busiInfoService")
	private BusiInfoService busiInfoService;
	@Resource(name = "userInfoService")
	private UserInfoService userInfoService;

	@RequestMapping("/showSysInfo")
	public String showSysInfo(Model model, @RequestParam String sysCode) throws Exception {
		return "/view/jsp/sysOperate/showSysInfo";
	}

	@RequestMapping("/pageSysInfoAjax")
	public @ResponseBody String pageSysInfoAjax(HttpServletRequest request, @ModelAttribute DataTableParam dataTable){
		Map map = HttpUtil.getParameterMap(request.getParameterMap());
		try {
			log.info("查询系统信息:map:{},  dataTable:", map, JSONObject.toJSONString(dataTable));
			DataTableDto dtd = new DataTableDto(request);
			log.info(JSONObject.toJSONString(dtd));
			LimitPageMap<SysInfo> pageMap = new LimitPageMap<SysInfo>(dtd.getiDisplayStart(), dtd.getiDisplayLength());
			pageMap.setT(new SysInfo());
			pageMap.getT().setSysCode(((String) map.get("sysCode")) == null ? "" : (String) map.get("sysCode"));
			pageMap.setStartLimit(Integer.valueOf(dataTable.getiDisplayStart()));
			pageMap.setPageCount(Integer.valueOf(dataTable.getiDisplayLength()));
			//		pageMap=sysInfoService.findSysInfoByPage(pageMap);

			map.put("iTotalRecords", pageMap.getAllCount() + "");
			map.put("iTotalDisplayRecords", pageMap.getAllCount() + "");
			map.put("aaData", pageMap.getPageContent());
			log.info("map:{}", JSONObject.toJSONString(map));
		} catch (Exception e) {
			log.error("查询系统信息异常:{}",e.getMessage());
		}
		return JSONObject.toJSONString(map);
	}

	/**
	 * 添加系统信息
	 * @Title: addSysInfo
	 * @author ZJHao
	 * @param sysInfo
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 * @date 2015年9月22日 下午5:47:10
	 */
	@RequestMapping("/addSysInfo")
	public @ResponseBody String addSysInfo(@ModelAttribute SysInfo sysInfo) {
		log.info("开始插入系统信息,param:{}", sysInfo.toString());
		String id =null;
		try {
			id=sysInfoService.insertSysInfo(sysInfo);
			log.info("插入系统数据结束,id:{}", id);
		} catch (Exception e) {
			log.error("插入系统信息异常:{}",e.getMessage());
		}
		return id;

	}

	/**
	 * 修改系统信息
	 * @Title: updateSysInfo
	 * @author ZJHao
	 * @param sysInfo
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 * @date 2015年9月22日 下午5:47:38
	 */
	@RequestMapping("/updateSysInfo")
	public @ResponseBody String updateSysInfo(@ModelAttribute SysInfo sysInfo)  {
		try {
			log.info("修改系统信息,param:{}", sysInfo.toString());
			sysInfoService.updateSysInfo(sysInfo);
			log.info("修改数据结束,id:{}", sysInfo.getId());
			return "true";
		} catch (Exception e) {
			log.error("修改系统信息异常:{}",e.getMessage());
		}
		return "false";
	}

	/**
	 * 删除系统信息通过系统id
	 * @Title: deleteSysInfo
	 * @author ZJHao
	 * @param id
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 * @date 2015年9月22日 下午5:47:47
	 */
	@RequestMapping("/deleteSysInfo")
	public @ResponseBody String deleteSysInfo(@RequestParam int id)  {
		try {
			log.info("删除系统信息,id:{}", id);
			sysInfoService.deleteSysInfo(id);
			return "true";
		} catch (Exception e) {
			log.error("删除系统信息异常:{}", e.getMessage());
		}
		return  "false";
	}

	@RequestMapping(params="intoBusiInfoPage")
	public String intoBusiInfoPage() throws Exception{
		return "/view/jsp/sysOperate/showBusiInfo";
	}

	/**
	 * 根据业务码和系统码查询到业务信息
	 * @Title: getBusiInfoBySysBusiCode
	 * @author ZJHao
	 * @return
	 * @throws Exception
	 * @return BusiInfo
	 * @throws
	 * @date 2015年9月22日 下午5:50:10
	 */
	@RequestMapping(params = "getBusiInfoBySysBusiCode")
	@ResponseBody
	public BusiInfo getBusiInfoBySysBusiCode(HttpServletRequest request)  {
		try {
			Map<String, String> params = HttpUtil.getParameterMap(request.getParameterMap());
			BusiInfo busiInfo = busiInfoService.getBusiInfoBySysBusiCode(params);
			return busiInfo;
		} catch (Exception e) {
			log.error("根据业务码和系统码查询到业务信息异常:{}", e.getMessage());
		}
		return null;
	}

	/**
	 * 分页查询业务信息
	 * @param request
	 * @param busiInfoCond
	 * @return
	 */
	@RequestMapping(params="pageBusiInfoAjax")
	@ResponseBody
	public DataTableJson pageBusiInfoAjax(HttpServletRequest request, @ModelAttribute BusiInfoCond busiInfoCond){
		busiInfoCond.setOrderBy(" sys_id ");
		DataTableJson json = new DataTableJson();
		Page page = new Page();
		DataTableDto dataTableDto = new DataTableDto(request);
		page.setStart(dataTableDto.getiDisplayStart());
		page.setRows(dataTableDto.getiDisplayLength());
		page.setCond(busiInfoCond);
		try {
			json = busiInfoService.findBusiInfoByPage(page);
			json.setsEcho(dataTableDto.getsEcho());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	@RequestMapping(params="addBusiInfo")
	public @ResponseBody MsgReturnDto addBusiInfo(@ModelAttribute BusiInfo busiInfo){
		try {
			log.info("开始插入业务信息,param:{}", busiInfo.toString());
			if(busiInfo.getId()==null){
                busiInfoService.inserBusi(busiInfo);
            }else{
                busiInfoService.updateBusiInfo(busiInfo);
            }
			log.info("插入业务数据结束,");
		} catch (Exception e) {
			log.error("插入业务信息异常:{}", e.getMessage());
		}
		return new MsgReturnDto("", "");

	}
	@RequestMapping(params="updateBusiInfo")
	public @ResponseBody String updateBusiInfo(@ModelAttribute BusiInfo busiInfo) {
		try {
			log.info("修改业务信息,param:{}",busiInfo.toString());
			busiInfoService.updateBusiInfo(busiInfo);
			log.info("修改数据结束,id:{}", busiInfo.getId());
			return "true";
		} catch (Exception e) {
			log.error("修改业务信息异常:{}",e.getMessage());
		}
		return "";
	}

	@RequestMapping(params="deleteBusiInfo")
	public @ResponseBody MsgReturnDto deleteBusiInfo(@ModelAttribute BusiInfo busiInfo) {
		try {
			log.info("删除业务信息,busiInfo:{}", busiInfo);
			busiInfoService.deleteBusiInfo(busiInfo);
		} catch (Exception e) {
			log.error("删除业务信息异常:{}", e.getMessage());
		}
		return new MsgReturnDto("", "");
	}

	@RequestMapping(params="findBusiInfoByCondition")
	@ResponseBody
	public BusiInfo findBusiInfoByCondition(@ModelAttribute BusiInfo busiInfo){
		BusiInfo busiInfo1=null;
		try {
			busiInfo1=busiInfoService.findBusiInfoByCondition(busiInfo);
		} catch (Exception e) {
			log.error("根据条件查询业务信息失败:{}",e.getMessage());
		}
		return busiInfo1;
	}


}
