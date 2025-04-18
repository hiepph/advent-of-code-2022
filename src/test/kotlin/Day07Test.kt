import day07.Day07
import kotlin.test.Test
import kotlin.test.assertEquals

class Day07Test {
    val input = readLines("Day07")
    val testInput = readLines("Day07", env = "test")

    @Test
    fun testPart1() {
        assertEquals(95437, Day07.part1(testInput))
        assertEquals(1449447, Day07.part1(input))
    }
}