package day09
    
import printResult
import readLines
import kotlin.math.absoluteValue
import kotlin.math.sign

data class Point(val x: Int, val y: Int) {
    fun move(direction: Char): Point {
        return when (direction) {
            'U' -> copy(y = y + 1)
            'D' -> copy(y = y - 1)
            'L' -> copy(x = x - 1)
            'R' -> copy(x = x + 1)
            else -> throw IllegalArgumentException("Unknown direction $direction")
        }
    }

    fun touches(other: Point): Boolean {
        return (x - other.x).absoluteValue <= 1 && (y - other.y).absoluteValue <= 1
    }

    fun moveTowards(other: Point): Point {
        // up: + y sign
        // down: - y sign
        // left: - x sign
        // right: + x sign
        return Point(
            x = (other.x - x).sign + x,
            y = (other.y - y).sign + y
        )
    }
}

object Day09 {
    fun part1(lines: List<String>) = followHead(lines, 2)
    fun part2(lines: List<String>) = followHead(lines, 10)

    private fun followHead(lines: List<String>, knots: Int): Int {
        val tailPositions = mutableSetOf(Point(0, 0))

        val rope = Array(knots) { Point(0, 0) }

        headMovements(lines).forEach { headDirection->
            rope[0] = rope[0].move(headDirection)
            rope.indices.windowed(2).forEach { (head, tail) ->
                if (!rope[tail].touches(rope[head])) {
                    rope[tail] = rope[tail].moveTowards(rope[head])
                }
            }
            tailPositions.add(rope.last())
        }

        return tailPositions.size
    }

    private fun headMovements(lines: List<String>): String {
        return lines.joinToString("") { line ->
            val (direction, distance) = line.split(" ")
            val steps = distance.toInt()
            direction.repeat(steps)
        }
    }

}

fun main() {
    val input = readLines("Day09")
    printResult("1", Day09.part1(input))
    printResult("2", Day09.part2(input))
}
