package com.wfj.exception.controller;

import com.wfj.exception.dal.cond.UserGroupCond;
import com.wfj.exception.dal.entity.UserGroup;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaYong on 2015/8/17.
 */
@Controller
@RequestMapping("/userGroup")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;


    @RequestMapping(params = "groupUser")
    public String groupUser() {
        return "/view/groupUser";
    }

    @RequestMapping(params = "group")
    public String group() {
        return "view/group";
    }


    @RequestMapping(params = "addUser")
    @ResponseBody
    public MsgReturnDto addUser(@RequestParam Integer[] ids, @RequestParam Integer groupId,
                                @RequestParam Integer userType, HttpServletRequest request) {
        String msg = null;
        System.out.println("=========== " + ids);
        try {
            msg = userGroupService.addUser(groupId, ids, userType);
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
            msg = userGroupService.delUserGroup(id);
        } catch (SQLException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return new MsgReturnDto("", msg);
    }

    @RequestMapping(params = "delUserGroupUserInfo")
    @ResponseBody
    public MsgReturnDto delUserGroupUserInfo(@RequestParam Integer id, HttpServletRequest request) {
        String msg = null;
        try {
            msg = userGroupService.delUserGroupUserInfo(id);
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
            userGroupService.saveOrUpdateGroup(userGroup);
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
    public DataTableJson getUserGroupList(@ModelAttribute UserGroupCond userGroupCond, HttpServletRequest request) {
        DataTableJson json = new DataTableJson();
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(userGroupCond);
        try {
            json = userGroupService.getUsergroupList(page);
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
            json = userGroupService.getNotExistUser(page);
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
            json = userGroupService.selectUsersByGroupid(page);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
