package com.pravega.backend.controllers

import org.springframework.core.AttributeAccessor
import org.springframework.integration.core.MessagingTemplate
import org.springframework.integration.websocket.IntegrationWebSocketContainer
import org.springframework.integration.websocket.WebSocketListener
import org.springframework.integration.websocket.inbound.WebSocketInboundChannelAdapter
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import reactor.netty.http.websocket.WebsocketInbound

class WebSocketHandler: TextWebSocketHandler() {
    override fun handleTransportError(session: WebSocketSession, exception: Throwable) {
        super.handleTransportError(session, exception)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        super.afterConnectionClosed(session, status)
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        super.handleTextMessage(session, message)
        println("WEBSOCKET SERVER");
        print(message.toString())

        session.sendMessage(TextMessage(message.payload))
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        super.afterConnectionEstablished(session)
    }
}
