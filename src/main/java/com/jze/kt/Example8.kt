package com.jze.kt

import kotlin.reflect.KProperty

/**
 * @author: 蒋泽恩
 * @date: 2019/5/18 10:29
 * @description: kotlin的委托属性
 */

fun main(){
    val foo =  Foo()
//    val oldValueP = foo._name
//    val oldValueS = foo.address
//    val oldValueP2 = foo._name
    foo.address = "xxx@163.com"
    foo.name = "alice1"
}

/**
 * 委托属性的基本语法
 * 被委托的类必须实现该属性的getValue和setValue的方法
 */
class Foo{
    var name:String by Delegate()
    var address: String by Delegate()

    val _name:String
        get(){
            if (name == null){
                name = loadName(this)
            }
            return name!!
        }
}

class Delegate {
    operator fun getValue(foo: Foo, property: KProperty<*>): String {
         println("调用了delegate的getValue()方法")
         //使用反射获取name
         val name = Foo::name
//         println(property.call("name"))
         return name.get(foo)
    }

    operator fun setValue(foo: Foo, property: KProperty<*>, i: String) {
        println("$foo 使用委托进行赋值 ${property.name}")
    }
}

fun loadName(foo:Foo):String{
    println("惰性初始化")
    return "lazyName"
}