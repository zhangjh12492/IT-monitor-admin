package com.wfj.websocket;

import com.wfj.exception.common.SysConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 功能描述:
 * 作者: LDL
 * 创建时间: 2014/8/18 10:31
 */
//@Configuration
//@EnableWebMvc
//@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    private static final Logger log = LoggerFactory.getLogger(WebSocketConfig.class);

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        log.info("\nserver is start");
        registry.addHandler(systemWebSocketHandler(), "/" + SysConstants.SYS_MES_INDEX + ".do")
                .addInterceptors(new WebSocketHandshakeInterceptor(SysConstants.SYS_MES_INDEX));
        registry.addHandler(systemWebSocketHandler(), "/" + SysConstants.SYS_MONITOR_INDEX + ".do")
                .addInterceptors(new WebSocketHandshakeInterceptor(SysConstants.SYS_MONITOR_INDEX));
        registry.addHandler(systemWebSocketHandler(), "/" + SysConstants.SYS_MONITOR_LIST + ".do")
                .addInterceptors(new WebSocketHandshakeInterceptor(SysConstants.SYS_MONITOR_LIST));

//		registry.addHandler(systemWebSocketHandler(), "sockjs/webSocketServer").addInterceptors(new WebSocketHandshakeInterceptor()).withSockJS();
    }

//	@Bean
	public WebSocketHandler systemWebSocketHandler() {
		return new SystemWebSocketHandler();
	}

}
