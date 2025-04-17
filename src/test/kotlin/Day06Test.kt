import day06.Day06
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day06Test {
    val testInputs = readLines("Day06", env = "test")
    val input = readInput("Day06")

    @Test
    fun testPart1() {
        val testResults = listOf(7, 5, 10)
        (testResults zip testInputs).forEach { (expected, testInput) ->
            assertEquals(expected = expected, actual = Day06.part1(testInput))
        }

        assertEquals(expected = 1965, actual = Day06.part1(input))
    }

    @Test
    fun testPart2() {
        assertEquals(expected = 2773, actual = Day06.part2(input))
    }
}