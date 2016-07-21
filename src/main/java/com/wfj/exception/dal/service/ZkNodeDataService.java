package com.wfj.exception.dal.service;

import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider;
import com.wfj.exception.dal.entity.ZkNodeDataInfo;
import com.wfj.exception.zookeeper.dto.ApplicationInfo;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface ZkNodeDataService {
	
	/**
	 * 插入zkNodeData数据
	 * @Title: insertZkNodeData
	 * @author ZJHao
	 * @param zkNodeData
	 * @throws Exception
	 * @return void
	 * @throws
	 * @date 2015年9月25日 上午11:24:13
	 */
	void insertZkNodeData(ZkNodeDataInfo zkNodeData) throws SQLException,Exception;

	/**
	 * 根据parentName查询最近15分钟内的zk节点的数据
	 * @param zkNodeData
	 * @return
	 * @throws Exception
	 */
	List<ZkNodeDataInfo> selectNearestZkData(ZkNodeDataInfo zkNodeData) throws Exception;

	/**
	 * 根据系统的名称获取到数据再根据时间(hh:mm)进行分组
	 * @return
	 * @throws Exception
	 */
	Map<String,List<ApplicationInfo>> processZkNodeDataByTimeHM(ZkNodeDataInfo zkNodeData) throws Exception;


	/**
	 * 根据系统名称-code和节点的名称查询最近的节点信息
	 * @return
	 * @throws Exception
	 */
	List<ApplicationInfo> selectNearestZkDataByParentNameAndNode(ZkNodeDataInfo zkNodeData) throws Exception;

	/**
	 * 根据系统名称-code获取到下边节点的最近一条数据的信息
	 * @return
	 * @throws Exception
	 */
	List<ApplicationInfo> selectNearestZkOneDataByNode(ZkNodeDataInfo zkNodeData) throws Exception;
	/**
	 * 获取当前时间段内所有系统存活的子节点的数量
	 * @return
	 * @throws Exception
	 */
	List<ZkNodeDataInfo> selectSysActiveNodeCount(HttpServletRequest request) throws Exception;

}





