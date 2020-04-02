package com.pravega.backend.controllers.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FriendController {
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @GetMapping("/friends")
    fun getAllFriends(): List<String> {
        return jdbcTemplate.query("SELECT * FROM friends") { result, _ ->
            result.getString("name")
        }
    }
}