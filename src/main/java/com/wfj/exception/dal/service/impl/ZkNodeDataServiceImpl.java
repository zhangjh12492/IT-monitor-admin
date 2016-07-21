package com.wfj.exception.dal.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.dal.entity.ZkNodeClassLibraryInfo;
import com.wfj.exception.dal.entity.ZkNodeDataInfo;
import com.wfj.exception.dal.service.SysInfoService;
import com.wfj.exception.dal.service.ZkNodeDataService;
import com.wfj.exception.zookeeper.dto.ApplicationInfo;
import com.wfj.persist.SimpleGenericDAO;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("zkNodeDataService")
public class ZkNodeDataServiceImpl implements ZkNodeDataService{

	private Logger log=LoggerFactory.getLogger(ZkNodeDataServiceImpl.class);
	private SimpleGenericDAO<ZkNodeDataInfo, Integer> zkNodeDataDao;
	private SimpleGenericDAO<ZkNodeClassLibraryInfo, Integer> zkNodeClassLibraryInfoDAO;
//    @Autowired
//    private ObtainChildrenSyncUsage obtainChildrenSyncUsage;
    @Autowired
    private SysInfoService sysInfoService;
	
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
	@Override
	public void insertZkNodeData(ZkNodeDataInfo zkNodeData) throws SQLException,Exception {
//		log.info("开始插入zkNodeData数据");
		zkNodeDataDao.insert(zkNodeData);
		ZkNodeClassLibraryInfo zkNodeClassLibraryInfo=zkNodeDataConvertToZkNodeClassLibraryInfo(zkNodeData);
		int count= (int) zkNodeClassLibraryInfoDAO.findByStatementCond("selectClassPathCountByAllCondition",zkNodeClassLibraryInfo);
		if(count<=0){
			zkNodeClassLibraryInfoDAO.insert(zkNodeClassLibraryInfo);
//			log.debug("插入zkNodeClassLibraryInfo成功!");
		}
//		log.debug("插入zkNodeData数据完成!");
	}

	@Override
	public List<ZkNodeDataInfo> selectNearestZkData(ZkNodeDataInfo zkNodeData) throws Exception {
		List<ZkNodeDataInfo> zkNodeDatas=zkNodeDataDao.findListByStatementCond("selectNearestZkData", zkNodeData);
		return zkNodeDatas;
	}

	/**
	 * 查询最近固定时间内的每分钟内的节点的数据
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, List<ApplicationInfo>> processZkNodeDataByTimeHM(ZkNodeDataInfo zkNodeData) throws Exception {
		List<ZkNodeDataInfo> zkNodeDatas=selectNearestZkData(zkNodeData);	//获取一段时间内的所有的节点信息
		Map<String,List<ApplicationInfo>> mapZkNodes=new HashMap<>();
		if(zkNodeDatas!=null&&zkNodeDatas.size()>0){
			for(ZkNodeDataInfo zkNode : zkNodeDatas){
				ApplicationInfo applicationInfo=JSONObject.parseObject(zkNode.getNodeData(),ApplicationInfo.class);
				if(applicationInfo!=null&&applicationInfo.getSysInfo()!=null){
					applicationInfo.setUpdateTime(zkNode.getCreatedTimeFormat());
					applicationInfo.setNodeCount(zkNode.getNodeCount());
					if(mapZkNodes.keySet().contains(zkNode.getNodeName())){
						mapZkNodes.get(zkNode.getNodeName()).add(applicationInfo);
					} else{
						List<ApplicationInfo> listApplication=new ArrayList<>();
						listApplication.add(applicationInfo);
						mapZkNodes.put(zkNode.getNodeName(),listApplication);
					}
				}

			}
		}
		return mapZkNodes;
	}

	/**
	 * 查询最近时间内单个系统下单个节点的所有信息
	 * @param zkNodeData
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ApplicationInfo> selectNearestZkDataByParentNameAndNode(ZkNodeDataInfo zkNodeData) throws Exception {
		List<ApplicationInfo> listApplication=new ArrayList<>();
		List<ZkNodeDataInfo> zkNodeDatas=this.selectNearestZkData(zkNodeData);	//获取一段时间内的所有的节点信息
		if(zkNodeDatas!=null&&zkNodeDatas.size()>0){
			ZkNodeClassLibraryInfo zkNodeClassLibraryInfo=zkNodeDataConvertToZkNodeClassLibraryInfo(zkNodeDatas.get(0));
			zkNodeClassLibraryInfo= (ZkNodeClassLibraryInfo) zkNodeClassLibraryInfoDAO.findByStatementCond("selectClassPathByAllCondition", zkNodeClassLibraryInfo);
			for(ZkNodeDataInfo zkNode : zkNodeDatas){
				ApplicationInfo applicationInfo=JSONObject.parseObject(zkNode.getNodeData(),ApplicationInfo.class);
				if(applicationInfo!=null&&applicationInfo.getSysInfo()!=null){
					applicationInfo.setUpdateTime(zkNode.getCreatedTimeFormat());
					applicationInfo.setNodeCount(zkNode.getNodeCount());
					applicationInfo.setNodeName(zkNode.getNodeName());
					applicationInfo.getSysInfo().setClassPath(zkNodeClassLibraryInfo.getClassPath());
					applicationInfo.getSysInfo().setLibraryPath(zkNodeClassLibraryInfo.getLibraryPath());
				}
				listApplication.add(applicationInfo);
			}
		}
		return listApplication;
	}

	/**
	 * 根据系统名称-code获取到系统下所有的节点的最近一条数据
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ApplicationInfo> selectNearestZkOneDataByNode(ZkNodeDataInfo zkNodeData) throws Exception {
		List<ApplicationInfo> listApplication=new ArrayList<>();
		List<ZkNodeDataInfo> zkNodeDatas=zkNodeDataDao.findListByStatementCond("selectNearestZkOneDataByNode", zkNodeData);	//获取一段时间内的所有的节点信息
		if(zkNodeDatas!=null&&zkNodeDatas.size()>0){
			for(ZkNodeDataInfo zkNode : zkNodeDatas){
				ApplicationInfo applicationInfo=JSONObject.parseObject(zkNode.getNodeData(),ApplicationInfo.class);
				if(applicationInfo!=null&&applicationInfo.getSysInfo()!=null){
					applicationInfo.setUpdateTime(zkNode.getCreatedTimeFormat());
					applicationInfo.setNodeCount(zkNode.getNodeCount());
					applicationInfo.setNodeName(zkNode.getNodeName());
				}
				listApplication.add(applicationInfo);
			}
		}
		return listApplication;
	}

	/**
     * 获取当前时间段内所有系统存活的子节点的数量
     * @return
     * @throws Exception
     */
    @Override
    public List<ZkNodeDataInfo> selectSysActiveNodeCount(HttpServletRequest request) throws Exception{
		Map<String,Integer> params=new HashMap<>();
//		String rootNodeName = PropertiesLoad.getProperties("dev.zookeeper.root_node_name");
//
//        String connectUrl = PropertiesLoad.getProperties("dev.zookeeper.connectUrl");
//		try {
////			ZooKeeper zk= new ZooKeeper(connectUrl, Integer.valueOf(PropertiesLoad.getProperties("dev.zookeeper.conn_time_out")), null);
//			ZooKeeper zk=obtainChildrenSyncUsage.getZk();
//			SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
//			assert sessionInfo != null;
//			User user = sessionInfo.getUser();
//			List<SysInfo> userSysInfo=sysInfoService.selectSysByUser(user);
//			if(userSysInfo!=null&&userSysInfo.size()>0){
//                for(SysInfo sysInfo:userSysInfo){
//                    String nodePath="/"+rootNodeName+"/"+sysInfo.getSysName()+"-"+sysInfo.getSysCode();
//                    Stat stat=zk.exists(nodePath,true);
//                    if(stat!=null){
//                        Integer nodeCount=zk.getChildren(nodePath,true).size();
//                        params.put(sysInfo.getSysCode(),nodeCount);
//                    }else{
//                        params.put(sysInfo.getSysCode(),0);
//                    }
//                }
//            }
//			zk.close();
//		} catch (Exception e) {
//			log.error("获取zk中各个系统的存活节点数量出错:{}",e.getMessage());
//		}
		List<ZkNodeDataInfo> nodeDataList=zkNodeDataDao.findListByStatementCond("selectSysActiveNodeCount",null);
        return nodeDataList;
    }

	@Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.zkNodeDataDao = new SimpleGenericDAO<ZkNodeDataInfo, Integer>(sqlSessionTemplate, ZkNodeDataInfo.class);
		this.zkNodeClassLibraryInfoDAO=new SimpleGenericDAO<ZkNodeClassLibraryInfo, Integer>(sqlSessionTemplate, ZkNodeClassLibraryInfo.class);
	}

	private ZkNodeClassLibraryInfo zkNodeDataConvertToZkNodeClassLibraryInfo(ZkNodeDataInfo zkNodeDataInfo) {
		ZkNodeClassLibraryInfo zkNodeClassLibraryInfo=new ZkNodeClassLibraryInfo();
		if (zkNodeDataInfo != null) {
			zkNodeClassLibraryInfo.setParentName(zkNodeDataInfo.getParentName());
			zkNodeClassLibraryInfo.setNodeName(zkNodeDataInfo.getNodeName());
			zkNodeClassLibraryInfo.setLibraryPath(zkNodeDataInfo.getLibraryPath());
			zkNodeClassLibraryInfo.setClassPath(zkNodeDataInfo.getClassPath());
			zkNodeClassLibraryInfo.setNodeDesc(zkNodeDataInfo.getNodeDesc());
		}
		return zkNodeClassLibraryInfo;
	}
}
