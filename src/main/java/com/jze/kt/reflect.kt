package com.jze.kt

import java.lang.StringBuilder
import kotlin.reflect.full.memberProperties

/**
 * @author: 蒋泽恩
 * @date: 2019/5/19 13:38
 * @description:kotlin反射
 */

class ReflectExample constructor(val name1:String, val name2:String){
    var name3: String? = null
}


fun main() {
    val e1 = ReflectExample("name1","name2")
    val kClass =  e1.javaClass.kotlin
    kClass.memberProperties.forEach{ println(it.name)}
    val kFunction = ::foo
    kFunction.call(10)
    val e = ReflectExample("张三","李四")
    e.name3
}

fun foo(num:Int) = println(num)



//序列化一个对象
private fun StringBuilder.serializerObject(obj: Any){

}

private fun StringBuilder.serializerString(s:String){

}
