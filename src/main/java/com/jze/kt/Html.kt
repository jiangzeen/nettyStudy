package com.jze.kt

import java.time.LocalDate
import java.time.Period

/**
 * @author: 蒋泽恩
 * @date: 2019/5/16 23:08
 * @description:
 */

//使用DSL计算时间
val Int.day:Period
    get() = Period.ofDays(this)

val Period.ago:LocalDate
    get() = LocalDate.now() - this



fun main(){
    val yesterday = 1.day.ago
    println(yesterday)
}