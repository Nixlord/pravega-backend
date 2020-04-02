package com.pravega.backend.controllers.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.HashMap

@RestController
class FriendController {
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @GetMapping("/friends")
    fun getAllFriends(): HashMap<String, Pair<String, Int>> {
        // Write better Kotlin code
        val results = hashMapOf<String, Pair<String, Int>>()
        jdbcTemplate.query("SELECT * FROM friends ORDER BY age desc") { result, _ ->
            results[UUID.randomUUID().toString()] = Pair(result.getString("name"), result.getInt("age"))
        }
        return results;
    }
}