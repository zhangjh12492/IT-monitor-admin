package com.wfj.sysmanager.controller;

import com.wfj.exception.util.DataTableDto;
import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.MsgReturnDto;
import com.wfj.sysmanager.cond.RoleCond;
import com.wfj.sysmanager.service.RoleResService;
import com.wfj.sysmanager.service.RoleServiceI;
import com.wfj.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by MaYong on 2015/9/23.
 */

@Controller
@RequestMapping("/roleRes")
public class RoleResController {


    @Autowired
    private RoleResService roleResService;

    @Autowired
    private RoleServiceI roleService;

    @RequestMapping(params = "main")
    public String roleResMain(){
        return "/admin/roleRes";

    }

    @RequestMapping(params = "addRes")
    @ResponseBody
    public MsgReturnDto addRes(@RequestParam String[] ids, @RequestParam String roleId,
                                HttpServletRequest request) {
        String msg = null;
        System.out.println("=========== " + ids);
        try {
            msg = roleResService.addRes(roleId, ids);
        } catch (SQLException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return new MsgReturnDto("", msg);
    }

    @RequestMapping(params = "delRoleRes")
    @ResponseBody
    public MsgReturnDto delRoleRes(@RequestParam String id, HttpServletRequest request) {
        String msg = null;
        try {
            msg = roleResService.delRoleRes(id);
        } catch (SQLException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return new MsgReturnDto("", msg);
    }


    @RequestMapping(params = "getRoleList")
    @ResponseBody
    public DataTableJson getRoleList(@ModelAttribute RoleCond cond, HttpServletRequest request) {
        DataTableJson json = new DataTableJson();
        Page page = new Page();
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

    @RequestMapping(params = "getNotExistRes")
    @ResponseBody
    public DataTableJson getNotExistRes(@RequestParam String roleId, HttpServletRequest request) {
        DataTableJson json = new DataTableJson();
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(roleId);
        try {
            json = roleResService.getNotExistRes(page);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;

    }

    @RequestMapping(params = "selectResByRoleid")
    @ResponseBody
    public DataTableJson selectResByRoleid(@RequestParam String roleId, HttpServletRequest request) {
        DataTableJson json = new DataTableJson();
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(roleId);
        try {
            json = roleResService.selectResByRoleid(page);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


}
