package com.wfj.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 功能描述:
 * 作者: LDL
 * 创建时间: 2014/8/18 15:40
 */
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

	private String invokeMethod;

	public WebSocketHandshakeInterceptor(String invokeMethod) {
		this.invokeMethod = invokeMethod;
	}

	private static final Logger log = LoggerFactory.getLogger(WebSocketHandshakeInterceptor.class);

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		log.info("before hadndshake");
		if (request instanceof ServletServerHttpRequest) {
			log.info("1......");
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			String name = getIpAddr(servletRequest.getServletRequest());
			System.out.println(name + "     name");
//			servletRequest.getServletRequest().getSession().setAttribute(Constants.WEBSOCKET_USERNAME, name+"_" + invokeMethod);
//			HttpSession session = servletRequest.getServletRequest().getSession();
//			if (session != null) {
//				//使用userName区分WebSocketHandler，以便定向发送消息
//				String userName = (String) session.getAttribute(Constants.WEBSOCKET_USERNAME);
				String userName = name+"_"+invokeMethod;
				System.out.println("userName              :     " + userName);
				attributes.put(Constants.WEBSOCKET_USERNAME, userName);
				log.info("2.......");
//			}
		}
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
		log.info("after  Handshake");
	}

	//获取ip
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader(" x-forwarded-for ");
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" WL-Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
