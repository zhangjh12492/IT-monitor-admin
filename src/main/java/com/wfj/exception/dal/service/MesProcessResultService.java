package com.wfj.exception.dal.service;

import com.wfj.exception.dal.entity.MesAllProcessReq;

public interface MesProcessResultService {


	/**
	 * reduce处理完成调用此方法,发送短息跟邮件
	 * @Title: mesCountProcessResult
	 * @author ZJHao
	 * @param mesAllReq
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 * @date 2015-8-31 下午3:31:44
	 */
	String mesCountProcessResult(MesAllProcessReq mesAllReq) throws Exception;
}
