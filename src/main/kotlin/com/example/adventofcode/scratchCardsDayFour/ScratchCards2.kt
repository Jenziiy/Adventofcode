package com.example.adventofcode.scratchCardsDayFour

import com.example.adventofcode.MyFile
import java.io.BufferedReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow

class ScratchCards2 {
    private val file = MyFile().getFile("scratchCardInput.txt")
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
            result += (2.0.pow(winningNumberAmount-1.toDouble())).toInt()
        }
        return result
    }
    private fun getDuplicateAmount(a: Int = 0, z: Int = threeDList.size-1): ArrayList<ArrayList<Int>> {
        var result = ArrayList<ArrayList<Int>>()
        for(i in a..< z) {
            val duplicates = calculateDuplicates(threeDList[i][0], threeDList[i][1])
            val next = i+1
            val upTo = next+duplicates
            result.add(arrayListOf(next, upTo))
        }
        return result
    }

    fun getTraversalAmount(){
        var result = 1
        val queue = getDuplicateAmount()
        result += queue.size
        while (queue.size > 0){
            val traversableCards = queue.removeFirst()
            val res = getDuplicateAmount(traversableCards[0],traversableCards[1])
                res.forEach {
                queue.add(it)
            }
            //println(result)
            result += getDuplicateAmount(traversableCards[0], traversableCards[1]).size
        }
    println(result)
    }
    private fun preprocessList(numbers: String): String {
        return numbers.replace("  ", " 0")
    }

    private fun calculateDuplicates(listA: MutableList<String>, listB: MutableList<String>): Int {
        var score = 0
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
