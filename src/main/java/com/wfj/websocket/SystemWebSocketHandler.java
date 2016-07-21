package com.wfj.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.ArrayList;


/**
 * 功能描述:
 * 作者: LDL
 * 创建时间: 2014/8/18 10:41
 */
public class SystemWebSocketHandler implements WebSocketHandler {

	private static final Logger logger;

	private static final ArrayList<WebSocketSession> users;

	static {
		users = new ArrayList<WebSocketSession>();
		logger = LoggerFactory.getLogger(SystemWebSocketHandler.class);
	}


	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("connect to the websocket success......");
		users.add(session);
		String userName = (String) session.getAttributes().get(Constants.WEBSOCKET_USERNAME);
//		if (userName != null) {
//			int count = webSocketService.getUnReadNews((String) session.getAttributes().get(Constants.WEBSOCKET_USERNAME));
//			session.sendMessage(new TextMessage(count + ""));
//		}
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

//		sendMessageToUsers();
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		logger.info("websocket connection closed......");
		users.remove(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		logger.info("websocket connection closed......");
		users.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 给所有在线用户发送消息
	 *
	 * @param message
	 */
	public void sendMessageToUsers(TextMessage message) {
		for (WebSocketSession user : users) {
			try {
				if (user.isOpen()) {
					user.sendMessage(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 给所有在线调用的是methodName方法的用户发送消息
	 * @param message
	 */
	public void sendMessageToUsers(TextMessage message,String methodName) {
		for (WebSocketSession user : users) {
			if (((String)user.getAttributes().get(Constants.WEBSOCKET_USERNAME)).endsWith(methodName)){
				try {
					if (user.isOpen()) {
						user.sendMessage(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

	
	/**
	 * 给某个用户发送某种方法的消息
	 *
	 * @param userName
	 * @param message
	 */
	public void sendMessageToUser(String userName, TextMessage message,String methodName) {
		logger.info("user count : "+users.size());
		for (WebSocketSession user : users) {
			if (user.getAttributes().get(Constants.WEBSOCKET_USERNAME).equals(userName)) {
				try {
					if (user.isOpen()) {
						user.sendMessage(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	/**
	 * 给某个用户发送消息
	 *
	 * @param userName
	 * @param message
	 */
	public void sendMessageToUser(String userName, TextMessage message) {
		logger.info("user count : "+users.size());
		for (WebSocketSession user : users) {
			if (user.getAttributes().get(Constants.WEBSOCKET_USERNAME).equals(userName)) {
				try {
					if (user.isOpen()) {
						user.sendMessage(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
}
