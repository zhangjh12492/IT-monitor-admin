package com.wfj.sysmanager.controller;

import com.wfj.sysmanager.httpModel.EasyuiDataGrid;
import com.wfj.sysmanager.httpModel.EasyuiDataGridJson;
import com.wfj.sysmanager.httpModel.Json;
import com.wfj.sysmanager.httpModel.Portal;
import com.wfj.sysmanager.service.PortalServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * portal控制器
 * 
 * @author 孙宇
 * 
 */
@Controller
@RequestMapping("/portalController")
public class PortalController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(PortalController.class);

	private PortalServiceI protalService;

	public PortalServiceI getProtalService() {
		return protalService;
	}

	@Autowired
	public void setProtalService(PortalServiceI protalService) {
		this.protalService = protalService;
	}

	@RequestMapping(params = "datagrid")
	@ResponseBody
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Portal portal) {
		return protalService.datagrid(dg, portal);
	}

	@RequestMapping(params = "show")
	@ResponseBody
	public EasyuiDataGridJson show(EasyuiDataGrid dg, Portal portal) {
		return datagrid(dg, portal);
	}

	@RequestMapping(params = "portal")
	public String show() {
		return "/admin/portal";
	}

	@RequestMapping(params = "del")
	@ResponseBody
	public Json del(String ids) {
		Json j = new Json();
		protalService.del(ids);
		j.setSuccess(true);
		return j;
	}

	@RequestMapping(params = "edit")
	@ResponseBody
	public Json edit(Portal portal) {
		Json j = new Json();
		protalService.edit(portal);
		j.setMsg("编辑成功！");
		j.setSuccess(true);
		return j;
	}

	@RequestMapping(params = "add")
	@ResponseBody
	public Json add(Portal portal) {
		Json j = new Json();
		protalService.add(portal);
		j.setSuccess(true);
		return j;
	}

}
