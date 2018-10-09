package io.rybalkinsd.kotlinbootcamp.practice

import com.kohttp.ext.eager
import io.rybalkinsd.kotlinbootcamp.practice.client.ChatClient
import io.rybalkinsd.kotlinbootcamp.util.logger
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Ignore
import org.junit.Test

typealias Client = ChatClient

@Ignore
class ChatClientTest {
    companion object {
        private const val MY_NAME_IN_CHAT = "Max"
        private const val PASSWORD = "abcd"
        private const val MY_MESSAGE_TO_CHAT = "aaaaaaaa"
        private val log = logger()
    }

    @Test
    fun register() {
        val response = Client.register(MY_NAME_IN_CHAT, PASSWORD).eager().also { println(it.body) }
        assertTrue(response.code== 200 || response.code == 400)
    }

    @Test
    fun login() {
        val response = Client.login(MY_NAME_IN_CHAT, PASSWORD).eager().also { println(it.body) }
        assertTrue(response.code== 200 || response.code == 400)
    }

    @Test
    fun viewHistory() {
        val response = Client.viewHistory(MY_NAME_IN_CHAT).eager().also { println(it.body) }
        assertTrue(response.code== 200 || response.code == 400)
    }

    @Test
    fun viewOnline() {
        val response = Client.viewOnline(MY_NAME_IN_CHAT).eager().also { println(it.body) }
        assertTrue(response.code== 200 || response.code == 400)
    }

    @Test
    fun say() {
        val response = Client.say(MY_NAME_IN_CHAT, MY_MESSAGE_TO_CHAT).eager().also { println(it.body) }
        assertTrue(response.code== 200 || response.code == 400)
    }

    @Test
    fun logout() {
        val response = Client.logout(MY_NAME_IN_CHAT).eager().also { println(it.body) }
        assertTrue(response.code == 200 || response.code == 400)
    }
}