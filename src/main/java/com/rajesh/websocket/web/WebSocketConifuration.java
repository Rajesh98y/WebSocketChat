package com.rajesh.websocket.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConifuration implements WebSocketMessageBrokerConfigurer {

//	private static final String CHAT_ENDPOINT = "/chat";

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chat-example").withSockJS();
	}

//	@Bean
//	public ChatWebSocketHandler getChatWebSocketHandler() {
//		return new ChatWebSocketHandler();
//	}

	@Override
	public void configureMessageBroker(final MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app");
		registry.enableSimpleBroker("/topic");
	}
	
}
