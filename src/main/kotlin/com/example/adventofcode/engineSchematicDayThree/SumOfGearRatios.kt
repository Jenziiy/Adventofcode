package com.example.adventofcode.engineSchematicDayThree

import com.example.adventofcode.MyFile

class SumOfGearRatios {
    private val file = MyFile().getFile("sumofparts.txt")
    private val twoDList = create2DList()
    private fun create2DList(): MutableList<MutableList<String>> {
        val twoDList = mutableListOf<MutableList<String>>()
        file.forEachLine {
            val oneDList = mutableListOf<String>()
            it.forEach { it2 ->
                oneDList.add(it2.toString())
            }
            twoDList.add(oneDList)
        }
        return twoDList
    }
    fun sumOfGears(): Int {
        var gears = 0
        println(twoDList)
        twoDList.forEachIndexed { it, line ->
            gears += getGears(it, line)
        }
        return gears
    }
    private fun getGears(lineIndex: Int, line: List<String>): Int {
        var res = 0
        line.forEachIndexed { itemIndex, item ->
            if (item == "*") {
                res += getGearPart(lineIndex, itemIndex)
            }
        }
        return res
    }
    private fun getGearPart(x: Int, y: Int): Int {
        val numList = mutableListOf<String>()
        var gearPart = 0
        val prevLineNumberStr = getNumbers(x-1,y)
        val currLineNumberStr = getNumbers(x,y)
        val nextLineNumberStr = getNumbers(x+1,y)

        if(prevLineNumberStr.isNotEmpty())
        prevLineNumberStr.split("[^0-9]".toRegex()).forEach{
            if(it.isNotEmpty())
            numList.add(it)
        }
        if(currLineNumberStr.isNotEmpty())
        currLineNumberStr.split("[^0-9]".toRegex()).forEach{
            if(it.isNotEmpty())
                numList.add(it)
        }
        if(nextLineNumberStr.isNotEmpty())
        nextLineNumberStr.split("[^0-9]".toRegex()).forEach{
            if(it.isNotEmpty())
                numList.add(it)
        }
        if(numList.size == 2 ){
         gearPart = numList[0].toInt() * numList[1].toInt()
        }
        return gearPart
    }
    private fun getNumbers(x: Int, y: Int): String {
        var lineNumberString = ""
        for (item in -3..3) {
            lineNumberString += (twoDList[x][y + item])
        }
        if (lineNumberString[4].toString() matches "[^0-9]".toRegex()) {
           lineNumberString = lineNumberString.subSequence(0, 4).toString()
        } else if (lineNumberString[5].toString() matches "[^0-9]".toRegex()) {
            lineNumberString = lineNumberString.subSequence(0, 5).toString()
        } else if (lineNumberString[6].toString() matches "[^0-9]".toRegex()) {
            lineNumberString = lineNumberString.subSequence(0, 6).toString()
        }
        if (lineNumberString[2].toString() matches "[^0-9]".toRegex()) {
            lineNumberString = lineNumberString.subSequence(3, lineNumberString.length).toString()
        } else if (lineNumberString[1].toString() matches "[^0-9]".toRegex()) {
            lineNumberString = lineNumberString.subSequence(2, lineNumberString.length).toString()
        } else if (lineNumberString[1].toString() matches "[^0-9]".toRegex()) {
            lineNumberString = lineNumberString.subSequence(1, lineNumberString.length).toString()
        }
        return lineNumberString
    }
}
