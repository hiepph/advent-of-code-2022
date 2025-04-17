package day03

import readLines

object Day03 {
    fun part1(lines: List<String>): Int {
        return lines.map { getCommonItem(it) }
            .map { getPriority(it) }
            .sum()
    }

    private fun getCommonItem(rucksack: String): Char {
        val compartmentSize = rucksack.length / 2
        val firstCompartment = rucksack.substring(0, compartmentSize).toSet()
        val secondCompartment = rucksack.substring(compartmentSize).toSet()

        return firstCompartment.intersect(secondCompartment).first()
    }

    private fun getPriority(item: Char): Int {
        return when {
            item.isLowerCase() -> 1 + (item - 'a')
            else -> 27 + (item - 'A')
        }
    }
}

fun main() {
    val input = readLines("Day03")
    println(Day03.part1(input))
}
