package day08
    
import printResult
import readInput

object Day08 {
    fun part1(input: String): Int {
        val map = buildMap(input)
        return countVisibleTrees(map)
    }

    fun part2(input: String): Int {
        val map = buildMap(input)
        return calculateScenicScores(map)
            .max()
    }

    private fun countVisibleTrees(map: Array<Array<Int>>): Int {
        var result = 0
        val rowLength = map.size
        val columnLength = map[0].size

        map.forEachIndexed { row, trees ->
            trees.forEachIndexed { column, height ->
                if (row == 0 || column == 0 || row == rowLength - 1 || column == columnLength - 1) {
                    result++
                } else  {
                    val left = map[row].slice(0 until column)
                    val right = map[row].slice(column + 1 until columnLength)
                    val top = map.slice(0 until row).map { it[column] }
                    val bottom = map.slice(row + 1 until rowLength).map { it[column] }

                    if (left.all { it < height } || right.all { it < height } || top.all { it < height } || bottom.all { it < height }) {
                        result++
                    }
                }
            }
        }

        return result
    }

    private fun calculateScenicScores(map: Array<Array<Int>>): List<Int> {
        val rowLength = map.size
        val columnLength = map[0].size

        return map.flatMapIndexed() { row, trees ->
            trees.mapIndexed() { column, height ->
                if (row == 0 || column == 0 || row == rowLength - 1 || column == columnLength - 1) {
                    0
                } else {
                    val left = map[row].slice(0 until column)
                    val right = map[row].slice(column + 1 until columnLength)
                    val top = map.slice(0 until row).map { it[column] }
                    val bottom = map.slice(row + 1 until rowLength).map { it[column] }

                    val leftScore = left.reversed().takeWhile { it < height }.count() + if (left.any { it >= height }) 1 else 0
                    val rightScore = right.takeWhile { it < height }.count() + if (right.any { it >= height }) 1 else 0
                    val topScore = top.reversed().takeWhile { it < height }.count() + if (top.any { it >= height }) 1 else 0
                    val bottomScore = bottom.takeWhile { it < height }.count() + if (bottom.any { it >= height }) 1 else 0

                    leftScore * rightScore * topScore * bottomScore
                }
            }
        }
    }

    private fun buildMap(input: String): Array<Array<Int>> {
        val rowLength = input.lines().size
        val columnLength = input.lines().first().length
        var map = Array(rowLength) { Array(columnLength) { 0 } }

        input.split("\n").forEachIndexed { row, line ->
            line.forEachIndexed() { column, height ->
                map[row][column] = height.digitToInt()
            }
        }

        return map
    }
}

fun main() {
    val input = readInput("Day08")
    printResult("1", Day08.part1(input))
    printResult("2", Day08.part2(input))
}
