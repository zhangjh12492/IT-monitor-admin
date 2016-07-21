package com.wfj.exception.controller;

import com.wfj.exception.dal.cond.UserGroupCond;
import com.wfj.exception.dal.entity.UserGroup;
import com.wfj.exception.dal.service.BusiGroupService;
import com.wfj.exception.dal.service.UserGroupService;
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
@RequestMapping("/busiGroup")
public class BusiGroupController {

    @Autowired
    private BusiGroupService busiGroupService;

    @RequestMapping(params = "busiGroup")
    public String busiGroup() {
        return "/view/busiGroupRef";
    }

    @RequestMapping(params = "addUser")
    @ResponseBody
    public MsgReturnDto addUser(@RequestParam Integer[] ids, @RequestParam Integer groupId, HttpServletRequest request) {
        String msg = null;
        System.out.println("=========== " + ids);
        try {
            msg = busiGroupService.addGroup(groupId, ids);
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
            msg = busiGroupService.delUserGroup(id);
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
            busiGroupService.saveOrUpdateGroup(userGroup);
        } catch (SQLException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return new MsgReturnDto("", msg);
    }

    @RequestMapping(params = "getUserGroupList")
    @ResponseBody
    public DataTableJson getBusiGroupList(@ModelAttribute UserGroupCond userGroupCond, HttpServletRequest request) {
        DataTableJson json = new DataTableJson();
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(userGroupCond);
        try {
            json = busiGroupService.getBusiGroupList(page);
            json.setsEcho(dataTableDto.getsEcho());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping(params = "getNotExistUser")
    @ResponseBody
    public DataTableJson getNotExistUser(@RequestParam Integer userGroupId, HttpServletRequest request) {
        DataTableJson json = new DataTableJson();
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(userGroupId);
        try {
            json = busiGroupService.getNotExistUser(page);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;

    }

    @RequestMapping(params = "selectUsersByGroupid")
    @ResponseBody
    public DataTableJson selectUsersByGroupid(@RequestParam Integer userGroupId, HttpServletRequest request) {
        DataTableJson json = new DataTableJson();
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(userGroupId);
        try {
            json = busiGroupService.selectUsersByGroupid(page);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
