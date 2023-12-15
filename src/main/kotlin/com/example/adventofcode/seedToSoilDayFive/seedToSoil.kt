package com.example.adventofcode.seedToSoilDayFive

import com.example.adventofcode.MyFile
import java.io.File

class seedToSoil {
    private val file = readFileAsLinesUsingUseLines("seedToSoilTest.txt")
    fun readFileAsLinesUsingUseLines(fileName: String): List<String>
            = File(fileName).useLines { it.toList() }
    fun processFile(){
        val mapOfMaps = mutableMapOf<String,MutableList<MutableList<Long>>>()
        val seeds = mutableListOf<Long>()
        file.forEachIndexed() { index, item ->
            val name = item.split(" ")[0]
            with(name) {
                when {
                    contains("to") -> {
                        mapOfMaps[name] = addMappingList(index)
                    }
                    contains("seeds:") -> {for (i in 1..<file[0].split(" ").size) {
                        seeds.add(file[0].split(" ")[i].toLong())
                    }
                        mapOfMaps["seeds"] = mutableListOf(seeds)
                    }
                }
            }
        }
        println(mapOfMaps)

    }
    private fun addMappingList(index: Int): MutableList<MutableList<Long>> {
        var lineIndex = index + 1
        val map = mutableListOf<MutableList<Long>>()
        while (file[lineIndex].isNotEmpty()) {
            val srcDestRangeList = mutableListOf<Long>()
            file[lineIndex].split(" ").forEach { nr ->
                srcDestRangeList.add(nr.toLong())
            }
            map.add(srcDestRangeList)
            lineIndex += 1
            if(lineIndex == file.size)
                break
        }
        return map
    }
}