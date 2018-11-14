package io.rybalkinsd.kotlinbootcamp.dao

import io.rybalkinsd.kotlinbootcamp.model.Message
import io.rybalkinsd.kotlinbootcamp.model.toMessage
import io.rybalkinsd.kotlinbootcamp.model.toUser
import kotlinx.coroutines.selects.select
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.reflect.KProperty

class MessageDao : Dao<Message> {
    override val all: List<Message>
        get() = transaction {
            Messages.selectAll().map {
                it.toMessage()
            }
        }

    fun String.split1():  List<String> =
            if (this.contains("="))
                listOf(this.substringBefore("="), "=", this.substringAfter("="))
            else if (this.contains(">="))
                listOf(this.substringBefore(">="), ">=", this.substringAfter(">="))
            else if (this.contains("<="))
                listOf(this.substringBefore("<="), "<=", this.substringAfter("<="))
            else if (this.contains(">"))
                listOf(this.substringBefore(">"), ">", this.substringAfter(">"))
            else if (this.contains("<"))
                listOf(this.substringBefore("<"), "<", this.substringAfter("<"))
            else if (this.contains("!="))
                listOf(this.substringBefore("!="), "!=", this.substringAfter("!="))
            else
                emptyList()

    override fun getAllWhere(vararg conditions: String): List<Message> {
        val str = conditions.map {
            it.split1()
        }
        return Users.select {
            str.map {
                if (it[0] == "id") {
                    val a = Messages.id
                    when (it[1]) {
                        "=" -> a eq it[2].toInt()
                        ">=" ->a greaterEq it[2].toInt()
                        "<=" -> a lessEq it[2].toInt()
                        ">" ->a greater it[2].toInt()
                        "<" -> a less it[2].toInt()
                        "!=" ->a neq it[2].toInt()
                        else -> throw Exception("id")
                    }
                } else if (it[0] == "user") {
                    val a = Messages.user
                    when (it[1]) {
                        "=" -> a eq it[2].toInt()
                        ">=" ->a greaterEq it[2].toInt()
                        "<=" -> a lessEq it[2].toInt()
                        ">" ->a greater it[2].toInt()
                        "<" -> a less it[2].toInt()
                        "!=" ->a neq it[2].toInt()
                        else -> throw Exception("user")
                    }
                } else throw Exception("lol")
            }.reduce { res, it ->
                res and it
            }
        }.map {
            it.toMessage()
        }
    }

    override fun insert(t: Message) {
        transaction {
            Messages.insert {
                it[Messages.id] = t.id
                it[Messages.value] = t.value
                it[Messages.time] = t.timestamp
                it[Messages.user] = t.user.id
            }
        }
    }

}
/*

class allDel
{
    val all = emptyList<Message>()
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = all

}*/
