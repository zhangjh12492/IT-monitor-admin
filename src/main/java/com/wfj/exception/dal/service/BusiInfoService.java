package com.wfj.exception.dal.service;

import com.wfj.exception.dal.entity.BusiInfo;
import com.wfj.exception.util.DataTableJson;
import com.wfj.util.Page;

import java.util.List;
import java.util.Map;

public interface BusiInfoService {

	void inserBusi(BusiInfo busiInfo) throws Exception;
	
	List<BusiInfo> findAllBusi(String sysId) throws Exception;
	
	/**
	 * 通过系统码和业务码获取业务信息
	 * @Title: getBusiInfoBySysBusiCode
	 * @author ZJHao
	 * @return
	 * @throws Exception
	 * @return BusiInfo
	 * @throws
	 * @date 2015年9月22日 下午5:51:13
	 */
	BusiInfo getBusiInfoBySysBusiCode(Map<String, String> params) throws Exception;
	
	/**
	 * 将mapreduce统计的业务插入数据库
	 * @Title: insertBusiByReduce
	 * @author ZJHao
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 * @date 2015年9月24日 上午10:59:37
	 */
	void insertBusiByReduce() throws Exception;

	/**
	 * 删除所有的BusiInfoTmp数据
	 * @throws Exception
	 */
	void deleteAllBusiInfoTmp() throws Exception;

	void deleteBusiInfo(BusiInfo busiInfo) throws Exception ;
	BusiInfo updateBusiInfo(BusiInfo busiInfo) throws Exception ;
	DataTableJson findBusiInfoByPage(Page page) throws Exception ;

	/**
	 * 根据条件查询busiInfo
	 * @param busiInfo
	 * @return
	 * @throws Exception
	 */
	BusiInfo findBusiInfoByCondition(BusiInfo busiInfo) throws Exception;


}
