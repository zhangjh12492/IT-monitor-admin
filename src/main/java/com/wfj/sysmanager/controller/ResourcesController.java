package com.wfj.sysmanager.controller;

import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.MsgReturnDto;
import com.wfj.mq.dto.DataTableDto;
import com.wfj.sysmanager.cond.ResourcesCond;
import com.wfj.sysmanager.cond.UserCond;
import com.wfj.sysmanager.entity.PcdsSyresources;
import com.wfj.sysmanager.entity.PcdsSyuser2;
import com.wfj.sysmanager.httpModel.EasyuiTreeNode;
import com.wfj.sysmanager.httpModel.Json;
import com.wfj.sysmanager.httpModel.Resources;
import com.wfj.sysmanager.service.ResourcesServiceI;
import com.wfj.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * 资源控制器
 * 
 * @author 孙宇
 * 
 */
@Controller
@RequestMapping("/resourcesController")
public class ResourcesController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ResourcesController.class);

	private ResourcesServiceI resourcesService;

	public ResourcesServiceI getResourcesService() {
		return resourcesService;
	}

	@Autowired
	public void setResourcesService(ResourcesServiceI resourcesService) {
		this.resourcesService = resourcesService;
	}

    /**
     * 跳转到角色管理
     * @return
     */
    @RequestMapping(params = "main")
    public String userMain(){
        return "/admin/resources_3";
    }

    @RequestMapping(params = "getResList")
    @ResponseBody
    public DataTableJson getResList(@ModelAttribute ResourcesCond cond, HttpServletRequest request) {
        DataTableJson json = new DataTableJson();
        Page page = new Page();
        cond.setOrderBy(" seq ");
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(cond);
        try {
            json = resourcesService.getResList(page);
            json.setsEcho(dataTableDto.getsEcho());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping(params = "getResById")
    @ResponseBody
    public PcdsSyresources getResById(@ModelAttribute ResourcesCond cond, HttpServletRequest request) {
        PcdsSyresources pcdsSyrole = null;
        try {
            pcdsSyrole = resourcesService.getResById(cond.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pcdsSyrole;
    }

    @RequestMapping(params = "saveOrUpdateRes")
    @ResponseBody
    public MsgReturnDto saveOrUpdateRes(@ModelAttribute PcdsSyresources menu, HttpServletRequest request) {
        String msg = null;
        try {
            resourcesService.saveOrUpdateRes(menu);
        } catch (SQLException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return new MsgReturnDto("", msg);
    }

    @RequestMapping(params = "delResById")
    @ResponseBody
    public MsgReturnDto delResById(@RequestParam String id, HttpServletRequest request){
        String msg = null;
        try {
            resourcesService.getResById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return new MsgReturnDto("", msg);

    }














    /**
	 * 跳转到资源管理页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "resources")
	public String resources() {
		return "/admin/resources";
	}

	/**
	 * 获取资源管理树
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "tree")
	@ResponseBody
	public List<EasyuiTreeNode> tree(String id) {
		return resourcesService.tree(id);
	}

	/**
	 * 资源管理treegrid
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "treegrid")
	@ResponseBody
	public List<Resources> treegrid(String id) {
		return resourcesService.treegrid(id);
	}

	/**
	 * 添加资源
	 * 
	 * @param resources
	 * @return
	 */
	@RequestMapping(params = "add")
	@ResponseBody
	public Json add(Resources resources) {
		Json j = new Json();
		Resources r = resourcesService.add(resources);
		j.setSuccess(true);
		j.setMsg("添加成功!");
		return j;
	}

	/**
	 * 删除资源
	 * 
	 * @param resources
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public Json del(Resources resources) {
		Json j = new Json();
		resourcesService.del(resources);
		j.setSuccess(true);
		j.setMsg("删除成功!");
		return j;
	}

	/**
	 * 编辑资源
	 * 
	 * @param resources
	 * @return
	 */
	@RequestMapping(params = "edit")
	@ResponseBody
	public Json edit(Resources resources) {
		Json j = new Json();
		Resources r = resourcesService.edit(resources);
		j.setSuccess(true);
		j.setMsg("编辑成功!");
		return j;
	}

}
