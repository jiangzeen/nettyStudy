package com.jze.io

import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.ByteBuffer

/**
 * @author: 蒋泽恩
 * @date: 2019/5/18 11:49
 * @description:
 */
fun main() {
    readFile("nio2.txt","normal_io.txt")
}

fun readFile(fileName:String, outPutName:String){
    val inPut = FileInputStream(fileName)
    val outPut = FileOutputStream(outPutName)

    val inputChannel = inPut.channel
    val outPutChannel = outPut.channel
    val buffer = ByteBuffer.allocate(1024)

    while (true){
        buffer.clear()
        val read = inputChannel.read(buffer)
        if (read == -1) break
        buffer.flip()

        outPutChannel.write(buffer)
    }

    inputChannel.close()
    outPutChannel.close()
}
