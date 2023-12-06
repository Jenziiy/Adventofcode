package com.example.adventofcode

import java.io.BufferedReader
import java.io.File

class MyFile {
    fun getFile(fileName: String): BufferedReader {
        return File(fileName).bufferedReader()
    }
}