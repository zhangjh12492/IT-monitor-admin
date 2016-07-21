package com.wfj.sysmanager.interceptors;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wfj.exception.dal.util.JsonUtils;
import com.wfj.exception.util.MsgReturnDto;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wfj.sysmanager.httpModel.SessionInfo;
import com.wfj.sysmanager.util.ResourceUtil;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURI();
		if(url.indexOf("userController.do" )>0|| url.indexOf("erpPrintDataController")>0 || url.indexOf("wfjController")>0 ) {
			return true;
		}
		SessionInfo sessionInfo = (SessionInfo) request.getSession()
				.getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo == null) {// 没有登录系统，或登录超时
			forward("您没有登录或登录超时，请重新登录！", request, response);
			return false;
		}
		return true;



	}

	private void forward(String msg, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("error/authMsg.jsp").forward(request, response);
*/
        MsgReturnDto dto = new MsgReturnDto("", msg);
        response.getWriter().print(JsonUtils.beanToJson(dto));
	}

}
