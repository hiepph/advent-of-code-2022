import day01.Day01
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day01Test {
    val input = readInput("Day01")
    val testInput = readInput("Day01", env="test")

    @Test
    fun testPart1() {
        assertEquals(expected = 24000, actual = Day01.part1(testInput))
        assertEquals(expected = 70116, actual = Day01.part1(input))
    }

    @Test
    fun testPart2() {
        assertEquals(expected = 45000, actual = Day01.part2(testInput))
        assertEquals(expected = 206582, actual = Day01.part2(input))
    }
}