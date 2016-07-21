package com.wfj.exception.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wfj.exception.dal.entity.MesAllProcessReq;
import com.wfj.exception.dal.service.MesAllProcessService;
import com.wfj.exception.util.HttpUtil;

/**
 * 异常处理
 * @ClassName: MesAllProcessController
 * @author ZJHao
 * @date 2015年9月23日 下午5:45:09
 *
 */
@Controller("/mesProcess")
public class MesAllProcessController {

	private static final Logger log= LoggerFactory.getLogger(MesAllProcessController.class);
	@Resource
	private MesAllProcessService mesAllProcessService;
	
	/**
	 * 根据系统code查询最近插入的一条异常处理结果
	 * @Title: selectOneTopProcessMesBySysCode
	 * @author ZJHao
	 * @param request
	 * @return
	 * @throws Exception
	 * @return MesAllProcessReq
	 * @throws
	 * @date 2015年9月23日 上午10:01:36
	 */
	@RequestMapping(params="selectOneTopProcessMesBySysCode")
	@ResponseBody
	public MesAllProcessReq selectOneTopProcessMesBySysCode(HttpServletRequest request) {
		try {
			Map<String, String> params=HttpUtil.getParameterMap(request.getParameterMap());
			MesAllProcessReq mesAllProcessReq =mesAllProcessService.selectOneTopProcessMesBySysCode(params);
			return mesAllProcessReq;
		} catch (Exception e) {
			log.error("根据系统code查询最近插入的一条异常处理结果报错:{}", e.getMessage());
		}
		return null;
	}
}
