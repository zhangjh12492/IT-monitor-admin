package com.wfj.exception.common;

public class SysConstants {

	/*连接超时时间秒*/
	public static int CONN_TIMEOUT_SECOND=10;
	/*连接超时时间毫秒*/
	public static int CONN_TIMEOUT_MS = 10 * 1000;
	/*远程连接netty服务器获取hbase中数据的url*/
	public static final String HBASE_NETTY_URL="hbase_netty_url";
	/**/
	public static final String HBASE_NETTY_MAP_METHOD_NAME="methodName"; 
	/**
	 * websocket 调用方法名称
	 */
	/*获取异常系统首页数据的方法*/
	public static final String SYS_MES_INDEX="sysMesIndex";
	/*获取系统监控信息展示信息的方法*/
	public static final String SYS_MONITOR_INDEX="sysMonitorIndex";
	/*所有系统监控的列表信息*/
	public static final String SYS_MONITOR_LIST="sysMonitorList";
	/*redis缓存中存放的系统监控的数据的条数*/
	public static final Integer REDIS_SYS_MONITOR_SIZE=11;
	/*存放所有的系统存活实例数量的redis key*/
	public static final String SYS_CHILD_NODE_ACTIVE_COUNT="sysChildNodeActiveCount";
	public static final String HBASE_NETTY_MAP_DATA="data";

	public static final String MAIL_SERVER="mail.server";	// 邮件发送SMTP主机

	public static final String MAIL_SENDER="mail.sender";	// 发件人邮箱地址

	public static final String MAIL_NICKNAME="mail.nickname";// 发件人显示昵称

	public static final String MAIL_USERNAME="mail.username";// 发件人邮箱用户名

	public static final String MAIL_PASSWORD="mail.password";// 发件人邮箱密码

	public static final String SMS_URL="sms_url";//短信发送地址
	
}
