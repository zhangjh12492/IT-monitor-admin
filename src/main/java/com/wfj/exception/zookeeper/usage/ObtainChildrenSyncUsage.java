package com.wfj.exception.zookeeper.usage;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.common.MailUsedStatus;
import com.wfj.exception.common.SysConstants;
import com.wfj.exception.dal.entity.MailConfigInfo;
import com.wfj.exception.dal.entity.SmsConfigInfo;
import com.wfj.exception.dal.entity.SysInfo;
import com.wfj.exception.dal.service.*;
import com.wfj.exception.util.PropertiesLoad;
import com.wfj.exception.zookeeper.watcher.RegisterWatcher;
import com.wfj.websocket.SystemWebSocketHandler;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

//import com.wfj.exception.zookeeper.watcher.RegisterWatcher;

/**
 * 获取zookeeper对象,watcher zookeeper中系统下的所有getChildren以及children的getDate方法
 *
 * @author ZJHao
 * @ClassName: ObtainChildrenSyncUsage
 * @date 2015-9-8 下午3:32:46
 */
@Component
@Scope(value = "singleton")
public class ObtainChildrenSyncUsage implements ApplicationListener<ContextRefreshedEvent> {


    private static final Logger log = LoggerFactory.getLogger(ObtainChildrenSyncUsage.class);

    @Autowired
    private SysInfoService sysInfoService;
    @Autowired
    private MesEarlyWarnService mesEarlyWarnService;
    @Autowired
    private ZkProcessService zkProcessService;
    @Autowired
    private ZkNodeDataService zkNodeDataService;
    @Autowired
    private SmsConfigInfoService smsConfigInfoService;
    @Autowired
    private MailConfigInfoService mailConfigInfoService;
    private AtomicInteger reconnectionCounts;
    private Thread zkc;
    private Thread m;
    private ZooKeeper zk;
    private Long sessionId;
    private byte[] sessionPassWd;
    private Boolean isMonitorStop = false;
    private Boolean isDebug;
    public static Map<String,Integer> nodeCountMap=new HashMap<>();
    private static final Long DEV_ZOOKEEPER_CONN_TIME_OUT = new Long(10000);

    public static final Integer ZK_RECONNECTION_MAX_COUNT = new Integer(5);

    private static final String DEV_ZOOKEEPER_CONNECTURL = "10.6.2.49:2121";

    private static final String DEV_ZOOKEEPER_ROOT_NODE_NAME = "wfj-omni-channel-server";

    private static String ZK_MONITOR_HEARLTH_THREAD_NAME = "netty-wfj-server-monitor-hearth-thread";

    private static String ZK_MONITOR_ZKCONNECTION_THREAD_NAME = "netty-wfj-server-monitor-zkconnection-thread";

    private static String ZK_CONN_URL = "10.6.2.49:2181";

    private Boolean isReconnection;

    private CountDownLatch cdl = new CountDownLatch(1);

    /**
     * 初始化zookeeper
     *
     * @param sysNameCodes
     * @return void
     * @throws NumberFormatException
     * @throws IOException
     * @throws KeeperException
     * @throws InterruptedException
     * @throws
     * @Title: initZk
     * @author ZJHao
     * @date 2015-9-8 上午11:11:09
     */
    public void initZk(List<String> sysNameCodes) throws Exception {
        String connectUrl = PropertiesLoad.getProperties("dev.zookeeper.connectUrl", this.ZK_CONN_URL);
        String rootNodeName = PropertiesLoad.getProperties("dev.zookeeper.root_node_name", this.DEV_ZOOKEEPER_ROOT_NODE_NAME);
        try {
            if (zk == null)
                zk = new ZooKeeper(connectUrl, Integer.valueOf(PropertiesLoad.getProperties("dev.zookeeper.conn_time_out", this.DEV_ZOOKEEPER_CONN_TIME_OUT + "")), new RegisterWatcher(this));
            if (zk != null && zk.getState() != ZooKeeper.States.CONNECTED) {
                sessionId = zk.getSessionId();
                sessionPassWd = zk.getSessionPasswd();
                for (String sysNameCode : sysNameCodes) {


                    List<String> rootChildSysNames = null;
                    if (zk.exists("/" + rootNodeName, false) != null)
                        rootChildSysNames = zk.getChildren("/" + rootNodeName, false);
                    //判断根节点下此系统是否存在,如果存在接着执行逻辑
                    if (rootChildSysNames != null && rootChildSysNames.size() > 0) {
                        if (rootChildSysNames.contains(sysNameCode)) {
                    /*
                     * 获取到此系统下的所有的子节点
					 */
                            List<String> childrens = zk.getChildren("/" + rootNodeName + "/" + sysNameCode, true);
                            zkProcessService.sendEmailSmsBySysCount(childrens.size(), sysNameCode.substring(sysNameCode.lastIndexOf("-") + 1, sysNameCode.length()));    //调用根据系统下节点存活数量发送短信的接口

                            //2.遍历系统下的子节点
                            if (childrens != null && childrens.size() > 0) {
                                nodeCountMap.put(sysNameCode,childrens.size()); //将系统下节点的数量放入map中
                                for (int i = 0; i < childrens.size(); i++) {
                                    //3.获取到zookeepr中单个节点的数据
                                    byte [] data=zk.getData("/" + rootNodeName + "/" + sysNameCode + "/" + childrens.get(i), true, new Stat());
//                                    ApplicationInfo applicationInfo = JSONObject.parseObject(new String(data), ApplicationInfo.class);
//                                    try {
//                                        zkNodeDataService.insertZkNodeData(SysMetronicUtil.zkNodeDataSysAppInfoConvert(applicationInfo, rootNodeName, sysNameCode, childrens.get(i), childrens.size()));
//                                    } catch (Exception e) {
//                                        log.error("插入zk数据有误,sysNameCode:{},zkNodeName:{},message:{}", sysNameCode, childrens.get(i), e.getMessage());
//                                    }
                                }

//						List<ApplicationShowDataVo> listAppShowData = SysMetronicUtil.convertSysMonitorData(applicationMaps);
//						systemWebSocketHandler().sendMessageToUsers(new TextMessage(JSONObject.toJSONString(listAppShowData)), SysConstants.SYS_MONITOR_INDEX);
//						systemWebSocketHandler().sendMessageToUsers(new TextMessage(JSONObject.toJSONString(applicationMaps)), SysConstants.SYS_MONITOR_INDEX);
                            }
                        } else {
                            log.error("code码为{}的系统下没有没有获取到zookeepr中的节点", sysNameCode);
                        }
                    }
                }
            }
        } catch (ConnectException e) {
            log.error("连接zookeepr异常:{}", e.getMessage());
        } catch (IOException e) {
            log.error("流异常,{}", e.getMessage());
        } catch (NumberFormatException e) {
            log.error("获取到资源文件中的参数出错,{}", e.getMessage());
        }


    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null){//root application context 没有parent，他就是老大. //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
            initMailSmsProperties();    //初始化短信发送地址和邮箱发送地址
            initZkData();//初始化zookeepr节点

        }
    }

    /**
     * 初始化方法,在spring.xml配置bean时调用
     *
     * @return void
     * @throws Exception
     * @throws
     * @Title: initZkData
     * @author ZJHao
     * @date 2015-9-8 上午11:11:37
     */
//    @PostConstruct
    public void initZkData(){
            try {
                List<SysInfo> sysList = sysInfoService.selectAllSys(null);
                if (sysList != null && sysList.size() > 0) {
                    List<String> sysNameCodes = new ArrayList<String>();
                    for (SysInfo sysInfo : sysList) {
                        sysNameCodes.add(sysInfo.getSysName() + "-" + sysInfo.getSysCode());
                    }
                    try {
                        initZk(sysNameCodes);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                log.error("初始化zookeeper出错:{}",e.getMessage());
            }

    }

    /**
     * 初始化邮箱和短信配置
     */
    public void initMailSmsProperties(){
        try {
            SmsConfigInfo smsConfigInfo=new SmsConfigInfo();
            smsConfigInfo.setUsedStatus(MailUsedStatus.USEDING.getCode());
            SmsConfigInfo smsConfigInfo1=smsConfigInfoService.selectSmsInfoByUsedStatus(smsConfigInfo);
            if(smsConfigInfo1!=null){
                PropertiesLoad.putProperties(SysConstants.SMS_URL,smsConfigInfo1.getSmsSendUrl());
                log.info("获取到短信配置:{}", JSONObject.toJSONString(smsConfigInfo));
            }else{
                log.error("未找到合适的短信配置地址");
            }
            MailConfigInfo mailConfigInfo=new MailConfigInfo();
            mailConfigInfo.setUsedStatus(MailUsedStatus.USEDING.getCode());
            MailConfigInfo mailConfigInfo1=mailConfigInfoService.selectMailInfoByUsedStatus(mailConfigInfo);
            if(mailConfigInfo1!=null){
                PropertiesLoad.putProperties(SysConstants.MAIL_NICKNAME,mailConfigInfo1.getMailNickName());
                PropertiesLoad.putProperties(SysConstants.MAIL_PASSWORD,mailConfigInfo1.getMailPassword());
                PropertiesLoad.putProperties(SysConstants.MAIL_SENDER,mailConfigInfo1.getMailSender());
                PropertiesLoad.putProperties(SysConstants.MAIL_SERVER,mailConfigInfo1.getMailServer());
                PropertiesLoad.putProperties(SysConstants.MAIL_USERNAME,mailConfigInfo1.getMailUserName());
            }else{
                log.error("未找到合适的邮箱配置地址");
            }
        } catch (Exception e) {
            log.error("初始化短信配置和邮箱配置出错:{}",e.getMessage());
        }
    }

    /**
     * zk 未连接监控，连续启动5次，时间间隔为 ZK 启动参数的超时时间
     * 若已连接，则监控线程结束
     *
     * @Methods Name zkConCheck
     */
    private void zkConCheck() {

        isReconnection = new Boolean(true);

        if (this.zkc != null && this.zkc.isAlive()) return;
        else if (this.zkc != null && this.zkc.isInterrupted()) {
            this.zkc.start();
        } else {
            this.zkc = new Thread(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    try {
                        while (isReconnection) {
                            //1.判断是否超出最大重试次数
                            Integer maxCountInteger = Integer.valueOf(PropertiesLoad.getProperties("system.seeting.monitor.reconnecte.count", ObtainChildrenSyncUsage.ZK_RECONNECTION_MAX_COUNT + ""));
                            if (reconnectionCounts.get() >= maxCountInteger) {
                                log.info("Zk Checker Service Was Great Max Reconnection Count, Stop It Now.....");
                                shutdownHealth("Disable");
                            } else {
                                //2.重试次数+1
                                reconnectionCounts.incrementAndGet();
                                //3.开始重连
                                reconnection();
                            }
                            //测试用5秒，正式env.setting.server.monitor.checker.sleeptime
                            zkc.sleep(new Integer(PropertiesLoad.getProperties("env.setting.server.monitor.checker.sleeptime")).intValue());
                        }
                        log.debug("Zk Checker Service is Stop!");
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        log.error(PropertiesLoad.getProperties("env.setting.server.error.00001016"));
                        log.error("Details: " + e.getMessage());
                        Thread.currentThread().interrupt();
                    }
                }
            }, this.ZK_MONITOR_ZKCONNECTION_THREAD_NAME);

            zkc.start();
        }
    }

    public void shutdownHealth(String status) {


        // 1.停止zk 链接监控线程
        if (this.zkc != null) {
            this.isReconnection = false;
        }
        //2.停止实例信息获取模块
        if (this.m != null) {
            this.isMonitorStop = true;
        }

        //3.重置监控状态
        this.isReconnection = new Boolean(false);
        this.isMonitorStop = false;
        this.reconnectionCounts = new AtomicInteger();
        this.cdl = new CountDownLatch(1);
        //4.销毁服务监控服务
        try {
            if (zk != null && zk.getState() != ZooKeeper.States.CLOSED) {
                zk.close();
                zk = null;
            } else {
                zk = null;
            }
            log.info("Health Monitor All of Stoped!");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            log.error(PropertiesLoad.getProperties("env.setting.server.error.00001017"));
            log.error("Details: " + e.getMessage());
        }

    }

    public void reconnection() {

        if (isDebug) return;

        try {
            int timeout = new Integer(PropertiesLoad.getProperties("system.seeting.monitor.timeout")).intValue();

//            if (zk != null && zk.getState().isConnected()) {
//                return;
//            } else if (zk != null && zk.getState().isAlive()) {
//                zk = new ZooKeeper(PropertiesLoad.getProperties("system.seeting.monitor.url"), timeout, new RegisterWatcher(this), sessionId, sessionPassWd);
//
//            } else if (zk != null && zk.getState() == ZooKeeper.States.CLOSED) {
//                zk = new ZooKeeper(PropertiesLoad.getProperties("system.seeting.monitor.url"), timeout, new RegisterWatcher(this));
//            } else {
//                zk = new ZooKeeper(PropertiesLoad.getProperties("system.seeting.monitor.url"), timeout, new RegisterWatcher(this));
//            }

            this.cdl.await(timeout, TimeUnit.MILLISECONDS);
            sessionId = zk.getSessionId();
            sessionPassWd = zk.getSessionPasswd();
            log.info("ZK Restate(" + sessionId + ") is(" + this.reconnectionCounts + "): " + zk.getState().toString());

            if (zk.getState() != ZooKeeper.States.CONNECTED) {
                log.info("ZK Restate Seting Close............. ");
                this.cdl = new CountDownLatch(1);
                zk.close();
                isReconnection = new Boolean(true);
                if (this.zkc != null && this.zkc.isAlive()) {
                    return;
                } else if (this.zkc != null && this.zkc.isInterrupted()) {
                    zkc.start();
                } else {
                    zkConCheck();
                }
                return;
            } else {
                log.info("Hearth Monitor Service is Restarted.......");
                isReconnection = new Boolean(false);
                // 1. 建立基础监控节点信息
//                buildMonitorRootInfo("Active");


            }

            // connectedSemaphore.await();

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            log.error(PropertiesLoad.getProperties("env.setting.server.error.00001009"));
            log.error("Details: " + e.getMessage());
            zkConCheck();
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            log.error(PropertiesLoad.getProperties("env.setting.server.error.00001006"));
            log.error("Details: " + e.getMessage());
            zkConCheck();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error(PropertiesLoad.getProperties("env.setting.server.error.00001007"));
            log.error("Details: " + e.getMessage());
            zkConCheck();
        }
    }

    /**
     * 获取所有系统的展示信息
     *
     * @return Map<String,List<ApplicationShowDataVo>>
     * @throws
     * @Title: getAllAppShowData
     * @author ZJHao
     * @date 2015-9-13 下午11:05:30
     */
//	public Map<String ,List<ApplicationShowDataVo>> getAllAppShowData(){
//		Map<String ,List<ApplicationShowDataVo>> 
//	}
//    @Bean
    public SystemWebSocketHandler systemWebSocketHandler() {
        return new SystemWebSocketHandler();
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public byte[] getSessionPassWd() {
        return sessionPassWd;
    }

    public void setSessionPassWd(byte[] sessionPassWd) {
        this.sessionPassWd = sessionPassWd;
    }

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public SysInfoService getSysInfoService() {
        return sysInfoService;
    }

    public ObtainChildrenSyncUsage setSysInfoService(SysInfoService sysInfoService) {
        this.sysInfoService = sysInfoService;
        return this;
    }

    public MesEarlyWarnService getMesEarlyWarnService() {
        return mesEarlyWarnService;
    }

    public ObtainChildrenSyncUsage setMesEarlyWarnService(MesEarlyWarnService mesEarlyWarnService) {
        this.mesEarlyWarnService = mesEarlyWarnService;
        return this;
    }

    public ZkProcessService getZkProcessService() {
        return zkProcessService;
    }

    public ObtainChildrenSyncUsage setZkProcessService(ZkProcessService zkProcessService) {
        this.zkProcessService = zkProcessService;
        return this;
    }

    public ZkNodeDataService getZkNodeDataService() {
        return zkNodeDataService;
    }

    public ObtainChildrenSyncUsage setZkNodeDataService(ZkNodeDataService zkNodeDataService) {
        this.zkNodeDataService = zkNodeDataService;
        return this;
    }

    public static void main(String[] args) {
        String time = "2015-9-11 10:36:53";
        System.out.println(time.substring(time.indexOf(" ") + 1, time.lastIndexOf(":")));

        System.out.println(((double) (100000L - 80000)) / 34 / 34);
    }



}
