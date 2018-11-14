package io.rybalkinsd.kotlinbootcamp.dao

import io.rybalkinsd.kotlinbootcamp.db.DbConnector
import io.rybalkinsd.kotlinbootcamp.model.toMessage
import io.rybalkinsd.kotlinbootcamp.model.toUser
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test

class MessageDaoTest {

    @Before
    fun setUp() {
        DbConnector
    }


    @Test
    fun `add massage`() {
        DbConnector

        transaction {
            addLogger(StdOutSqlLogger)

            Messages.insert {
                it[id] = 1
                it[user] = 44
                it[time] = DateTime.now()
                it[value] = "My first message"
            }
        }
    }

    @Test
    fun `select all from Mess`() {
        DbConnector
        transaction {
            addLogger(StdOutSqlLogger)

            val users = MessageDao().all

            println(users)
        }
    }

}