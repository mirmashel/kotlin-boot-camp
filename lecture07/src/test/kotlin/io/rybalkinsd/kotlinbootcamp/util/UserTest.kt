package io.rybalkinsd.kotlinbootcamp.util

import junit.framework.TestCase.assertEquals
import org.junit.Test

class UserTest {
    @Test
    fun `ch name`() {
        assertEquals("Alice", User().name);
    }
    @Test
    fun `wrong age`() {
        val bob = User(). apply {
            name =  "Bob"
            age = -1
        }
        assertEquals(0, bob.age)
    }
}
