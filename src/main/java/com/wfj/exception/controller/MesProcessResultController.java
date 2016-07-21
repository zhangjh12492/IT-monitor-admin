package com.wfj.exception.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.common.RequestResult;
import com.wfj.exception.dal.entity.MesAllProcessReq;
import com.wfj.exception.dal.service.MesProcessResultService;

@Controller
@RequestMapping("/mesProcessResult")
public class MesProcessResultController {

	private static final Logger log=LoggerFactory.getLogger(MesProcessResultController.class);
	
	@Resource(name="mesProcessResultService")
	private MesProcessResultService mesProcessResultService;
	
	/**
	 * hadoop 统计完异常数量调用此方法,判断该给哪些用户发送消息
	 * @Title: processSendUserByEW
	 * @author ZJHao
	 * @return
	 * @return String
	 * @throws
	 * @date 2015-8-31 下午2:48:02
	 */
	@RequestMapping(params="processSendUserByEW",method = RequestMethod.POST ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String processSendUserByEW(@RequestBody MesAllProcessReq mesAllReq){
		RequestResult<String> resReq=new RequestResult<String>();
		try {
			String flag=mesProcessResultService.mesCountProcessResult(mesAllReq);
			resReq.setContent(flag);
			resReq.setSuccess(true);
		} catch (Exception e) {
			resReq.setSuccess(false);
			resReq.setResultMsg(e.getMessage());
			resReq.setContent("false");
			e.printStackTrace();
		}
		return JSONObject.toJSONString(resReq);
	}
}
