package day08
    
import printResult
import readInput

object Day08 {
    fun part1(input: String): Int {
        val map = TreeMap(input)
        return map.countVisibleTrees()
    }

    fun part2(input: String): Int {
        val map = TreeMap(input)
        return map.calculateScenicScores()
            .flatten()
            .max()
    }
}

fun main() {
    val input = readInput("Day08")
    printResult("1", Day08.part1(input))
    printResult("2", Day08.part2(input))
}
