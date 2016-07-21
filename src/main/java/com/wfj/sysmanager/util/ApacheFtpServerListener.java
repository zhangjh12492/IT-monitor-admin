package com.wfj.sysmanager.util;

//import org.apache.ftpserver.ftplet.FtpException;
//import org.apache.ftpserver.impl.DefaultFtpServer;
//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 项目启动的时候,就开启ftp服务器
 * 
 * @author 孙宇
 * 
 */
public class ApacheFtpServerListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(ApacheFtpServerListener.class);
	private static final String FTPSERVER_CONTEXT_NAME = "FTPSERVER_CONTEXT_NAME";

	public void contextDestroyed(ServletContextEvent sce) {
/*
		logger.info("正在停止FTP服务....");
		DefaultFtpServer server = (DefaultFtpServer) sce.getServletContext().getAttribute(FTPSERVER_CONTEXT_NAME);
		if (server != null) {
			server.stop();
			sce.getServletContext().removeAttribute(FTPSERVER_CONTEXT_NAME);
			logger.info("FTP服务已停止!");
		} else {
			logger.info("停止FTP服务出错!");
		}
*/
	}

	public void contextInitialized(ServletContextEvent sce) {
/*
		logger.info("正在开启FTP服务....");
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		DefaultFtpServer server = (DefaultFtpServer) ac.getBean("apacheFtpServer");
		sce.getServletContext().setAttribute(FTPSERVER_CONTEXT_NAME, server);
		try {
			server.start();
			logger.info("FTP服务已开启!");
		} catch (FtpException e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
*/
	}

}
