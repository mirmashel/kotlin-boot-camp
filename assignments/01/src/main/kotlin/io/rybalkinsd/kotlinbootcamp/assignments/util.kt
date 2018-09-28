package io.rybalkinsd.kotlinbootcamp.assignments

/**
 * Returns the greatest of `int` values.
 *
 * @param values an argument. !! Assume values.length > 0. !!
 * @return the largest of values.
 */
fun max(values: List<Int>): Int {
    var m: Int = values.first()
    for (i in values)
        m = if (i > m) i else m
    return m
}
/**
 * Returns the sum of all `int` values.
 *
 * @param values an argument. Assume values.length > 0.
 * @return the sum of all values.
 */
fun sum(values: List<Int>): Long {
    var s: Long = 0
    for (i in values)
        s += i
    return s
}