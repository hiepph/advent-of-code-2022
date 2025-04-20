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
        // right: + x sign
        // left: - x sign
        return copy(
            x = (other.x - x).sign + x,
            y = (other.y - y).sign + y
        )
    }
}

object Day09 {
    fun part1(lines: List<String>): Int {
        val tailPositions = mutableSetOf(Point(0, 0))

        var tail = Point(0, 0)
        var head = Point(0, 0)

        headMovements(lines).forEach { headMovement ->
            head = head.move(headMovement)
            if (!tail.touches(head)) {
                tail = tail.moveTowards(head)
                tailPositions.add(tail)
            }
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

    fun part2(lines: List<String>): Int {
        return -1
    }
}

fun main() {
    val input = readLines("Day09")
    printResult("1", Day09.part1(input))
    // printResult("2", Day09.part2(input))
}
