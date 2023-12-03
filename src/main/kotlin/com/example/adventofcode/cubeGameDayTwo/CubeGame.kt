package com.example.adventofcode.cubeGameDayTwo

import java.io.File

class CubeGame {

    fun getFileContents(): MutableMap<String, String> {
        val gameMap = mutableMapOf<String, String>()
        val myFile = File("cubedata.txt")
        val bufferedReader = myFile.bufferedReader()
        bufferedReader.forEachLine {
            val keyValuePair = it.split(':')
     //       val stringArr = keyValuePair[1].split(";").toTypedArray()
            gameMap[keyValuePair[0]] = keyValuePair[1]
        }
        //       println(gameMap.map { "${it.key} : ${it.value}"}.joinToString(", "))
        return gameMap
    }

    fun getEligibleGames(){
        var sumIDs = 0;
        val gameMap = getFileContents()
        var sumEligibleIDs = 0;
        gameMap.forEach{ it ->
            val gameID = it.key
            var eligibleGame = true
            val game = it.value.split(";").toTypedArray()
            game.forEach{ it2 ->
                val bag = it2.split(",").toTypedArray()
                bag.forEach {it3 ->
                    val cube = it3.split(" ")
                    if (cube[2] == "red"){
//                     println("${cube[2]} ${cube[1]}")
                        if(cube[1].toInt() > 12 ){
                         eligibleGame = false
                        }
                    } else if(cube[2] == "green"){
                     if(cube[1].toInt() > 13 ){
                         eligibleGame = false
                     }
                    } else if(cube[2] == "blue"){
                     if(cube[1].toInt() > 14 ){
                         eligibleGame = false
                     }
                    } else{
                     println("what is this?? cube has a different color namely : ${cube[2]} ")
                     error("what is this?? cube has a different color namely : ${cube[2]} ")
                    }
                }
            }
            if (eligibleGame) {
                sumEligibleIDs += gameID.split(" ")[1].toInt()
            }
            sumIDs += gameID.split(" ")[1].toInt()
        }
        println("This  is the sum of all VALID IDs $sumEligibleIDs")
        println("This is the sum of ALL IDs: $sumIDs")
    }
}