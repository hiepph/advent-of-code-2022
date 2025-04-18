import day08.Day08
import kotlin.test.Test
import kotlin.test.assertEquals

class Day08Test {
    val input = readInput("Day08")
    val testInput = readInput("Day08", env = "test")

    @Test
    fun testPart1() {
        assertEquals(21, Day08.part1(testInput))
        // assertEquals(0, Day08.part1(input))
    }

    @Test
    fun testPart2() {
        assertEquals(0, Day08.part2(testInput))
        // assertEquals(0, Day08.part2(input))
    }
}