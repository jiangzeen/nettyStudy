package com.jze.kt

import java.util.*

/**
 * @author: 蒋泽恩
 * @date: 2019/5/14 16:46
 * @description: kotlin入门
 */
fun main(args: Array<String>) {
    println("Hello World")

    /*kotlin基本数据类型*/
    val aBoolean:Boolean = true
    var aInt:Int = 10
    var afloat:Float = 10.20F
    val aByte:Byte = 10
    val aChar:Char = 'a'
    var a = "abcd"
    val aString = String(charArrayOf('a','b', 'c', 'd'))
    /**支持字符串模板**/
    println("${a + aString}")
    println(a==aString)
    println(a === aString)
    println(aBoolean)

    println("------------我是一个分割线--------------")
    var man: Man = showInfo()
    man.test()
    if(man is Girl){
        man.test()
    }

    println("--------------------------------------")
    var string:String = readLine()!!
    println("输入的string是:$string")
}

open class  Man constructor(name:String, age:Int, height:Int){
    open var myName:String? = null
    open var myAge:Int? = 0
    open var myHeight:Int? = 10
    init {
        myName= name
        myAge = age
        myHeight = height
    }

    override fun toString(): String {
        return "[name:$myName,age:$myAge,height:$myHeight]"
    }

    open fun test(){
        println("这是man的test()方法")
    }
}

class Girl constructor(name: String, age: Int, height: Int, dress:String): Man
(name, age, height){
    var myDress:String = dress
    init {

    }
    override fun toString():String{
        return "[name:$myName,age:$myAge,height:$myHeight,dress:$myDress]"
    }

    override fun test(){
        println("这是girl的test()方法")
    }
}



val girl: Man = Girl("kotlin", 20, 156, "T")

fun showInfo(): Man {
    val s = Scanner(System.`in`)
    println(girl)
    return girl
}