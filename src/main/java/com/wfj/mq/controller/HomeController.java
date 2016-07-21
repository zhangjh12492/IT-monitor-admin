package com.wfj.mq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/home")
public class HomeController {

	@RequestMapping("/index")
	public String home(HttpServletRequest request,ModelMap model){
			String basePath=request.getContextPath();
		model.addAttribute("ctx", basePath);
		return "home";
	}

}
