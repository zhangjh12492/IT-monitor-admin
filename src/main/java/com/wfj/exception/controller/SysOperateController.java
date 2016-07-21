package com.wfj.exception.controller;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.dal.cond.SysInfoCond;
import com.wfj.exception.dal.entity.BusiInfo;
import com.wfj.exception.dal.entity.SysInfo;
import com.wfj.exception.dal.service.BusiInfoService;
import com.wfj.exception.dal.service.SysInfoService;
import com.wfj.exception.dal.service.UserInfoService;
import com.wfj.exception.util.DataTableDto;
import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.HttpUtil;
import com.wfj.exception.util.MsgReturnDto;
import com.wfj.sysmanager.httpModel.SessionInfo;
import com.wfj.sysmanager.httpModel.User;
import com.wfj.sysmanager.util.ResourceUtil;
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
import java.util.Map;

@Controller
@RequestMapping("/sysOperate")
public class SysOperateController {

	private static final Logger log=LoggerFactory.getLogger(SysOperateController.class);
	
	@Resource(name="sysInfoService")
	private SysInfoService sysInfoService;
	@Resource(name="busiInfoService")
	private BusiInfoService busiInfoService;
	@Resource(name="userInfoService")
	private UserInfoService userInfoService;
	
	/**
	 * 进入系统管理界面
	 * @param model
	 * @param sysCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="showSysInfo")
	public String showSysInfo(Model model,@RequestParam String sysCode) {
		try {
			List<SysInfo> list=sysInfoService.selectAllSys(sysCode);
			model.addAttribute("allSysInfo",list);
		} catch (Exception e) {
			log.error("查询系统信息异常:{}", e.getMessage());
		}
		return "/view/jsp/sysOperate/showSysInfo";
	}
	
	/**
	 * 获取所有的系统信息用于展示下拉框
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="getSysInfoAjax")
	@ResponseBody
	public String getSysInfoAjax(HttpServletRequest request) {
		try {
			Map<String,String> params=HttpUtil.getParameterMap(request.getParameterMap());
			List<SysInfo> list=sysInfoService.selectAllSys(params.get("sysCode"));
			return JSONObject.toJSONString(list);
		} catch (Exception e) {
			log.error("获取所有的系统信息用于展示下拉框报错:{}", e.getMessage());
		}
		return "";
	}
	
	/**
	 * 根据系统id获取系统信息
	 * @Title: getSysInfoBySysId
	 * @author ZJHao
	 * @param id
	 * @return
	 * @throws Exception
	 * @return SysInfo
	 * @throws
	 * @date 2015年9月23日 下午3:33:22
	 */
	@RequestMapping(params="getSysInfoBySysId")
	@ResponseBody
	public SysInfo getSysInfoBySysId(@RequestParam Integer id) {
		try {
			SysInfo sysInfo=sysInfoService.selectBySysId(id);
			return sysInfo;
		} catch (Exception e) {
			log.error("根据系统id获取系统信息异常:{}", e.getMessage());
		}
		return null;
	}

	/**
	 * 根据系统code获取系统信息
	 * @Title: getSysInfoBySysCode
	 * @author ZJHao
	 * @return
	 * @throws Exception
	 * @return SysInfo
	 * @throws
	 * @date 2015年9月23日 下午3:33:22
	 */
	@RequestMapping(params="getSysInfoBySysCode")
	@ResponseBody
	public SysInfo getSysInfoBySysCode(@RequestParam String sysCode){
		try {
			SysInfo sysInfo=sysInfoService.selectSysInfoBySysCode(sysCode);
			return sysInfo;
		} catch (Exception e) {
			log.error("根据系统code获取系统信息异常:{}", e.getMessage());
		}
		return null;
	}
	/**
	 * 异步获取分页系统信息
	 * @param request
	 * @param sysInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="pageSysInfoAjax")
	public @ResponseBody DataTableJson pageSysInfoAjax(HttpServletRequest request, @ModelAttribute SysInfoCond sysInfo)  {
		log.info("sysInfo:{}",sysInfo.toString());
		sysInfo.setOrderBy(" sys_code ");
		DataTableJson json = new DataTableJson();
		Page page = new Page();
		DataTableDto dataTableDto = new DataTableDto(request);
		page.setStart(dataTableDto.getiDisplayStart());
		page.setRows(dataTableDto.getiDisplayLength());
		page.setCond(sysInfo);
		try {
			json = sysInfoService.findSysInfoByPage(page);
			json.setsEcho(dataTableDto.getsEcho());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	

	
	
//	@RequestMapping(params="selectSysInfoList")
//	public DataTableJson selectSysInfoList(HttpServletRequest request,@ModelAttribute SysInfo sysInfo){
//		 DataTableJson json = new DataTableJson();
//	        Page page = new Page();
//	        DataTableDto dataTableDto = new DataTableDto(request);
//	        page.setStart(dataTableDto.getiDisplayStart());
//	        page.setRows(dataTableDto.getiDisplayLength());
//	        page.setCond(sysInfo);
//	        try {
//	            json = userInfoService.getUserinfoList(page);
//	            json.setsEcho(dataTableDto.getsEcho());
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	        return json;
//	}
	
	@RequestMapping(params="addSysInfo")
	public @ResponseBody MsgReturnDto addSysInfo(@ModelAttribute SysInfo sysInfo)  {
		try {
			log.info("开始插入系统信息,param:{}",sysInfo.toString());
			if(sysInfo.getId()==null){
                sysInfoService.insertSysInfo(sysInfo);
            }else{
                sysInfoService.updateSysInfo(sysInfo);
            }
			log.info("插入系统数据结束,");
		} catch (Exception e) {
			log.error("插入系统信息异常:{}", e.getMessage());
		}
		return new MsgReturnDto("", "");
		
	}
	@RequestMapping(params="updateSysInfo")
	public @ResponseBody String updateSysInfo(@ModelAttribute SysInfo sysInfo) {
		try {
			log.info("修改系统信息,param:{}",sysInfo.toString());
			sysInfoService.updateSysInfo(sysInfo);
			log.info("修改数据结束,id:{}",sysInfo.getId());
		} catch (Exception e) {
			log.error("修改系统信息异常:{}", e.getMessage());
			return "false";
		}
		return "true";
	}
	
	@RequestMapping(params="deleteSysInfo")
	public @ResponseBody MsgReturnDto deleteSysInfo(@RequestParam Integer id) {
		try {
			log.info("删除系统信息,id:{}",id);
			sysInfoService.deleteSysInfo(id);
		} catch (Exception e) {
			log.error("删除系统信息异常:{}", e.getMessage());
		}
		return new MsgReturnDto("", "");
	}
	
	/**
	 * 获取到当前登陆的用户所能查看到的系统
	 * @Title: getUserSysInfo
	 * @author ZJHao
	 * @param request
	 * @return
	 * @throws Exception
	 * @return List<SysInfo>
	 * @throws
	 * @date 2015年9月24日 下午6:15:32
	 */
	@RequestMapping(params="getUserSysInfo")
	@ResponseBody
	public List<SysInfo> getUserSysInfo(HttpServletRequest request) {
		try {
			SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
			assert sessionInfo != null;
			User user = sessionInfo.getUser();
			log.info("获取到当前登陆的用户所能查看到的系统,user:{}",user.toString());
			List<SysInfo> list=sysInfoService.selectSysByUser(user);
			return list;
		} catch (Exception e) {
			log.error("获取当前登陆的用户所能查看的系统异常:{}", e.getMessage());
		}
		return null;
	}
	
	/**
	 * 
	 * @Title: pageBusiInfo
	 * @author ZJHao
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 * @date 2015年9月22日 下午5:48:35
	 */
	@RequestMapping("/pageBusiInfo")
	public String pageBusiInfo(Model model,HttpServletRequest request) throws Exception{
		try {
			Map<String, String> map=HttpUtil.getParameterMap(request.getParameterMap());
			List<BusiInfo> list=busiInfoService.findAllBusi(map.get("sysId"));
			model.addAttribute("allBusiInfo", list);
			log.info("model:{}",model.toString());
		} catch (Exception e) {
			log.error("分页查询业务信息异常:{}", e.getMessage());
		}
		return "/view/jsp/sysOperate/showBusiInfo";
	}
	
	
}
