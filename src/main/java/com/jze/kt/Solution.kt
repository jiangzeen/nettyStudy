package com.jze.kt

/**
 * @author: 蒋泽恩
 * @date: 2019/5/15 15:29
 * @description:leetcode NO.914
 * 给定一副牌，每张牌上都写着一个整数。此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
 * 每组都有 X 张牌。组内所有的牌上都写着相同的整数。仅当你可选的 X >= 2 时返回 true。
 * 输入：[1,2,3,4,4,3,2,1]
 * 输出：true
 * 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
 */
class Solution {
    var memeber = 10
    fun hasGroupsSizeX(deck: IntArray): Boolean{
        var item = 1
        val commons = ArrayList<Int>()
        deck.sort()
        for (i in 0 until deck.size - 1){
            if (deck[i] == deck[i + 1]){
                item++
            }else{
                commons.add(item)
                item = 1
            }
        }
        commons.add(item)
        println(commons)
        val num = commons.min()
        if (num == 1) return false
        //commons中是否存在>=2的公约数
        return minCommon(num!!, commons)
    }
}

fun minCommon(min:Int, commons:ArrayList<Int>):Boolean{
    var con: Int
    for (i in 0 until commons.size){
        var temp = min
        if (commons[i] != min){
            var r = 1
            con = commons[i]
            while (r != 0){
                r = con % temp
                con = temp
                temp = r
            }
            println(con)
            if (con == 1) return false
        }
    }
    return  true
}


/**
 * 拓展属性和拓展函数
 * 拓展属性不支持直接初始化，kotlin提供了setter/getter用于初始化
 */
val <T> List<T>.lastIndex: Int
   get() = size - 1
var Solution.ext
    get() = 10
    set(value) {
        this.memeber += value
    }


fun main() {
    println(Solution().hasGroupsSizeX(intArrayOf(1,1,1,2,2,2,3,3)))
    val t  = Solution()
    t.ext = 20   //调用了set方法,提供value给set方法使用
    println(t.memeber)
}