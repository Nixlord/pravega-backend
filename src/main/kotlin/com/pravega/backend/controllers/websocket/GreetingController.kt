package com.pravega.backend.controllers.websocket

import com.pravega.backend.models.greeting.GreetingRequest
import com.pravega.backend.models.greeting.GreetingResponse
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    fun greeting(message: GreetingRequest): GreetingResponse {
        return GreetingResponse("Hello ${message.name}");
    }
}
