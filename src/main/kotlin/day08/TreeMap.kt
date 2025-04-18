package day08

import product

class TreeMap {
    val rowLength: Int
    val columnLength: Int
    val trees: Array<Array<Int>>

    constructor(input: String) {
        rowLength = input.lines().size
        columnLength = input.lines().first().length
        trees = Array(rowLength) { Array(columnLength) { 0 } }

        input.split("\n").forEachIndexed { row, line ->
            line.forEachIndexed() { column, height ->
                trees[row][column] = height.digitToInt()
            }
        }
    }

    fun countVisibleTrees(): Int {
        var result = 0

        trees.forEachIndexed { row, heights ->
            heights.forEachIndexed { column, height ->
                if (isAtEdge(row, column)) {
                    result++
                } else  {
                    val left = getLeftTrees(row, column)
                    val right = getRightTrees(row, column)
                    val top = getTopTrees(row, column)
                    val bottom = getBottomTrees(row, column)

                    if (left.all { it < height } || right.all { it < height } || top.all { it < height } || bottom.all { it < height }) {
                        result++
                    }
                }
            }
        }

        return result
    }

    fun calculateScenicScores(): List<List<Int>> {
        return trees.mapIndexed() { row, heights ->
            heights.mapIndexed() { column, height ->
                if (isAtEdge(row, column)) {
                    0
                } else {
                    listOf(
                        getLeftTrees(row, column).reversed(),
                        getRightTrees(row, column),
                        getTopTrees(row, column).reversed(),
                        getBottomTrees(row, column)
                    ).map { calculateScoreFromSide(it, height) }
                    .product()
                }
            }
        }
    }

    private fun calculateScoreFromSide(lineOfTrees: List<Int>, height: Int): Int {
        return lineOfTrees.takeWhile { it < height }.count() + if (lineOfTrees.any { it >= height }) 1 else 0
    }

    private fun isAtEdge(row: Int, column: Int) = row == 0 || column == 0 || row == rowLength - 1 || column == columnLength - 1

    private fun getLeftTrees(row: Int, column: Int) = trees[row].slice(0 until column)
    private fun getRightTrees(row: Int, column: Int) = trees[row].slice(column + 1 until columnLength)
    private fun getTopTrees(row: Int, column: Int) = trees.slice(0 until row).map { it[column] }
    private fun getBottomTrees(row: Int, column: Int) = trees.slice(row + 1 until rowLength).map { it[column] }
}