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
    }
//    @GetMapping
//    fun getData() {
//        val url = URL("https://adventofcode.com/2023/day/1/input")
//            .openConnection() as HttpsURLConnection
//        println(url.getResponseCode())
//        val data = url.inputStream.bufferedReader().forEachLine { println(it) }
//        println("hi there");
//    }
