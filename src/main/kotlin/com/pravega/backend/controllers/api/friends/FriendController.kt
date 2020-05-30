package com.pravega.backend.controllers.api.friends

import com.impossibl.postgres.api.jdbc.PGConnection
import com.impossibl.postgres.api.jdbc.PGNotificationListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.HashMap

@RestController
class FriendController() {
    @Autowired
    var connection: PGConnection? = null

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @GetMapping("/friends")
    fun getAllFriends(): HashMap<Int, Friend> {
        // Write better Kotlin code
        val results = hashMapOf<Int, Friend>()
        var id = 0
        jdbcTemplate.query("SELECT * FROM friends ORDER BY age desc") { result, _ ->
            results[id] = Friend(result.getString("name"), result.getInt("age"))
            id += 1
        }
        return results;
    }

    @EventListener
    fun onReady(event: ApplicationReadyEvent) {
        println("OnReady")
        if (connection == null) {
            println("Null connection")
        }
        connection?.apply {
            createStatement().apply {
                execute("LISTEN new_friend")
                close()
            }
            addNotificationListener(
                    object : PGNotificationListener {
                        override fun notification(processId: Int, channelName: String?, payload: String?) {
                            super.notification(processId, channelName, payload)
                            println(channelName)
                            println(payload)
                        }
                    }
            )
        }
    }
}