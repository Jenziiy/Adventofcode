package com.example.adventofcode.engineSchematicDayThree

import com.example.adventofcode.MyFile
import java.io.BufferedReader

class SumOfGearRatios {
    private val file = MyFile().getFile("gearRatiosTest")
    private fun create2DList(file: BufferedReader): MutableList<List<String>> {
        file.forEachLine {
            val oneDList = mutableListOf<String>()
            it.forEach { it2 ->
                oneDList.add(it2.toString())
            }
            twoDList.add(oneDList)
        }
        return twoDList
    }
    private val twoDList = create2DList(file)
    // loop file
        // get gears per line
            // look for * -> no? return 0
            //            -> yes? look line above
            //            -> line above is num? look
            //            ->

    fun sumOfGears(): Int{
        var gears = 0
        twoDList.forEachIndexed{ it, line ->
            gears += getGears(it, line)
        }
        return gears
    }
    private fun getGears(lineIndex: Int, line: List<String>): Int {
        var res = 0
        line.forEachIndexed { itemIndex, item ->
            if(item == "*"){
             res += getGearPart(lineIndex, itemIndex)
            }
        }
        return res
    }

    private fun getGearPart(x: Int, y: Int): Int {
        val visitedNodeSet = mutableSetOf<String>()
        visitedNodeSet.add("$x,$y")
        for(i in x-1..x+1){
            var numberStringPositive = ""
            var numberStringNegative = ""
            var numberString = ""
            var isSpecialSymbol1 = false
            var isSpecialSymbol2 = false
        for (j in 0..3) {
            var coordinates = "$i,${y - j}"
            if (!visitedNodeSet.contains(coordinates) && !isSpecialSymbol1) {
                visitedNodeSet.add(coordinates)
                if (twoDList[i][y-j] matches "[0-9]".toRegex()) {
                    numberStringNegative += twoDList[i][y-j]
                } else {
                    isSpecialSymbol1 = true
                    numberStringNegative = numberStringNegative.reversed()
                }
            }
            coordinates = "$i,${y + j}"
            if (!visitedNodeSet.contains(coordinates)) {
                visitedNodeSet.add(coordinates)
                if (twoDList[i][y+j] matches "[0-9]".toRegex()) {
                    numberStringPositive += twoDList[i][y+j]
                } else {
                    isSpecialSymbol2 = true
                }
            }
            if (isSpecialSymbol1 && isSpecialSymbol2){
                numberString = numberStringNegative + numberStringPositive
                return numberString.toInt()
            }
        }
        }

        return 0
    }
}