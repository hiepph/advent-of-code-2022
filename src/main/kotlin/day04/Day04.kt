package day04

import printResult
import readInput
import readLines

object Day04 {
    fun part1(lines: List<String>): Int {
       return lines.filter { isFullyContained(it) }
           .count()
    }

    private fun isFullyContained(assignment: String): Boolean {
        val (firstSection: List<Int>, secondSection: List<Int>) = assignment.split(",")
            .map { it.split("-").map { it.toInt() } }
        return firstSection[0] <= secondSection[0] && firstSection[1] >= secondSection[1] ||
                secondSection[0] <= firstSection[0] && secondSection[1] >= firstSection[1];
    }
}

fun main() {
    val input = readLines("Day04")
    printResult("1", Day04.part1(input))
}