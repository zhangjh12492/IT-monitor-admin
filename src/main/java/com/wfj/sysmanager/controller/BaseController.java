package com.wfj.sysmanager.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wfj.sysmanager.httpModel.SessionInfo;
import com.wfj.sysmanager.httpModel.User;
import com.wfj.sysmanager.util.CustomDateEditor;
import com.wfj.sysmanager.util.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.wfj.admin.bo.ErpStockInfoUserBO;
//import com.pcds.admin.bo.ErpSupplyInfoUserBO;
//import com.pcds.admin.exception.BOException;
//import com.pcds.admin.vo.ErpStockInfo;
//import com.pcds.sysmanager.httpModel.SessionInfo;
//import com.pcds.sysmanager.httpModel.User;
//import com.pcds.sysmanager.util.CustomDateEditor;
//import com.pcds.sysmanager.util.ResourceUtil;

/**
 * 基础控制器，其他控制器需extends此控制器获得initBinder自动转换的功能
 *
 * @author 孙宇
 */
//@Controller
//@RequestMapping("/baseController")
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        // binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

/*
    protected UserInfo getUserInfo(HttpServletRequest request, HttpSession session) {
        UserInfo userInfo = new UserInfo();
        User user = getOptInfo(session);
        String[] stockSid = user.getStock_sid();
        userInfo.setId(user.getId());
        userInfo.setName(user.getName());
        userInfo.setIpAddr(request.getRemoteHost());
        if (stockSid == null || stockSid.length == 0) {
        } else {
            userInfo.setStock_sid(Integer.valueOf(stockSid[0]));
        }
        return userInfo;
    }
*/


}
