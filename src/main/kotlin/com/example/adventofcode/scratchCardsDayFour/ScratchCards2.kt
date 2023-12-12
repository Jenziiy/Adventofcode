package com.example.adventofcode.scratchCardsDayFour

import com.example.adventofcode.MyFile
import java.io.BufferedReader
import kotlin.math.pow

class ScratchCards2 {
    val file = MyFile().getFile("scratchCardInputTest.txt")
    private fun create3DList(file: BufferedReader): MutableList<MutableList<MutableList<String>>> {
        val threeDList = mutableListOf<MutableList<MutableList<String>>>()
        file.forEachLine {
            val twoDList = mutableListOf<MutableList<String>>()
            val line = it.split(":")[1]
            line.split("|").forEach() { it2 ->
             val oneDList = preprocessList(it2).split(" ")
                twoDList.add(oneDList.toMutableList())
            }
            threeDList.add(twoDList)
        }
        return threeDList
    }
    private var threeDList = create3DList(file)
    fun getResult(): Int {
        var result = 0
        threeDList.forEach {
            val winningNumberAmount = calculateDuplicates(it[0],
                it[1])
            result += (2.0.pow(winningNumberAmount.toDouble())).toInt()
        }
        return result
    }
    private fun preprocessList(numbers: String): String {
        return numbers.replace("  ", " 0")
    }

    private fun calculateDuplicates(listA: MutableList<String>, listB: MutableList<String>): Int {
        var score = -1
        listA.forEach{
            listB.forEach{it2 ->
                 if(it == it2 && it.isNotEmpty()){
                    score++
                    return@forEach
                }
            }
        }
        return score
    }
}
