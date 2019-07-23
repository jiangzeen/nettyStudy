package com.jze.kt

import java.lang.IllegalArgumentException

/**
 * @author: 蒋泽恩
 * @date: 2019/5/16 21:46
 * @description: 在kotlin中对null的处理
 */
fun main(){
    //使用?进行空的处理
   var b:String? = "abc"
    b = null
    val l = b?.length
    println(l)

    //更加智能的做法
    var c:String? = "kotlin"
    if (c != null && c.isNotEmpty()){
        println("String of length ${c.length}")

    }else{
        println("empty string")
    }
    //?.可用于链式调用
    var person: Person? = null
    person?.department?.head?.name

    //Elvis操作符表达式,可替代一些判null的if else
    val length1: Int = b?.length?:-1
    val length2: Int = if (b != null) b.length else-1
    fun foo(node:String?):String?{
        //可使用return throw等表达式
        val l = node?.length?:return null
        val l2 = node?.length?:throw IllegalArgumentException("ex") as Throwable
        return "123"
    }

//    val s = b?.length?: fail("null")
//    println(s)

    val isEmptyStringList: List<String>.() -> Boolean = List<String>::isEmpty

    //函数引用示例
    fun length(s:String) = s.length
    val oddLength = compose(::isOdd, ::length)

    val strings = listOf("a", "ab", "abc")

    println(strings.filter(oddLength))

}

open class Person(var department:Department?)

class Department(var head:Head?)

class Head(var name:String?)

fun fail(message:String):Nothing{
    throw IllegalArgumentException(message)
}

//函数组合
fun <A, B, C> compose(f:(B)->C, g:(A)->B):(A)->C{
    return {x -> f(g(x))}
}

fun isOdd(x:Int) = x % 2==0
