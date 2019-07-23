package com.jze.kt

/**
 * @author: 蒋泽恩
 * @date: 2019/5/15 14:25
 * @description: kotlin中的oop
 */
class Data {
    init {
        println("init block")
    }

    init {
        println("constructor block")
    }
}

class DontCreateMe private constructor(val num: Int = 10){
    init {
        println("don't create me")
    }
    companion object{
        fun singleTon(size:Int):DontCreateMe{
            return DontCreateMe(size)
        }
    }
}



fun test(list:List<String>): List<Int> {
    return list.map {
        println(it)
        10
    }.toList()
}

fun main() {
    Data()
    val pri = DontCreateMe.singleTon(10)
    val  intList = test(listOf("123", "456"))
    println(intList)
}