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
    fun getAllFriends(): List<Pair<String, Int>> {
        return jdbcTemplate.query("SELECT * FROM friends ORDER BY age desc") { result, _ ->
            Pair(result.getString("name"), result.getInt("age"))
        }
    }
}