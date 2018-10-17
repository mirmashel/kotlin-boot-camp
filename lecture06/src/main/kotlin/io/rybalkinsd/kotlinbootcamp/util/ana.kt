package io.rybalkinsd.kotlinbootcamp.util

import org.springframework.web.bind.annotation.Mapping


@Dummy
@Deprecated(message = "PLOHO",
        replaceWith = ReplaceWith("dummy1()"))
fun dummy() = 1


//@Suppress("Deprecation")
fun dummy1() {
    dummy()
}

@Dummy
class A

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class Dummy

annotation class Controller(
        val path: Array<String>
)

@Repeatable
@Retention(AnnotationRetention.SOURCE)
annotation class Mapping(
        val path: String
)

@Controller(path = ["/handle", "/health"])
class Handler{
    @io.rybalkinsd.kotlinbootcamp.util.Mapping("/map1")
    @io.rybalkinsd.kotlinbootcamp.util.Mapping("/map")
    fun handle(): String = ""
}


