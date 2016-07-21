package com.wfj.exception.zookeeper.watcher;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.dal.entity.MesEarlyWarnInfo;
import com.wfj.exception.dal.service.MesEarlyWarnService;
import com.wfj.exception.zookeeper.dto.ApplicationInfo;
import com.wfj.exception.zookeeper.usage.ObtainChildrenSyncUsage;
import com.wfj.exception.zookeeper.util.SysMetronicUtil;
import com.wfj.websocket.SystemWebSocketHandler;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.List;

/**
 * zookeeper的watcher注册类,并且获取watcher的NodeDataChanged、NodeChildrenChanged事件
 * @ClassName: RegisterWatcher
 * @author ZJHao
 * @date 2015-9-8 下午3:35:22
 *
 */
public class RegisterWatcher implements Watcher {

	private Logger log = LoggerFactory.getLogger(RegisterWatcher.class);

	private ObtainChildrenSyncUsage obtainChildrenSyncUsage;
	@Resource
	private MesEarlyWarnService mesEarlyWarnService;

	public RegisterWatcher(ObtainChildrenSyncUsage obtainChil) {
		this.obtainChildrenSyncUsage = obtainChil;
	}

	private ZooKeeper zk;

	public RegisterWatcher() {
	}

	@Override
	public void process(WatchedEvent event) {
		try {
			log.debug("registerWatcher.process.event : {}", event);
			if(event.getPath()!=null){
				if (event.getState() == KeeperState.SyncConnected) {
					if (EventType.NodeDataChanged == event.getType()  ) {
						String [] eventPaths=event.getPath().split("/");//path的格式,/root/sys-code/sys-children-code	(rootName/parentName/nodeName)
						//如果是某一系统的某个节点中的数据改变了，1.获取到内存中的此系统的数据
						//3.将节点下的数据插入数据库
						byte[] data = obtainChildrenSyncUsage.getZk().getData(event.getPath(), true, new Stat());
						ApplicationInfo applicationInfo = JSONObject.parseObject(new String(data), ApplicationInfo.class);
						//插入数据库
						Integer sysNodeCount=ObtainChildrenSyncUsage.nodeCountMap.get(eventPaths[2]);
						if(sysNodeCount==null){
							sysNodeCount=1;
						}
						obtainChildrenSyncUsage.getZkNodeDataService().insertZkNodeData(SysMetronicUtil.zkNodeDataSysAppInfoConvert(applicationInfo, eventPaths[1], eventPaths[2], eventPaths[3],sysNodeCount));
						//调用websocket发送信息
//						systemWebSocketHandler().sendMessageToUsers(new TextMessage(JSONObject.toJSONString(SysMetronicUtil.convertSysMonitorData(applicationMaps,mesEarlyWarnInfo))),SysConstants.SYS_MONITOR_INDEX);
//						log.info("redisKey    :     {}",redisKey);
//						systemWebSocketHandler().sendMessageToUsers(new TextMessage(JSONObject.toJSONString(SysMetronicUtil.getSysMonitorListData(applicationMaps,redisKey,mesEarlyWarnInfo))),SysConstants.SYS_MONITOR_LIST);
					} else if (EventType.NodeChildrenChanged == event.getType() ) {
						log.info("zk节点改变,{}",event.getPath());
						String [] eventPaths=event.getPath().split("/");//path的格式,/root/sys-code	(rootName/parentName)
						List<String> childrens = obtainChildrenSyncUsage.getZk().getChildren(event.getPath(), true);	//获取到改变子节点数量的系统的所有子节点
						obtainChildrenSyncUsage.getZkProcessService().sendEmailSmsBySysCount(childrens.size(), eventPaths[2].substring(eventPaths[2].lastIndexOf("-") + 1, eventPaths[2].length()));	//调用根据系统下节点存活数量发送短信的接口

						if (childrens != null && childrens.size() > 0) {
							ObtainChildrenSyncUsage.nodeCountMap.put(eventPaths[2],childrens.size());
							for (int i = 0; i < childrens.size(); i++) {
								byte[] data = obtainChildrenSyncUsage.getZk().getData(event.getPath() + "/" + childrens.get(i), true, new Stat());
//								ApplicationInfo applicationInfo = JSONObject.parseObject(new String(data), ApplicationInfo.class);
//								obtainChildrenSyncUsage.getZkNodeDataService().insertZkNodeData(SysMetronicUtil.zkNodeDataSysAppInfoConvert(applicationInfo, eventPaths[1], eventPaths[2], childrens.get(i),childrens.size()));
							}
							//调用websocket发送信息
//							systemWebSocketHandler().sendMessageToUsers(new TextMessage(JSONObject.toJSONString(SysMetronicUtil.convertSysMonitorData(applicationMaps,mesEarlyWarnInfo))),SysConstants.SYS_MONITOR_INDEX);
//							log.info("redisKey    :     {}",redisKey);
//							systemWebSocketHandler().sendMessageToUsers(new TextMessage(JSONObject.toJSONString(SysMetronicUtil.getSysMonitorListData(applicationMaps,redisKey,mesEarlyWarnInfo))),SysConstants.SYS_MONITOR_LIST);	//系统监控界面的列表信息
						}
					}
				}
			}
		} catch (KeeperException e) {
//			try {
//				if (!obtainChildrenSyncUsage.getZk().getState().equals(ZooKeeper.States.CONNECTED))
//					obtainChildrenSyncUsage.initZkData();
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
			e.printStackTrace();
		} catch (InterruptedException e) {
//			try {
//				if (!obtainChildrenSyncUsage.getZk().getState().equals(ZooKeeper.States.CONNECTED))
//					obtainChildrenSyncUsage.initZkData();
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
			e.printStackTrace();
		} catch (Exception e) {
//			try {
//				if (!obtainChildrenSyncUsage.getZk().getState().equals(ZooKeeper.States.CONNECTED))
//					obtainChildrenSyncUsage.initZkData();
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
			e.printStackTrace();
		}
	}

	public MesEarlyWarnInfo getMesEaryWarnInf(String redisKey) throws Exception{
		List<MesEarlyWarnInfo> listMesEarlyWarn=obtainChildrenSyncUsage.getMesEarlyWarnService().findMesEarlyInfoBySysCode(redisKey.substring(redisKey.lastIndexOf("-")+1,redisKey.length()));
		MesEarlyWarnInfo mesEarlyWarnInfo=null;
		if(listMesEarlyWarn!=null&listMesEarlyWarn.size()>0){
			mesEarlyWarnInfo=listMesEarlyWarn.get(0);
		}
		return mesEarlyWarnInfo;
	}

	public ZooKeeper getZk() {
		return zk;
	}

	public void setZk(ZooKeeper zk) {
		this.zk = zk;
	}

	public ObtainChildrenSyncUsage getObtainChildrenSyncUsage() {
		return obtainChildrenSyncUsage;
	}

	public void setObtainChildrenSyncUsage(ObtainChildrenSyncUsage obtainChildrenSyncUsage) {
		this.obtainChildrenSyncUsage = obtainChildrenSyncUsage;
	}

	public MesEarlyWarnService getMesEarlyWarnService() {
		return mesEarlyWarnService;
	}
	public void setMesEarlyWarnService(MesEarlyWarnService mesEarlyWarnService) {
		this.mesEarlyWarnService = mesEarlyWarnService;
	}

//	@Bean
	public SystemWebSocketHandler systemWebSocketHandler() {
		return new SystemWebSocketHandler();
	}
}
