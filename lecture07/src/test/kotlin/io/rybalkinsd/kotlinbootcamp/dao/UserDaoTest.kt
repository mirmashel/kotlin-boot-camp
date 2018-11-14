package io.rybalkinsd.kotlinbootcamp.dao

import io.rybalkinsd.kotlinbootcamp.db.DbConnector
import io.rybalkinsd.kotlinbootcamp.model.toUser
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Before
import org.junit.Test

class UserDaoTest {

    @Before
    fun setUp() {
        DbConnector
    }

    @Test
    fun `insert user`() {
        transaction {
            addLogger(StdOutSqlLogger)

            val id = Users.insert {
                it[id] = 10
                it[login] = "login22"
            } get Users.id

            println(id)
        }
    }

    @Test
    fun `select all from User`() {
        transaction {
            addLogger(StdOutSqlLogger)

            val users = UserDao().all

            println(users)
        }
    }

    @Test
    fun `select Where from User`() {
        transaction {
            addLogger(StdOutSqlLogger)

            val users = UserDao().getAllWhere("id>5")

            println(users)
        }
    }


}