package io.rybalkinsd.kotlinbootcamp

import org.junit.Test

class DataAnalysisTest {

/*    @Test
    fun ww() {
        val str = RawProfile("""
            firstName=Dent,
            lastName=kent,
            source=linkedin
            age=lol
            """.trimIndent())
        print_profile(str.person())
    }


    @Test
    fun otladka() {
        for (i in listofProfiles)
            print_profile(i)
    }*/

    @Test
    fun `check avg age`() {
        println("Average ages")
        avgAge.forEach { println("${it.key}: ${it.value}") }
    }

    @Test
    fun `check grouped profiles`() {
        groupedProfiles.forEach { x -> println("id: ${x.key}"); x.value.forEach { y -> print_profile(y) } }
    }
}