package com.example.adventofcode.seedToSoilDayFive

import java.io.File

class SeedToSoil2 {
    private val file = readFileAsLinesUsingUseLines("seedToSoilTest.txt")
    private fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
    private fun processFile(): MutableMap<String, MutableList<MutableList<Long>>> {
        val mapOfMaps = mutableMapOf<String, MutableList<MutableList<Long>>>()
        //val seeds = mutableListOf<Long>()
        val seedsList = mutableListOf<MutableList<Long>>()
        file.forEachIndexed { index, item ->
            val name = item.split(" ")[0]
            with(name) {
                when {
                    contains("to") -> {
                        mapOfMaps[name] = addMappingList(index)
                    }

                    contains("seeds:") -> {
                        for (i in 2..<file[0].split(" ").size) {
                            val seeds = mutableListOf<Long>()
                            val sLine = file[0].split(" ")
                            println(sLine[i].toLong() + sLine[i - 1].toLong())
                            val rangeEnd = sLine[i].toLong() + sLine[i - 1].toLong()
                            //                      for(j in sLine[i-1].toLong()..< rangeEnd) {
                            for(j in 1..< sLine.size) {
                                seeds.add(sLine[j].toLong())
                                println(seeds)
                            }
                            seedsList.add(seeds)
                        }
                        mapOfMaps["seeds"] = seedsList
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
        val seeds = map["humidity-to-location"]
        seeds?.get(0)?.forEachIndexed { index, range ->
            if (index % 2 != 0) {
                val seed = seeds[0][index-1]
                var subResult = passThruMap(map, "temperature-to-humidity", seed, range)
                subResult = passThruMap(map, "light-to-temperature", subResult, range)
                subResult = passThruMap(map, "water-to-light", subResult, range)
                subResult = passThruMap(map, "fertilizer-to-water", subResult, range)
                subResult = passThruMap(map, "soil-to-fertilizer", subResult, range)
                subResult = passThruMap(map, "seed-to-soil", subResult, range)
                subResult = passThruMap(map,"seeds" , subResult, range)


                if (res.isNotEmpty()) {
                    if (subResult < res[0]) {
                        res.removeAt(0)
                        res.add(subResult)
                    }
                } else {
                    res.add(subResult)
                }
            }
                    print(res)

          //      res.add(subResult)
            }
        println(res)
        val sortedRes = res.toSortedSet()
        println(sortedRes.first())
    }

    private fun passThruMap(
        map: MutableMap<String, MutableList<MutableList<Long>>>,
        key: String,
        seed: Long,
        range: Long
    ): Long {
        val pathMap = mutableMapOf<String, LongArray>()
        var seedItem = seed
        var rangeItem = range
        var subResult: Long = -1

        map[key]?.forEach {
            val diff = it[0]-it[1]
            if (it[1] <= seedItem) {
            //    subResult = seed - it[1] + it[0]
                seedItem = seedItem+it[2] - it[1] + it[0]
                pathMap["$seedItem,${seedItem+it[1]+it[2]}"]
                while(seedItem.toInt() != 0) {
                    if (it[1] + it[2] < seedItem + rangeItem) {
                        val path = longArrayOf(seedItem + diff, it[1] + it[2] + diff)
                        rangeItem = seedItem + rangeItem - it[1] + it[2]
                        seedItem = it[1] + it[2]
                    } else {
                        val path = longArrayOf(seedItem + diff, seedItem + rangeItem + diff)
                        rangeItem = 0
                        seedItem = 0
                    }
                //    pathMap[path] = path
                }

            }
        }
        if (subResult == (-1).toLong()) {
            subResult = seed
        }
        return subResult
    }
}

