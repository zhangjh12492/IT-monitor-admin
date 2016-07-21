package com.wfj.exception.controller;

import com.wfj.exception.dal.cond.UserInfoCond;
import com.wfj.exception.dal.service.UserInfoService;
import com.wfj.exception.util.DataTableDto;
import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.MsgReturnDto;
import com.wfj.sysmanager.model.Syuser;
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

/**
 * Created by MaYong on 2015/8/17.
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    private static final Logger log= LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(params = "user")
    public String groupUser() {
        return "/view/user";
    }

    @RequestMapping(params = "delUserInfo")
    @ResponseBody
    public MsgReturnDto delUserInfo(@RequestParam Integer id, HttpServletRequest request) {
        String msg = null;
        try {
            msg = userInfoService.delUserInfo(id);
        } catch (SQLException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return new MsgReturnDto("", msg);
    }

    @RequestMapping(params = "saveOrUpdateUser")
    @ResponseBody
    public MsgReturnDto saveOrUpdateUser(@ModelAttribute Syuser userInfo, HttpServletRequest request) {
        String msg = null;
        try {
            userInfoService.saveOrUpdateUser(userInfo);
        } catch (SQLException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return new MsgReturnDto("", msg);
    }

    @RequestMapping(params = "getUserInfoList")
    @ResponseBody
    public DataTableJson getUserInfoList(@ModelAttribute UserInfoCond userInfoCond, HttpServletRequest request) {
        DataTableJson json = new DataTableJson();
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(userInfoCond);
        try {
            json = userInfoService.getUserinfoList(page);
            json.setsEcho(dataTableDto.getsEcho());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 根据条件查询用户
     * @param syuser
     * @return
     */
    @RequestMapping(params = "getUserByCondition")
    @ResponseBody
    public Syuser getUserByCondition(@ModelAttribute Syuser syuser) {
        try {
            Syuser syuser1=userInfoService.getUserByCondition(syuser);
            return syuser1;
        } catch (Exception e) {
            log.error("根据条件查询user失败:{}",e.getMessage());
        }
        return null;
    }

}
