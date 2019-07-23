package com.jze.kt

/**
 * @author: 蒋泽恩
 * @date: 2019/5/15 20:57
 * @description: 一些特殊的class
 */

//数据类,用于存储数据的类，可类比与Java的pojo，大大简化代码
//默认实现了equals、hasCode、toString、获取成员属性componentN()等方法，并且有一个copy()方法可用于复制数据
//使用data class关键字声明，且必须有一个主要的construct构造器,且construct至少拥有一个成员参数
//也可以在class body中定义成员变量，但是不包含在copy()中而且也不提供获取成员实例的方法componentN()
data class User(var name:String, var age:Int)
val user = User("123", 10)



//使用密封类,类似于enum，密封类也是有限的几个集合,只不过密封类可以有多个实例化
//使用sealed关键字声明密封类
//密封类可以用于when表达式语句,当when的表达式中所有的情况(即sealed class)都被列举出来时可省略else语句
sealed class Expr
data class Const(val number: Double):Expr()
data class Sum(val el: Expr, val e2:Expr):Expr()


fun eval(expr: Expr):Double= when (expr){
    is Const -> expr.number
    is Sum -> eval(expr.el) + eval(expr.e2)

}


fun main() {
    println(user.copy())
    var (a, b) = user
    println(user.component1())

}