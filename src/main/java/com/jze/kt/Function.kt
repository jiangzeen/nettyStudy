package com.jze.kt

import java.lang.IllegalArgumentException
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * @author: 蒋泽恩
 * @date: 2019/5/15 22:22
 * @description: 高阶函数
 */
fun main() {
    val intList = listOf(1,2,3,4,5)
    //使用lambda表达式构造函数
    val result = intList.fold(10, {acc, nextElement ->
        print("[传入参数accumulator：$acc,")
        print("传入参数nextElement：$nextElement,")
        val result = acc+nextElement
        print("combine函数计算结果是：$result]")
        println()
        result
    })
    println("最终结果是$result")
    //使用匿名函数方法
    intList.fold(1, fun(a:Int, b:Int):Int = a+b)
    println("*****************************")
    val intFunction = IntTransformer()
    println("使用高阶函数计算两个数的结果：${sum(4,5, intFunction)}")
    println("*****************************")
    //带有接收者的函数 理解--使用在接收者对象String上调用plus方法,可以使用隐式的this
    val repeatFun: String.(Int)->String = {times -> this.repeat(times)}
    fun runTransformation(f:(String, Int) -> String):String{
        return f("hello", 3)
    }
    println("结果：${runTransformation(repeatFun)}")
    println("******************************")
    // = String,s->this.plus(s)
    //可使用invoke方法调用
    val stringPlus:(String, String)->String = String::plus
    println("直接调用stringPlus的结果是：${stringPlus("<-","->")}")
    println("使用invoke调用结果是：${stringPlus.invoke("Hello", "World")}")
    //可作为Int的拓展方法使用
    val intPlus:Int.(Int)->Int = {i->this.plus(i)}
    println("intPlus作为Int的拓展函数调用：${1.intPlus(2)}")
    println("********************************")
    method(ReentrantLock()) {"这是一个method"}
    println("********************************")
    saveUser(Users(1,"123","江西"))
    saveUser2(Users(1,"123","赣州"))
}


/**
 * 一般用于高阶函数
 * 高阶函数:高阶函数是将函数用作参数或返回值的函数
 * lambda表达式
 * 可以使用方法引用 参考Java
 */
//example1.实现：将一个初始值与一个集合collection中所有值相加返回结果
fun <T, R> Collection<T>.fold(
        initial:R,
        combine:(acc:R, nextElement:T) -> R
):R{
    var accumulator: R = initial
    for (element:T in this){
        accumulator = combine(accumulator, element)
    }
    return accumulator
}

//自定义类实现函数式接口获取函数示例
class IntTransformer:(Int, Int) -> Int{
    override fun invoke(x:Int, y:Int): Int {
       return x + y
    }

}

fun sum(x:Int, y:Int, add:(Int, Int)->Int):Int{
    return add(x, y)
}

/**
 *lambda注意：
 * 1.如果函数的最后一个参数接收一个函数时，那么lambda表达式可以放在参数()的后面
 * 2.下划`_`可用于未使用的参数
 * 3.
 */
var items = listOf(1,2,3)
val product = items.fold(1){acc, e -> acc * e}

/**
 * 内联函数--使用inline修饰fun
 * 内联函数的作用：在编译期将函数进行替换成普通的语句，减少函数入栈出栈的资源开销
 * noinline要解决的问题:内联函数的参数也是内联的，如果该参数是一个函数的话，
 * 那么在编译期就是一个具体的值而不是函数，所以非内联函数调用的话会造成错误->需要的是一个函数而不是一个值
 * 使用noinline可以将其声明为非内联函数
 */
inline fun <T> method(lock:Lock,noinline body:() -> T):T{
    lock.lock()
    try {
        otherMethod(body)
        return body()
    }finally {
        lock.unlock()
    }

}

fun <T> otherMethod(body: () -> T):T{
    return body()
}


//局部函数和拓展
class Users(val id: Int, val name:String,  val address: String)

fun  saveUser(user: Users){
    if(user.name.isEmpty()){
        throw IllegalArgumentException("姓名不能为空")
    }
    if (user.address.isEmpty()){
        throw IllegalArgumentException("地址不能为空")
    }
    println("已保存用户")
}

fun saveUser2(user: Users){
    fun isValidate(value:String, fieldName: String){
        if (value.isEmpty()){
            throw IllegalArgumentException("$fieldName 不能为空")
        }
    }
    isValidate(user.name, "Name")
    isValidate(user.address, "address")
    println("已保存用户")
}
//可进一步改写为拓展函数
fun Users.validate(){

}
