package com.wfj.sysmanager.controller;

import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.MsgReturnDto;
import com.wfj.mq.dto.DataTableDto;
import com.wfj.sysmanager.cond.RoleCond;
import com.wfj.sysmanager.entity.PcdsSyrole;
import com.wfj.sysmanager.httpModel.EasyuiTreeNode;
import com.wfj.sysmanager.httpModel.Json;
import com.wfj.sysmanager.httpModel.Role;
import com.wfj.sysmanager.service.RoleServiceI;
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
 * 角色控制器
 * 
 * @author 孙宇
 * 
 */
@Controller
@RequestMapping("/roleController")
public class RoleController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	private RoleServiceI roleService;

	public RoleServiceI getRoleService() {
		return roleService;
	}

	@Autowired
	public void setRoleService(RoleServiceI roleService) {
		this.roleService = roleService;
	}

    //=========================================== 角色管理部分 ============================================
	/**
	 * 跳转到角色管理页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "role")
	public String role() {
//		return "/admin/role";
		return "/admin/role";
	}

    /**
     * 跳转到角色管理
     * @return
     */
    @RequestMapping(params = "main")
    public String roleMain(){
        return "/admin/role_2";
    }

    @RequestMapping(params = "getRoleList")
    @ResponseBody
    public DataTableJson getRoleList(@ModelAttribute RoleCond cond, HttpServletRequest request) {
        DataTableJson json = new DataTableJson();
        Page page = new Page();
        cond.setOrderBy(" seq ");
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(cond);
        try {
            json = roleService.getRoleList(page);
            json.setsEcho(dataTableDto.getsEcho());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping(params = "getRoleById")
    @ResponseBody
    public PcdsSyrole getRoleById(@ModelAttribute RoleCond cond, HttpServletRequest request) {
        PcdsSyrole pcdsSyrole = null;
        try {
            pcdsSyrole = roleService.getRoleById(cond.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pcdsSyrole;
    }

    @RequestMapping(params = "saveOrUpdateRole")
    @ResponseBody
    public MsgReturnDto saveOrUpdateRole(@ModelAttribute PcdsSyrole menu, HttpServletRequest request) {
        String msg = null;
        try {
            roleService.saveOrUpdateRole(menu);
        } catch (SQLException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return new MsgReturnDto("", msg);
    }

    @RequestMapping(params = "delRoleById")
    @ResponseBody
    public MsgReturnDto delRoleById(@RequestParam String id, HttpServletRequest request){
        String msg = null;
        try {
            roleService.delRoleById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return new MsgReturnDto("", msg);

    }

    //======================================== 角色管理部分 结束 =========================================





    /**
	 * 角色树
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "tree")
	@ResponseBody
	public List<EasyuiTreeNode> tree(String id) {
		return roleService.tree(id);
	}

	/**
	 * 角色treegrid
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "treegrid")
	@ResponseBody
	public List<Role> treegrid(String id) {
		return roleService.treegrid(id);
	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(params = "add")
	@ResponseBody
	public Json add(Role role) {
		Json j = new Json();
		Role r = roleService.add(role);
		j.setSuccess(true);
		j.setMsg("添加成功!");
		return j;
	}

	/**
	 * 删除角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public Json del(Role role) {
		Json j = new Json();
		roleService.del(role);
		j.setSuccess(true);
		j.setMsg("删除成功!");
		return j;
	}

	/**
	 * 编辑角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(params = "edit")
	@ResponseBody
	public Json edit(Role role) {
		Json j = new Json();
		Role r = roleService.edit(role);
		j.setSuccess(true);
		j.setMsg("编辑成功!");
		return j;
	}

}
