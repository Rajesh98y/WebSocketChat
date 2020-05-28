package com.rajesh.websocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.rajesh.websocket.model.ChatMessage;
import com.rajesh.websocket.model.MessageType;

@Component
public class WebSocketListener {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(WebSocketListener.class);

	@Autowired
	private SimpMessageSendingOperations sendingOperations;  
	
	
	@EventListener
	public void handleWebSocketConnectListener(final SessionConnectedEvent event) {
		LOGGER.info("bing bang bing.. we have new cheky little connection");
	}
	
	
	
	@EventListener
	public void handleWebSocketDisConnectListener(final SessionDisconnectEvent event) {
		
		final StompHeaderAccessor headerAccessor=StompHeaderAccessor.wrap(event.getMessage());
		final String username=(String )headerAccessor.getSessionAttributes().get("username");
		
		final ChatMessage chatMessage=ChatMessage.builder()
				.type(MessageType.DISCONNECT)
				.sender(username)
				.build();
		
		sendingOperations.convertAndSend("/topic/public",chatMessage);
	}
	
	
}
