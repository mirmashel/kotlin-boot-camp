package io.rybalkinsd.kotlinbootcamp.practice.client

import com.kohttp.dsl.httpGet
import com.kohttp.dsl.httpPost
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

object ChatClient {
    // Change to server url
    private const val HOST = "localhost"
    private const val PORT = 8080

    /**
     * POST /chat/login?name=my_name
     */
    fun register(name: String, password: String) = httpPost {
            host = HOST
            port = PORT
            path = "/chat/register"
            body {
                form {
                    "name" to name
                    "password" to password
                }
            }
        }

    fun login(name: String, password: String) = httpPost {
        host = HOST
        port = PORT
        path = "/chat/login"
        body {
            form {
                "name" to name
                "password" to password
            }
        }
    }

    /**
     * GET /chat/history
     */
    fun viewHistory(name: String): Response = httpGet {
        host = HOST
        port = PORT
        path = "/chat/chat"
        param {
            "name" to name
        }
    }

    /**
     * POST /chat/say
     *
     * Body: "name=my_name&msg='my_message'"
     */
    fun say(name: String, msg: String): Response = httpPost {
        host = HOST
        port = PORT
        path = "/chat/say"
        body {
            form {
                "name" to name
                "msg" to msg
            }
        }
    }

    /**
     * GET /chat/online
     */
    fun viewOnline(name: String): Response = httpGet {
        host = HOST
        port = PORT
        path = "/chat/online"
        param {
            "name" to name
        }
    }

    /**
     * POST /chat/logout?name=my_name
     */
    fun logout(name: String): Response {
        val request = Request.Builder().apply {
            url("http://$HOST:$PORT/chat/logout?name=$name")
            delete()
        }.build()
        return OkHttpClient
                .Builder()
                .build()
                .newCall(request)
                .execute()
    }
}
