package com.wfj.exception.dal.service;

import com.wfj.exception.dal.entity.MailConfigInfo;
import com.wfj.exception.email.vo.MailSenderVo;
import com.wfj.exception.util.DataTableJson;
import com.wfj.util.Page;

public interface MailConfigInfoService {


	/**
	 * 新增短信配置
	 * @throws Exception
	 */
	void insertMailConfigInfo(MailConfigInfo mailConfigInfo) throws Exception;

	/**
	 * 删除短信配置
	 * @param mailConfigInfo
	 * @throws Exception
	 */
	void deleteMailConfigInfo(MailConfigInfo mailConfigInfo) throws Exception;

	/**
	 * 修改短信配置
	 * @throws Exception
	 */
	void updateMailConfigInfo(MailConfigInfo mailConfigInfo) throws  Exception;

	/**
	 * 查询短信配置
	 * @param page
	 * @return
	 * @throws Exception
	 */
	DataTableJson selectMailConfigInfoPage(Page page) throws Exception;

	/**
	 * 根据使用状态查询邮箱配置
	 * @param mailConfigInfo
	 * @return
	 * @throws Exception
	 */
	Integer selectMailCountByUsedStatus(MailConfigInfo mailConfigInfo) throws Exception;
	/**
	 * 查询所有可以使用的邮箱配置
	 * @return
	 * @throws Exception
	 */
	MailConfigInfo selectMailInfoByUsedStatus(MailConfigInfo mailConfigInfo) throws Exception;

	/**
	 * 给多人发送邮件
	 * @param mailSenderVo
	 * @return
	 * @throws Exception
	 */
	Boolean sendMail(MailSenderVo mailSenderVo) throws Exception;
}
