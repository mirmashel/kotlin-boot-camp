package io.rybalkinsd.kotlinbootcamp

import java.util.Random

class RawProfile(val rawData: String)

/**
 * Here are Raw profiles to analyse
 */
val rawProfiles = listOf(
        RawProfile("""
            firstName=Dent,
            lastName=kent,
            age=88,
            source=linkedin
            """.trimIndent()
        ),
        RawProfile("""
            firstName=Dent,
            lastName=kent,
            age=88,
            source=vk
            """.trimIndent()
        ),
        RawProfile("""
            firstName=Dent,
            lastName=kent,
            age=88,
            source=facebook
            """.trimIndent()
        ),
        RawProfile("""
            firstName=Dent,
            lastName=kent,
            age=88,
            source=linkedin
            """.trimIndent()
        ),
        RawProfile("""
            firstName=Dent,
            lastName=kent,
            age=88,
            source=vk
            """.trimIndent()
        ),
        RawProfile("""
            firstName=Dent,
            lastName=kent,
            age=88,
            source=facebook
            """.trimIndent()
        ),
        RawProfile("""
            firstName=alice,
            age=16,
            source=facebook
            """.trimIndent()
        ),
        RawProfile("""
            firstName=Dent,
            lastName=kent,
            age=88,
            source=linkedin
            """.trimIndent()
        ),
        RawProfile("""
            firstName=Dent,
            lastName=kent,
            age=88,
            source=vk
            """.trimIndent()
        ),
        RawProfile("""
            firstName=alla,
            lastName=OloOlooLoasla,
            source=vk
            """.trimIndent()
        ),
        RawProfile("""
            firstName=bella,
            age=36,
            source=vk
            """.trimIndent()
        ),
        RawProfile("""
            firstName=angel,
            age=I will not tell you =),
            source=facebook
            """.trimIndent()
        ),
        RawProfile(
                """
            lastName=carol,
            source=vk
            age=49,
            """.trimIndent()
        ),
        RawProfile("""
            firstName=bob,
            lastName=John,
            age=47,
            source=linkedin
            """.trimIndent()
        ),
        RawProfile("""
            lastName=KENT,
            firstName=dent,
            age=88,
            source=facebook

            """.trimIndent()
        ),
        RawProfile("""
            firstName=lulz
            age=1
            source=facebook
        """.trimIndent())
)

enum class DataSource {
    FACEBOOK,
    VK,
    LINKEDIN
}

sealed class Profile(
    var id: Long,
    var firstName: String? = null,
    var lastName: String? = null,
    var age: Int? = null,
    var dataSource: DataSource
)

fun equals_profile(a: Profile, b: Profile) =
        b.firstName == a.firstName && b.lastName == a.lastName && b.age == a.age

fun print_profile(p: Profile) {
    println(p.id)
    println(p.firstName)
    println(p.lastName)
    println(p.age)
    println(p.dataSource)
    println()
}

/**
 * Task #1
 * Declare classes for all data sources
 */
class FacebookProfile(id: Long) : Profile(dataSource = DataSource.FACEBOOK, id = id)
class VKProfile(id: Long) : Profile(dataSource = DataSource.VK, id = id)
class LinkedInProfile(id: Long) : Profile(dataSource = DataSource.LINKEDIN, id = id)

fun values(str: String, wh: String): String? {
    var i = 0
    while (i < str.length - wh.length) {
        if (str.slice(i until (i + wh.length)) == wh) {
            var res = ""
            i += wh.length
            while (i < str.length && (str[i].isLetterOrDigit() || str[i] == '_'))
                res += str[i++].toString()
            return res
        }
        i++
    }
    return null
}

class RandId {
    companion object {
        fun get(): Long {
            var id = (Random().nextInt(899999999) + 100000000).toLong()
            while (id in existing_ids)
                id = (Random().nextInt(899999999) + 100000000).toLong()
            existing_ids.add(id)
            return id
        }
        private val existing_ids: MutableList<Long> = arrayListOf()
    }
}

fun RawProfile.person(): Profile {
    val p: Profile = when {
        values(this.rawData, "source=") == "facebook" -> FacebookProfile(RandId.get())
        values(this.rawData, "source=") == "vk" -> VKProfile(RandId.get())
        else -> LinkedInProfile(RandId.get())
    }
    p.age = values(this.rawData, "age=")?.toIntOrNull()
    p.firstName = values(this.rawData, "firstName=")?.toLowerCase()?.capitalize()
    p.lastName = values(this.rawData, "lastName=")?.toLowerCase()?.capitalize()
    return p
}

val listofProfiles: List<Profile> = rawProfiles.map { it.person() }.fold(arrayListOf()) { res, pers ->
    var in_res = false
    for (j in res)
        if (equals_profile(j, pers)) {
            pers.id = j.id
            if (pers.dataSource == j.dataSource)
                in_res = true
        }
    if (!in_res)
        res.add(pers)
    res
}

fun DataSource.makeAvg(): Pair<DataSource, Double> {
    var count = listofProfiles.count { it.dataSource == this }
    val avg = listofProfiles.fold(0) { sum, pers ->
        sum + if (pers.dataSource == this) {
            if (pers.age == null)
                count--
            pers.age ?: 0
        } else 0
    }.toDouble() / count
    return Pair(this, avg)
}

/**
 * Task #2
 * Find the average age for each datasource (for profiles that has age)
 */
val avgAge: Map<DataSource, Double> = mapOf(DataSource.FACEBOOK.makeAvg(), DataSource.LINKEDIN.makeAvg(), DataSource.VK.makeAvg())

/**
 * Task #3
 * Group all user ids together with all profiles of this user.
 * We can assume users equality by : firstName & lastName & age
 */
val groupedProfiles: Map<Long, List<Profile>> =
        listofProfiles.associate { it.id to listofProfiles.fold(arrayListOf<Profile>()) { res, x -> if (x.id == it.id) res.add(x); res } }