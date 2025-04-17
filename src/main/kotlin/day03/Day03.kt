package day03

import readLines

object Rucksack {
    fun getCommonItem(rucksack: String): Char {
        val compartments = rucksack.chunked(rucksack.length / 2)
        return compartments.map{ it.toSet() }
            .reduce { acc, set -> acc.intersect(set) }
            .first()
    }
}

object Group {
    fun getCommonItem(group: List<String>): Char {
        val rucksacks: List<Set<Char>> = group.map { it.toSet() }
        return rucksacks.reduce { acc, set -> acc.intersect(set) }.first()
    }
}

object Day03 {
    fun part1(lines: List<String>): Int {
        return lines.map { Rucksack.getCommonItem(it) }
            .map { getPriority(it) }
            .sum()
    }

    fun part2(lines: List<String>): Int {
        return lines.chunked(3)
            .map { Group.getCommonItem(it) }
            .map { getPriority(it) }
            .sum()
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
    println(Day03.part2(input))
}
