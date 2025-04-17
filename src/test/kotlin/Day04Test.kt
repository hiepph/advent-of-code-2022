import day04.Day04
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day04Test {
    val testInput = readLines("Day04", env = "test")
    val input = readLines("Day04")

    @Test
    fun testPart1() {
        assertEquals(expected = 2, actual = Day04.part1(testInput))
        assertEquals(expected = 560, actual = Day04.part1(input))
    }
}