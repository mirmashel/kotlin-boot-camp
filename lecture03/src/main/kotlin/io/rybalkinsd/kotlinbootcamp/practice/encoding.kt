package io.rybalkinsd.kotlinbootcamp.practice

import kotlin.math.min


/**
 * NATO phonetic alphabet
 */
val alphabet = setOf("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India",
        "Juliett", "Kilo", "Lima", "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo", "Sierra",
        "Tango", "Uniform", "Victor", "Whiskey", "Xray", "Yankee", "Zulu")

/**
 * A mapping for english characters to phonetic alphabet.
 * [ a -> Alfa, b -> Bravo, ...]
 */
val association: Map<Char, String> = alphabet.associateBy { it[0].toLowerCase() }

/**
 * Extension function for String which encode it according to `association` mapping
 *
 * @return encoded string
 *
 * Example:
 * "abc".encode() == "AlfaBravoCharlie"
 *
 */
fun String.encode(): String = this
        .map { it.toLowerCase() }
        .map { association[it] ?: it }
        .joinToString("")
/**
 * A reversed mapping for association
 * [ alpha -> a, bravo -> b, ...]
 */
val reversedAssociation: Map<String, Char> = alphabet.associate { it to it[0].toLowerCase() }

/**
 * Extension function for String which decode it according to `reversedAssociation` mapping
 *
 * @return encoded string or null if it is impossible to decode
 *
 * Example:
 * "alphabravocharlie".encode() == "abc"
 * "charliee".decode() == null
 *
 */
fun String.decode(): String? {
    if (this.isEmpty()) return ""
    if (!this[0].isLetter())
        return this[0] + (this.slice(1 until this.length).decode() ?: return null)
    val c: Char = reversedAssociation[
            this.slice(0 until min((association[this[0].toLowerCase()]?.length ?: 0), this.length)).toLowerCase().capitalize()
    ] ?: return null
    return c.toString() + (this.slice((association[this.toLowerCase()[0]]?.length ?: 0) until this.length).decode() ?: return null)
}