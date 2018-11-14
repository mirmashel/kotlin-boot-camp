package io.rybalkinsd.kotlinbootcamp.util

import kotlin.reflect.KProperty

class User {
    var name = "Alice"
    var age by AgeDelegate()
}

class AgeDelegate {
    var age: Int? = null
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int = age!!

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int){
        if (value > 120) age = 120
        else if (value < 0) age = 0
        else age = value
    }
}