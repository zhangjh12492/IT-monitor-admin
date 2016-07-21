package com.wfj.exception.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 
 *
 */
@Component
public class SystemBootstrap implements InitializingBean{
	/**
	 * CONFIG_FILE_PATH 系统变量配置文件路径
	 */
	private static final String CONFIG_FILE_PATH_NETTY = "/netty.properties";
	private static final String CONFIG_FILE_PATH_RABBITMQ_ = "/rabbitmq.properties";
	private static final String CONFIG_FILE_PATH_CONFIG_ = "/config.properties";

	
	@Override
	public void afterPropertiesSet() {
		System.out.print("afterPropertiesSet----------------------");
		InputStream inputStream = null;
		InputStream inputStream_mq = null;
		InputStream inputStream_config = null;
		Properties properties = new Properties();
		Properties properties_mq = new Properties();
		Properties properties_config = new Properties();
		try {
			inputStream = SystemBootstrap.class.getResourceAsStream(CONFIG_FILE_PATH_NETTY);
			properties.load(inputStream);
            inputStream_mq = SystemBootstrap.class.getResourceAsStream(CONFIG_FILE_PATH_RABBITMQ_);
            properties_mq.load(inputStream_mq);
            inputStream_config = SystemBootstrap.class.getResourceAsStream(CONFIG_FILE_PATH_CONFIG_);
            properties_config.load(inputStream_config);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		PropertiesLoad.init(properties);
		PropertiesLoad.init(properties_mq);
		PropertiesLoad.init(properties_config);
	}
}
