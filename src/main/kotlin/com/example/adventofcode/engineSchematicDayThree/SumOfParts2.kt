package com.example.adventofcode.engineSchematicDayThree

import com.example.adventofcode.MyFile

class SumOfParts2 {
    private fun create2DArray(): MutableList<List<String>> {
        var fileLineAmount = 0
        val reader = MyFile().getFile("sumofparts.txt")

        val twoDArray = mutableListOf<List<String>>()
        reader.forEachLine {
            val oneDArray = mutableListOf<String>()
            it.forEach { it2 ->
                oneDArray.add(it2.toString())
            }
            twoDArray.add(oneDArray)
        }

        return twoDArray
//    twoDArray.forEach {
//        println(it)
//    }
    }
    fun markSpecialChars(): MutableList<String> {
        val twoDArray = create2DArray()
        val specialCharCoordinatesList = mutableListOf<String>()
        twoDArray.forEachIndexed { index, line ->
            line.forEachIndexed{ index2, item ->
                if(item matches "[^.0-9]".toRegex()){
                    specialCharCoordinatesList.add("$index,$index2")
                }
            }
        }
        return specialCharCoordinatesList
    }

    fun getSpecialCharsAdjacentNumbers(): MutableList<String> {
        val twoDArray = create2DArray()
        val specialCharCoordinates = markSpecialChars()
        val adjacentNumberList = mutableListOf<String>()
        specialCharCoordinates.forEach{
          val (line,item) =  it.split(',')
                //if num is underneath special char
            if(twoDArray[line.toInt()-1][item.toInt()] matches "[0-9]".toRegex()) {
                // TODO: write implementation for when special char is on [-1][0]
                // 1. 012 2. 01  3. -2 -1 0  4. 0 -1 5.
                 if(twoDArray[line.toInt()-1][item.toInt()-1] matches "[0-9]".toRegex() && (
                     twoDArray[line.toInt()-1][item.toInt()-2] matches "[^0-9]".toRegex() ||
                     twoDArray[line.toInt()-1][item.toInt()+1] matches "[0-9]".toRegex() )){
                    if(twoDArray[line.toInt()-1][item.toInt()-1] matches "[0-9]".toRegex() &&
                        twoDArray[line.toInt()-1][item.toInt()-2] matches "[^0-9]".toRegex()){
                        var adjacentNumber = ""
                        adjacentNumber += twoDArray[line.toInt()-1][item.toInt()-1]
                        adjacentNumber += twoDArray[line.toInt()-1][item.toInt()]
                        if(twoDArray[line.toInt()-1][item.toInt()+1] matches "[0-9]".toRegex() ){
                            adjacentNumber += twoDArray[line.toInt()-1][item.toInt()+1]
                        }
                        adjacentNumberList += adjacentNumber
                    }
                 }else if(twoDArray[line.toInt()-1][item.toInt()] matches "[0-9]".toRegex()){
                    var adjacentNumber = ""
                    if(twoDArray[line.toInt()-1][item.toInt()-1] matches "[0-9]".toRegex()){
                        if(twoDArray[line.toInt()-1][item.toInt()-2] matches "[0-9]".toRegex()){
                            adjacentNumber += twoDArray[line.toInt()-1][item.toInt()-2]
                        }
                        adjacentNumber += twoDArray[line.toInt()-1][item.toInt()-1]
                        adjacentNumber += twoDArray[line.toInt()-1][item.toInt()]
                    } else if(twoDArray[line.toInt()-1][item.toInt()+1] matches "[0-9]".toRegex()){
                        adjacentNumber += twoDArray[line.toInt()-1][item.toInt()]
                        adjacentNumber += twoDArray[line.toInt()-1][item.toInt()+1]
                        if(twoDArray[line.toInt()-1][item.toInt()+2] matches "[0-9]".toRegex()){
                            adjacentNumber += twoDArray[line.toInt()-1][item.toInt()+2]
                        }
                    }
                    adjacentNumberList += adjacentNumber
                }
            } else {
                if(twoDArray[line.toInt()-1][item.toInt()-1] matches "[0-9]".toRegex()){
                    var adjacentNumber = ""
                    if(twoDArray[line.toInt()-1][item.toInt()-2] matches "[0-9]".toRegex()){
                        if(twoDArray[line.toInt()-1][item.toInt()-3] matches "[0-9]".toRegex()){
                            adjacentNumber += twoDArray[line.toInt()-1][item.toInt()-3]
                        }
                        adjacentNumber += twoDArray[line.toInt()-1][item.toInt()-2]
                    }
                    adjacentNumber += twoDArray[line.toInt()-1][item.toInt()-1]
                    adjacentNumberList += adjacentNumber
                }
                if(twoDArray[line.toInt()-1][item.toInt()+1] matches "[0-9]".toRegex()){
                    var adjacentNumber = ""
                    adjacentNumber += twoDArray[line.toInt()-1][item.toInt()+1]
                    if(twoDArray[line.toInt()-1][item.toInt()+2] matches "[0-9]".toRegex()){
                        adjacentNumber += twoDArray[line.toInt()-1][item.toInt()+2]
                        if(twoDArray[line.toInt()-1][item.toInt()+3] matches "[0-9]".toRegex()){
                            adjacentNumber += twoDArray[line.toInt()-1][item.toInt()+3]
                        }
                    }
                    adjacentNumberList += adjacentNumber
                }
            }
// line above [0][*] -> [1][d]
            if(twoDArray[line.toInt()+1][item.toInt()] matches "[0-9]".toRegex()){
                // TODO: write implementation for when special char is on [+1][0]
                // 1. 012 2. 01  3. -2 -1 0  4. 0 -1 5.
                if(twoDArray[line.toInt()+1][item.toInt()-1] matches "[0-9]".toRegex() && (
                            twoDArray[line.toInt()+1][item.toInt()-2] matches "[^0-9]".toRegex() ||
                                    twoDArray[line.toInt()+1][item.toInt()+1] matches "[0-9]".toRegex() )){
                    if(twoDArray[line.toInt()+1][item.toInt()-1] matches "[0-9]".toRegex() &&
                        twoDArray[line.toInt()+1][item.toInt()-2] matches "[^0-9]".toRegex()){
                        var adjacentNumber = ""
                        adjacentNumber += twoDArray[line.toInt()+1][item.toInt()-1]
                        adjacentNumber += twoDArray[line.toInt()+1][item.toInt()]
                        if(twoDArray[line.toInt()+1][item.toInt()+1] matches "[0-9]".toRegex() ){
                            adjacentNumber += twoDArray[line.toInt()+1][item.toInt()+1]
                        }
                        adjacentNumberList += adjacentNumber
                    }
                }else if(twoDArray[line.toInt()+1][item.toInt()] matches "[0-9]".toRegex()){
                    var adjacentNumber = ""
                    if(twoDArray[line.toInt()+1][item.toInt()-1] matches "[0-9]".toRegex()){
                        if(twoDArray[line.toInt()+1][item.toInt()-2] matches "[0-9]".toRegex()){
                            adjacentNumber += twoDArray[line.toInt()+1][item.toInt()-2]
                        }
                        adjacentNumber += twoDArray[line.toInt()+1][item.toInt()-1]
                        adjacentNumber += twoDArray[line.toInt()+1][item.toInt()]
                    } else if(twoDArray[line.toInt()+1][item.toInt()+1] matches "[0-9]".toRegex()){
                        adjacentNumber += twoDArray[line.toInt()+1][item.toInt()]
                        adjacentNumber += twoDArray[line.toInt()+1][item.toInt()+1]
                        if(twoDArray[line.toInt()+1][item.toInt()+2] matches "[0-9]".toRegex()){
                            adjacentNumber += twoDArray[line.toInt()+1][item.toInt()+2]
                        }
                    }
                    adjacentNumberList += adjacentNumber
                }
            } else {
                if(twoDArray[line.toInt()+1][item.toInt()-1] matches "[0-9]".toRegex()){
                    var adjacentNumber = ""
                    if(twoDArray[line.toInt()+1][item.toInt()-2] matches "[0-9]".toRegex()){
                        if(twoDArray[line.toInt()+1][item.toInt()-3] matches "[0-9]".toRegex()){
                            adjacentNumber += twoDArray[line.toInt()+1][item.toInt()-3]
                        }
                        adjacentNumber += twoDArray[line.toInt()+1][item.toInt()-2]
                    }
                    adjacentNumber += twoDArray[line.toInt()+1][item.toInt()-1]
                    adjacentNumberList += adjacentNumber
                }
                if(twoDArray[line.toInt()+1][item.toInt()+1] matches "[0-9]".toRegex()){
                    var adjacentNumber = ""
                    adjacentNumber += twoDArray[line.toInt()+1][item.toInt()+1]
                    if(twoDArray[line.toInt()+1][item.toInt()+2] matches "[0-9]".toRegex()){
                        adjacentNumber += twoDArray[line.toInt()+1][item.toInt()+2]
                        if(twoDArray[line.toInt()+1][item.toInt()+3] matches "[0-9]".toRegex()){
                            adjacentNumber += twoDArray[line.toInt()+1][item.toInt()+3]
                        }
                    }
                    if(adjacentNumber ==  "982"){
                        println("stop $line, $item")
                    }
                    adjacentNumberList += adjacentNumber
                }
            }

            if(twoDArray[line.toInt()][item.toInt()-1] matches "[0-9]".toRegex()){
                var adjacentNumber = ""
                if(twoDArray[line.toInt()][item.toInt()-2] matches "[0-9]".toRegex()){
                    if(twoDArray[line.toInt()][item.toInt()-3] matches "[0-9]".toRegex()){
                        adjacentNumber += twoDArray[line.toInt()][item.toInt()-3]
                    }
                    adjacentNumber += twoDArray[line.toInt()][item.toInt()-2]
                }
                adjacentNumber += twoDArray[line.toInt()][item.toInt()-1]
                adjacentNumberList += adjacentNumber
            }
            if(twoDArray[line.toInt()][item.toInt()+1] matches "[0-9]".toRegex()){
                var adjacentNumber = ""
                adjacentNumber += twoDArray[line.toInt()][item.toInt()+1]
                if(twoDArray[line.toInt()][item.toInt()+2] matches "[0-9]".toRegex()){
                    adjacentNumber += twoDArray[line.toInt()][item.toInt()+2]
                    if(twoDArray[line.toInt()][item.toInt()+3] matches "[0-9]".toRegex()){
                        adjacentNumber += twoDArray[line.toInt()][item.toInt()+3]
                    }
                }
                adjacentNumberList += adjacentNumber
            }
        }
        return adjacentNumberList
    }

    fun calculateSumOfAdjacentNumbers(){
        val specialIntsAdjacentList = mutableListOf<Int>()
        val specialCharsAdjacentNumbers = getSpecialCharsAdjacentNumbers()
        println(specialCharsAdjacentNumbers)
        specialCharsAdjacentNumbers.forEachIndexed{ index, it ->
         if(it != "")
             //it matches "^[0-9]*\$".toRegex() &&
            specialIntsAdjacentList.add(it.toInt())
         else{
             println(it)
         }
        }
        val sumOfAdjacentNumbers = specialIntsAdjacentList.reduce{ a, b -> a + b }
        println(sumOfAdjacentNumbers)
    }
}
