package com.wfj.exception.controller;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.dal.entity.MesAllProcessReq;
import com.wfj.exception.dal.entity.PcdsSymenu;
import com.wfj.exception.dal.service.MenuService;
import com.wfj.exception.dal.service.SysInfoService;
import com.wfj.sysmanager.httpModel.SessionInfo;
import com.wfj.sysmanager.httpModel.User;
import com.wfj.sysmanager.service.MenuServiceI;
import com.wfj.sysmanager.service.UserServiceI;
import com.wfj.sysmanager.util.IpUtil;
import com.wfj.sysmanager.util.ResourceUtil;

/**
 * Created by MaYong on 2015/8/21.
 */

@Controller
@RequestMapping("/login")
public class LoginController {
	

    @Autowired
    private UserServiceI userService;

    @Autowired
    private MenuServiceI menuServiceI;
    
    @Resource(name="sysInfoService")
    private SysInfoService sysInfoService;

    @Resource(name = "menuService_my")
    private MenuService menuService;

    @RequestMapping(params = "loginMain")
    public ModelAndView login(ModelAndView model) {
        model.setViewName("forward:login.jsp");
//        model.setViewName("forward:index33.jsp");
        return model;
    }

    @RequestMapping(params = "getMenuList")
    @ResponseBody
    public List<PcdsSymenu> getMenuList(@RequestParam Integer userId) {
        List<PcdsSymenu> list = null;
        try {
            list = menuService.getMenuList(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @RequestMapping(params = "leftMenu")
    public ModelAndView left(@RequestParam Integer userId, ModelAndView model) {
        List<PcdsSymenu> list = null;
        try {
            list = menuService.getMenuList(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("===============:" + list.size());
        model.addObject("mainMenu", list);
        model.addObject("userId", userId);
        model.setViewName("forward:view/jsp/iframe/left.jsp");
//        model.setViewName("forward:index33.jsp");
        return model;
    }

    @RequestMapping(params = "login")
    public ModelAndView login(@RequestParam String name, @RequestParam String password,
                              HttpServletRequest request, HttpSession session,
                              ModelAndView model) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        User u = userService.login(user);
        if (u != null) {
            u.setIp(IpUtil.getIpAddr(request));
            SessionInfo sessionInfo = new SessionInfo();
            sessionInfo.setUser(u);
            session.setAttribute(ResourceUtil.getSessionInfoName(), sessionInfo);
            session.setMaxInactiveInterval(60 * 60 * 24);
            //正常，跳转到首页
            List<PcdsSymenu> list = null;
            try {
                list = menuService.getMenuList(u.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            System.out.println("===============:" + list.size());
            model.addObject("userId", u.getId());
            model.addObject("mainMenu", list);
            model.setViewName("forward:view/jsp/iframe/indexFrame.jsp");
//            model.setViewName("forward:index33.jsp");
        } else {
            //跳转到登录页面，并显示登录失败信息
//            model.setViewName("forward:login.jsp");
            System.out.println("**************:登录失败");
        }


/*
        List<EasyuiTreeNode> list = menuServiceI.tree("0");

        for (EasyuiTreeNode node : list) {
            String pid = node.getId();
            List<EasyuiTreeNode> sub = menuServiceI.tree(pid);
            node.setChildren(sub);
        }
        model.addObject("mainMenu", list);
*/
        return model;

    }
    
    /**
     * 获取登陆的用户所属系统生成异常以及警告的数量
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(params="getUserProcessMesCount",produces="text/html;charset=UTF-8")
    @ResponseBody
    public String getUserProcessMesCount(HttpServletRequest request) throws Exception{
    	SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
        assert sessionInfo != null;
        User user = sessionInfo.getUser();
        List<MesAllProcessReq> mesAllReqList=sysInfoService.selectUserProcessMesCountByUser(user);
        return JSONObject.toJSONString(mesAllReqList);
    }

}
