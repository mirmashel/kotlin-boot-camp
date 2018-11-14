package io.rybalkinsd.kotlinbootcamp.dao

import com.sun.org.apache.bcel.internal.generic.Select
import com.sun.org.apache.xpath.internal.operations.Bool
import io.rybalkinsd.kotlinbootcamp.model.User
import io.rybalkinsd.kotlinbootcamp.model.toUser
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import javax.jws.soap.SOAPBinding

class UserDao : Dao<User> {

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


    override val all: List<User>
        get() = transaction {
            Users.selectAll().map { it.toUser() }
        }

    override fun getAllWhere(vararg conditions: String): List<User> {
        val str = conditions.map {
            it.split1()
        }
        return Users.select {
            str.map {
                if (it[0] == "id") {
                    val a = Users.id
                    when (it[1]) {
                        "=" -> a eq it[2].toInt()
                        ">=" ->a greaterEq it[2].toInt()
                        "<=" -> a lessEq it[2].toInt()
                        ">" ->a greater it[2].toInt()
                        "<" -> a less it[2].toInt()
                        "!=" ->a neq it[2].toInt()
                        else -> throw Exception("idus")
                    }
                }
                else if (it[0] == "login") {
                    val a = Users.login
                    when (it[1]) {
                        "=" -> a eq it[2]
                        else -> throw Exception("login")
                    }
                } else throw Exception("ppc")
            }.reduce { res, it ->
                res and it
            }
        }.map {
            it.toUser()
        }
    }

    override fun insert(t: User) {
        transaction {
            Users.insert {
                it[Users.id] = t.id
                it[Users.login] = t.login
            }
        }
    }

}
