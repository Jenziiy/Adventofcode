package com.example.adventofcode.scratchCardsDayFour

import com.example.adventofcode.MyFile
import java.io.BufferedReader

class ScratchCards {
    val file = MyFile().getFile("scratchCardInput.txt")

    fun createMap(file: BufferedReader): Map<String, String> {
        val inputMap = mutableMapOf<String, String>()
        file.forEachLine {
                val (key, value) = it.split(":")
                inputMap[key] = value
        }
        return inputMap
    }
    val map = createMap(file)

    fun getResult(): Int {
        var result = 0
        map.forEach {
            val (winning, having) = it.value.split("|")
            val winningList = winning.split(" ").toMutableList()
            val havingList = having.split(" ").toMutableList()
            preprocessList(winningList)
            preprocessList(havingList)
            println(winningList)
            println(havingList)
            val winningNumberAmount = calculateDuplicates(winningList, havingList)
            result += (Math.pow(2.0,winningNumberAmount.toDouble())).toInt()
        }
        return result
    }
    fun preprocessList(list: MutableList<String>) {
        var index = 0
        while(list.size > index) {
            if (list[index].length == 1) {
                list[index] = "0${list[index]}"
            } else if(list[index].isEmpty()){
                list.removeAt(index)
                index -= 1
            }
            index++
        }
    }

    fun calculateDuplicates(listA: List<String>, listB: List<String>): Int {
        var score = -1
        listA.forEach{
            listB.forEach{it2 ->
                if(it == it2){
                    score++
                }
            }
        }
        return score
    }
}
