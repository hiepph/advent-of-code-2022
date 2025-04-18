import day08.Day08
import kotlin.test.Test
import kotlin.test.assertEquals

class Day08Test {
    val input = readInput("Day08")
    val testInput = readInput("Day08", env = "test")

    @Test
    fun testPart1() {
        assertEquals(21, Day08.part1(testInput))
         assertEquals(1684, Day08.part1(input))
    }

    @Test
    fun testPart2() {
        assertEquals(8, Day08.part2(testInput))
         assertEquals(486540, Day08.part2(input))
    }
}