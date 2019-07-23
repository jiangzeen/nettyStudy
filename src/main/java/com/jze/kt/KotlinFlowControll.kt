package com.jze.kt

/**
 * @author: 蒋泽恩
 * @date: 2019/5/14 19:49
 * @description: kotlin流程控制
 */
fun main(args: Array<String>) {
    //普通的if语句，可以不需要else语句
    val number1:Int = 10
    if(number1 > 0){
        println("number > 10")
    }else{
        println("number < 10")
    }
    //if语句作为一个表达式,else分支是强制的
    val number2:Int = if (number1 > 10){
        number1
    }else{
        0
    }
    println(number2)

    //if else else if表达式
    val num: Int = readLine()!!.toInt()
    val result = if(num in 1..9)
        "num > 0"
    else if(num > 10)
        "num < 0"
    else
        "else num"
    println(result)

    //if表达式嵌套
    val n1 = 3
    val n2 = 5
    val n3 = -2

    val max = if (n1 > n2) {
        if (n1 > n3)
            n1
        else
            n3
    } else {
        if (n2 > n3)
            n2
        else
            n3
    }
    println("max = $max")

    //使用when代替switch语句
    val result2= when{
        1==1-> {"1"}
        //当when为一个表达式时必须存在else语句
        else -> {"2"}
    }
}