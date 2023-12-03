package com.example.adventofcode
//import com.example.adventofcode.calibrationDayOne.Calibration
import com.example.adventofcode.cubeGameDayTwo.CubeGame
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AdventofcodeApplication

fun main(args: Array<String>) {
	runApplication<AdventofcodeApplication>(*args)
	println("this is main")
// 	Calibration().getData() //AdventOfCodeDay1Challenge1
//	Calibration().getData2()
	CubeGame().getEligibleGames()
}

