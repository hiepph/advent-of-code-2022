import day03.Day03
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day03Test {
    val testInput = readLines("Day03", env = "test")
    val input = readLines("Day03")

    @Test
    fun testPart1() {
        assertEquals(expected = 157, actual = Day03.part1(testInput))
        assertEquals(expected = 8233, actual = Day03.part1(input))
    }

    @Test
    fun testPart2() {
        assertEquals(expected = 70, actual = Day03.part2(testInput))
        assertEquals(expected = 2821, actual = Day03.part2(input))
    }
}