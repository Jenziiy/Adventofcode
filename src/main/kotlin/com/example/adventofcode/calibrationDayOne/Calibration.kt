package com.example.adventofcode.calibrationDayOne

import java.io.File

class Calibration {

    fun getData() {
        var lineRes = 0
        val myFile = File("adventofcode1.txt")
        println("Attempting to read from file in: "+myFile.getCanonicalPath())
        val bufferedReader = myFile.bufferedReader()
        bufferedReader.forEachLine {
            var num1: Char? = null
            var num2: Char? = null
        // println(it)
            it.forEach{ el ->
                if(el.isDigit()) {
                    if (num1 == null) {
                        num1 = el
                        num2 = el
                    } else
                        num2 = el
                    }
                }
            lineRes += "$num1$num2".toInt()
            println("$num1$num2")
            }
        println(lineRes)
         }

    fun getData2(){
        var resultFile = 0
        val numbersToLetters = mapLettersToNumbers()
        val bufferedReader = File("adventofcode1.txt").bufferedReader()
        bufferedReader.forEachLine {
           val splitList =  it.split("((?<=[0-9])|(?=[0-9]))".toRegex())
            var stringLine: String = ""
            for(item in splitList){
                if (item.contains("[0-9]".toRegex())){
                    stringLine += item.toString()
                } else if(item.length > 1) {
                    for (i in item.indices){
                        if ( i+2 < item.length && item[i] == 'o'){
                            if(item[i+1] == 'n' && item[i+2] == 'e'){
                                stringLine += numbersToLetters.getValue("one")
                            }
                        }
                        if ( item[i] == 't'){
                            if(i+2 < item.length && item[i+1] == 'w' && item[i+2] == 'o'){
                                stringLine += numbersToLetters.getValue("two")
                            } else if(i+4 < item.length && item[i+1] == 'h' && item[i+2] == 'r' && item[i+3] == 'e' && item[i+4] == 'e'){
                                stringLine += numbersToLetters.getValue("three")
                            }
                        }
                        if ( i+2 < item.length && item[i] == 'f'){
                            if(item[i+1] == 'o' && item[i+2] == 'u' && item[i+3] == 'r'){
                                stringLine += numbersToLetters.getValue("four")
                            } else if(item[i+1] == 'i' && item[i+2] == 'v' && item[i+3] == 'e'){
                                stringLine += numbersToLetters.getValue("five")
                            }
                        }
                        if ( item[i] == 's'){
                            if(i+2 < item.length && item[i+1] == 'i' && item[i+2] == 'x'){
                                stringLine += numbersToLetters.getValue("six")
                            } else if( i+4 < item.length && item[i+1] == 'e' && item[i+2] == 'v' && item[i+3] == 'e'
                                && item[i+4] == 'n'){
                                stringLine += numbersToLetters.getValue("seven")
                            }
                        }

                        if ( i+4 < item.length && item[i] == 'e'){
                            if(item[i+1] == 'i' && item[i+2] == 'g' && item[i+3] == 'h' && item[i+4] == 't'){
                                stringLine += numbersToLetters.getValue("eight")
                            }
                        }
                        if ( i+3 < item.length && item[i] == 'n'){
                            if(item[i+1] == 'i' && item[i+2] == 'n' && item[i+3] == 'e'){
                                stringLine += numbersToLetters.getValue("nine")
                            }
                        }

                    }
//                   numbersToLetters.forEach{ entry ->
//                       if (item.contains((entry.key).toString())){
//                           stringLine += (entry.value).toString()
//                       }
//                   }
                }
            }
            //println(stringLine)
            var resultLine = 0
            resultLine = (stringLine[0].toString() + stringLine[stringLine.length-1].toString()).toInt()
            resultFile += resultLine
            }
        println(resultFile)
        }
    }
    private fun mapLettersToNumbers(): Map<Any, Any> {
        return mapOf(
            "one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5,
            "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9
        )
    }
//    @GetMapping
//    fun getData() {
//        val url = URL("https://adventofcode.com/2023/day/1/input")
//            .openConnection() as HttpsURLConnection
//        println(url.getResponseCode())
//        val data = url.inputStream.bufferedReader().forEachLine { println(it) }
//        println("hi there");
//    }
