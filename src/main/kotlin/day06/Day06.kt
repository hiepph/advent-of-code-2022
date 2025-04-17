package day06

import printResult
import readInput

object Day06 {
    fun part1(input: String): Int {
        return findMarker(input, 4)
    }

    fun part2(input: String): Int {
        return findMarker(input, 14)
    }

    private fun findMarker(input: String, numberOfDifferentCharacters: Int): Int {
        return input.windowed(numberOfDifferentCharacters)
            .indexOfFirst { it.toSet().size == numberOfDifferentCharacters } + numberOfDifferentCharacters
    }
}

fun main() {
    val input = readInput("Day06")
    printResult("1", Day06.part1(input))
    printResult("2", Day06.part2(input))
}