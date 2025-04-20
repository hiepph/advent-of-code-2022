import day09.Day09
import kotlin.test.Test
import kotlin.test.assertEquals

class Day09Test {
    val input = readLines("Day09")
    val testInput = readLines("Day09", env = "test")
    val testInput2 = readLines("Day09_2", env = "test")

    @Test
    fun testPart1() {
        assertEquals(13, Day09.part1(testInput))
         assertEquals(6376, Day09.part1(input))
    }

    @Test
    fun testPart2() {
        assertEquals(1, Day09.part2(testInput))
        assertEquals(36, Day09.part2(testInput2))
        assertEquals(2607, Day09.part2(input))
    }
}