package com.wfj.sysmanager.controller;

import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.MsgReturnDto;
import com.wfj.mq.dto.DataTableDto;
import com.wfj.sysmanager.cond.UserCond;
import com.wfj.sysmanager.entity.PcdsSyuser2;
import com.wfj.sysmanager.httpModel.*;
import com.wfj.sysmanager.service.UserServiceI;
import com.wfj.sysmanager.util.ExceptionUtil;
import com.wfj.sysmanager.util.IpUtil;
import com.wfj.sysmanager.util.ResourceUtil;
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
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * 用户控制器
 *
 * @author 孙宇
 */
@Controller
@RequestMapping("/userController")
public class UserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserServiceI userService;

    public UserServiceI getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserServiceI userService) {
        this.userService = userService;
    }

    @RequestMapping(params = "north")
    public String north() {
        return "/layout/north";
    }

    @RequestMapping(params = "west")
    public String west() {
        return "/layout/west";
    }

    @RequestMapping(params = "center")
    public String center() {
        return "/layout/center";
    }

    @RequestMapping(params = "south")
    public String south() {
        return "/layout/south";
    }



    /**
     * 跳转到角色管理
     * @return
     */
    @RequestMapping(params = "main")
    public String userMain(){
        return "/admin/user_2";
    }

    @RequestMapping(params = "getUserList")
    @ResponseBody
    public DataTableJson getUserList(@ModelAttribute UserCond cond, HttpServletRequest request) {
        DataTableJson json = new DataTableJson();
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(cond);
        try {
            json = userService.getUserList(page);
            json.setsEcho(dataTableDto.getsEcho());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping(params = "getUserById")
    @ResponseBody
    public PcdsSyuser2 getUserById(@ModelAttribute UserCond cond, HttpServletRequest request) {
        PcdsSyuser2 pcdsSyrole = null;
        try {
            pcdsSyrole = userService.getUserById(cond.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pcdsSyrole;
    }

    @RequestMapping(params = "saveOrUpdateUser")
    @ResponseBody
    public MsgReturnDto saveOrUpdateUser(@ModelAttribute PcdsSyuser2 menu, HttpServletRequest request) {
        String msg = null;
        try {
            userService.saveOrUpdateUser(menu);
        } catch (SQLException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return new MsgReturnDto("", msg);
    }

    @RequestMapping(params = "delUserById")
    @ResponseBody
    public MsgReturnDto delUserById(@RequestParam Integer id, HttpServletRequest request){
        String msg = null;
        try {
            userService.delUserById(id);
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
     * 跳转到home页面
     *
     * @return
     */
    @RequestMapping(params = "home")
    public String home() {
        return "/layout/home";
    }

    /**
     * 跳转到系统探针页面
     *
     * @return
     */
    @RequestMapping(params = "jspinfo")
    public String jspinfo() {
        return "/admin/tz/jspinfo";
    }

    /**
     * 跳转到用户管理页面
     *
     * @return
     */
    @RequestMapping(params = "user")
    public String user() {
        return "/admin/user";
    }

    /**
     * 跳转到用户信息页面
     *
     * @return
     */
    @RequestMapping(params = "userInfo")
    public String userInfo() {
        return "/user/userInfo";
    }

    /**
     * 获得用户信息
     *
     * @return
     */
    @RequestMapping(params = "getUserInfo")
    @ResponseBody
    public Json getUserInfo(HttpSession session) {
        Json j = new Json();
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
        if (sessionInfo != null && sessionInfo.getUser() != null) {
            User u = userService.getUserInfo(sessionInfo.getUser());
            j.setObj(u);
            j.setSuccess(true);
        } else {
            j.setMsg("您没有登录或登录超时，请重新登录后重试！");
        }
        return j;
    }

    /**
     * 编辑用户信息
     *
     * @return
     */
    @RequestMapping(params = "editUserInfo")
    @ResponseBody
    public Json editUserInfo(User user) {
        Json j = new Json();
        User u = userService.editUserInfo(user);
        if (u != null) {
            j.setSuccess(true);
        }
        return j;
    }

    /**
     * 用户注销
     *
     * @param session
     * @return
     */
    @RequestMapping(params = "logout")
    @ResponseBody
    public Json logout(HttpSession session) {
        Json j = new Json();
        if (session != null) {
            session.invalidate();
        }
        j.setSuccess(true);
        return j;
    }

    /**
     * 用户注册
     *
     * @param user 用户的信息
     * @return json
     */
    @RequestMapping(params = "reg")
    @ResponseBody
    public Json reg(User user) {
        Json j = new Json();
        try {
            User u = userService.reg(user);
            j.setSuccess(true);
            j.setMsg("注册成功！");
        } catch (Exception e) {
            j.setMsg("用户名已存在！");
            logger.error(ExceptionUtil.getExceptionMessage(e));
        }
        return j;
    }

    /**
     * 用户登录
     *
     * @param user 用户的信息
     * @return json
     */
    @RequestMapping(params = "login")
    @ResponseBody
    public Json login(User user, HttpSession session, HttpServletRequest request) {
        Json j = new Json();
        User u = userService.login(user);
        if (u != null) {
            j.setSuccess(true);
            j.setMsg("登录成功!");

            u.setIp(IpUtil.getIpAddr(request));
            SessionInfo sessionInfo = new SessionInfo();
            sessionInfo.setUser(u);
            session.setAttribute(ResourceUtil.getSessionInfoName(), sessionInfo);
            session.setMaxInactiveInterval(60 * 60 * 24);
            j.setObj(u);
        } else {
            j.setMsg("用户名或密码错误!");
        }
        return j;
    }

    /**
     * 获得用户表格
     *
     * @param dg
     * @param user
     * @return
     */
    @RequestMapping(params = "loginDatagrid")
    @ResponseBody
    public EasyuiDataGridJson loginDatagrid(EasyuiDataGrid dg, User user) {
        return userService.datagrid(dg, user);
    }

    /**
     * 获得用户列表
     *
     * @param q
     * @return
     */
    @RequestMapping(params = "loginCombobox")
    @ResponseBody
    public List<User> loginCombobox(String q) {
        return userService.combobox(q);
    }

    /**
     * 用户表格
     *
     * @param dg
     * @param user
     * @return
     */
    @RequestMapping(params = "datagrid")
    @ResponseBody
    public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, User user) {
        return userService.datagrid(dg, user);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @RequestMapping(params = "add")
    @ResponseBody
    public User add(User user) {
        return userService.add(user);
    }

    /**
     * 编辑用户
     *
     * @param user
     * @return
     */
    @RequestMapping(params = "edit")
    @ResponseBody
    public User edit(User user) {
        return userService.edit(user);
    }

    /**
     * 批量编辑用户角色
     *
     * @param userIds 用户ID
     * @param roleId  角色ID
     * @return
     */
    @RequestMapping(params = "editUsersRole")
    @ResponseBody
    public Json editUsersRole(String userIds, String roleId) {
        Json j = new Json();
        userService.editUsersRole(userIds, roleId);
        j.setSuccess(true);
        return j;
    }

    /**
     * 删除用户
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "del")
    @ResponseBody
    public Json del(String ids) {
        Json j = new Json();
        userService.del(ids);
        j.setSuccess(true);
        return j;
    }

}
