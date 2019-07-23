package com.jze.kt

/**
 * @author: 蒋泽恩
 * @date: 2019/5/14 19:15
 * @description:
 */


val arrayInt: IntArray = intArrayOf(1, 2, 3)
val arrayChar: CharArray = charArrayOf('h', 'e', 'l', 'l', 'o')
val stringArray: Array<String> = arrayOf("123", "456", "789")
val manArray: Array<Man> = arrayOf(Girl("kt", 9, 180, "beauty"))
fun main() {
  /**kotlin数组**/
 for (man in manArray) {
     println(man)
 }
     arrayInt.forEach ret@{
     if (it > 0) return@ret
     println(it)
 }
 for (i in 0 until arrayInt.size){
     println(arrayInt[i])
 }
 arrayChar[0] = 'n'
 for (char in arrayChar){
     print(char + "\t")
 }
 println()
 stringArray.forEach {
    string -> println(string)
 }
 //使用函数式编程
 val filterInt =  arrayInt.filter { x -> x > 1 }
    for (int in filterInt){
        println(int)
    }
}
