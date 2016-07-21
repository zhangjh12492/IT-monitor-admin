package com.wfj.sysmanager.controller;

import com.wfj.exception.dal.util.JsonUtils;
import com.wfj.exception.util.MsgReturnDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by MaYong on 2015/9/28.
 */
@Controller
@RequestMapping("/testRequest")
public class TestRequestController {

    @RequestMapping(params = "testRequest")
    public void test(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        request.setAttribute("msg", msg);
        MsgReturnDto dto = new MsgReturnDto("code", "desc");
        response.getWriter().print(JsonUtils.beanToJson(dto));
//        request.getRequestDispatcher("error/authMsg.jsp").forward(request, response);

    }
}

