package com.wfj.exception.dal.service;

import com.wfj.exception.dal.cond.SmsConfigInfoCond;
import com.wfj.exception.dal.entity.BusiInfo;
import com.wfj.exception.dal.entity.MailConfigInfo;
import com.wfj.exception.dal.entity.SmsConfigInfo;
import com.wfj.exception.util.DataTableJson;
import com.wfj.util.Page;

import java.util.List;
import java.util.Map;

public interface SmsConfigInfoService {


	/**
	 * 新增短信配置
	 * @throws Exception
	 */
	void insertSmsConfigInfo(SmsConfigInfo smsConfigInfo) throws Exception;

	/**
	 * 删除短信配置
	 * @param smsConfigInfo
	 * @throws Exception
	 */
	void deleteSmsConfigInfo(SmsConfigInfo smsConfigInfo) throws Exception;

	/**
	 * 修改短信配置
	 * @throws Exception
	 */
	void updateSmsConfigInfo(SmsConfigInfo smsConfigInfo) throws  Exception;

	/**
	 * 查询短信配置
	 * @param page
	 * @return
	 * @throws Exception
	 */
	DataTableJson selectSmsConfigInfoPage(Page page) throws Exception;

	/**
	 * 根据使用状态查询邮箱配置
	 * @return
	 * @throws Exception
	 */
	Integer selectSmsCountByUsedStatus(SmsConfigInfo smsConfigInfo) throws Exception;

	/**
	 * 查询所有可以使用的短信配置
	 * @return
	 * @throws Exception
	 */
	SmsConfigInfo selectSmsInfoByUsedStatus(SmsConfigInfo smsConfigInfo) throws Exception;
}
