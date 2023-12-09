package com.example.adventofcode
//import com.example.adventofcode.calibrationDayOne.Calibration
import com.example.adventofcode.engineSchematicDayThree.SumOfGearRatios
import com.example.adventofcode.engineSchematicDayThree.SumOfParts
import com.example.adventofcode.scratchCardsDayFour.ScratchCards
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AdventofcodeApplication

fun main(args: Array<String>) {
	runApplication<AdventofcodeApplication>(*args)
	println("this is main")
// 	Calibration().getData() //AdventOfCodeDay1Challenge1
//	Calibration().getData2()
//	CubeGame().getEligibleGames()
//	SumOfParts().calculateSumOfAdjacentNumbers()
	println(SumOfGearRatios().sumOfGears())
//	val res = ScratchCards().getResult()
//	println(res)
}

