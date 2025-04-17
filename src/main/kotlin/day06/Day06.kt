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
        for (i in 0..input.length - numberOfDifferentCharacters) {
            val window = input.substring(i, i + numberOfDifferentCharacters)
            if (window.toSet().size == numberOfDifferentCharacters) {
                return i + numberOfDifferentCharacters
            }
        }

        return -1
    }
}

fun main() {
    val input = readInput("Day06")
    printResult("1", Day06.part1(input))
    printResult("2", Day06.part2(input))
}