package com.wfj.exception.controller;

import com.wfj.exception.dal.cond.SysInfoCond;
import com.wfj.exception.dal.entity.UserGroup;
import com.wfj.exception.dal.service.SysGroupService;
import com.wfj.exception.util.DataTableDto;
import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.MsgReturnDto;
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
 * Created by MaYong on 2015/8/17.
 */
@Controller
@RequestMapping("/sysGroup")
public class SysGroupController {

    @Autowired
    private SysGroupService sysGroupService;

    @RequestMapping(params = "main")
    public String sysGroup() {
        return "/view/sysGroupRef";
    }

    @RequestMapping(params = "addGroup")
    @ResponseBody
    public MsgReturnDto addGroup(@RequestParam Integer[] ids, @RequestParam Integer sysInfoId, HttpServletRequest request) {
        String msg = null;
        System.out.println("=========== " + ids);
        try {
            msg = sysGroupService.addGroup(sysInfoId, ids);
        } catch (SQLException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return new MsgReturnDto("", msg);
    }

    @RequestMapping(params = "delUserGroup")
    @ResponseBody
    public MsgReturnDto delUserGroup(@RequestParam Integer id, HttpServletRequest request) {
        String msg = null;
        try {
            msg = sysGroupService.delUserGroup(id);
        } catch (SQLException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return new MsgReturnDto("", msg);
    }

    @RequestMapping(params = "saveOrUpdateGroup")
    @ResponseBody
    public MsgReturnDto saveOrUpdateGroup(@ModelAttribute UserGroup userGroup, HttpServletRequest request) {
        String msg = null;
        try {
            sysGroupService.saveOrUpdateGroup(userGroup);
        } catch (SQLException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return new MsgReturnDto("", msg);
    }

    @RequestMapping(params = "getSystemList")
    @ResponseBody
    public DataTableJson getSystemList(@ModelAttribute SysInfoCond sysInfoCond, HttpServletRequest request) {
        DataTableJson json = new DataTableJson();
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(sysInfoCond);
        try {
            json = sysGroupService.getSystemList(page);
            json.setsEcho(dataTableDto.getsEcho());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping(params = "getNotExistGroup")
    @ResponseBody
    public DataTableJson getNotExistGroup(@RequestParam Integer sysInfoId, HttpServletRequest request) {
        DataTableJson json = new DataTableJson();
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(sysInfoId);
        try {
            json = sysGroupService.getNotExistGroup(page);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;

    }

    @RequestMapping(params = "selectGroupBySysid")
    @ResponseBody
    public DataTableJson selectGroupBySysid(@RequestParam Integer sysInfoId, HttpServletRequest request) {
        DataTableJson json = new DataTableJson();
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(sysInfoId);
        try {
            json = sysGroupService.selectGroupBySysid(page);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
