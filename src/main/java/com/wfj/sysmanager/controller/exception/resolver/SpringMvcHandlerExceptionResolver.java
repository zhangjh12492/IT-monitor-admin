package com.wfj.sysmanager.controller.exception.resolver;

//import com.pcds.sysmanager.util.ExceptionUtil;
//import org.apache.log4j.Logger;
import com.wfj.sysmanager.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * spring mvc异常捕获类
 * 
 * @author 孙宇
 * 
 */
@Component
public class SpringMvcHandlerExceptionResolver implements HandlerExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger(SpringMvcHandlerExceptionResolver.class);

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		String exceptionMessage = ExceptionUtil.getExceptionMessage(ex);
		logger.error(exceptionMessage);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("exceptionMessage", exceptionMessage);
		return new ModelAndView("/error/exceptionMessage", model);
	}

}
