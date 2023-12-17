package com.example.adventofcode.seedToSoilDayFive

import java.io.File

class SeedToSoil {
    private val file = readFileAsLinesUsingUseLines("seedToSoil.txt")
    private fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
    private fun processFile(): MutableMap<String, MutableList<MutableList<Long>>> {
        val mapOfMaps = mutableMapOf<String, MutableList<MutableList<Long>>>()
        val seeds = mutableListOf<Long>()
        file.forEachIndexed { index, item ->
            val name = item.split(" ")[0]
            with(name) {
                when {
                    contains("to") -> {
                        mapOfMaps[name] = addMappingList(index)
                    }

                    contains("seeds:") -> {
                        for (i in 1..<file[0].split(" ").size) {
                            seeds.add(file[0].split(" ")[i].toLong())
                        }
                        mapOfMaps["seeds"] = mutableListOf(seeds)
                    }
                }
            }
        }
        println(mapOfMaps)
        return mapOfMaps

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
            if (lineIndex == file.size)
                break
        }
        return map
    }

    fun getLocationNumbers() {
        val res = mutableListOf<Long>()
        val map = processFile()
        val seeds = map["seeds"]
        seeds?.get(0)?.forEach { seed ->
            var subResult: Long = passThruMap(map, "seed-to-soil", seed)
            subResult = passThruMap(map, "soil-to-fertilizer", subResult)
            subResult = passThruMap(map, "fertilizer-to-water", subResult)
            subResult = passThruMap(map, "water-to-light", subResult)
            subResult = passThruMap(map, "light-to-temperature", subResult)
            subResult = passThruMap(map, "temperature-to-humidity", subResult)
            subResult = passThruMap(map, "humidity-to-location", subResult)
            res.add(subResult)
        }
        println(res)
        val sortedRes = res.toSortedSet()
        println(sortedRes.first())
    }

    private fun passThruMap(map: MutableMap<String, MutableList<MutableList<Long>>>, key: String, seed: Long): Long {
        var subResult: Long = -1
        map[key]?.forEach {
            if (seed >= it[1] && seed <= it[2] + it[1]) {
                subResult = seed - it[1] + it[0]
            }
        }
        if (subResult == (-1).toLong()) {
            subResult = seed
        }
        return subResult
    }
}