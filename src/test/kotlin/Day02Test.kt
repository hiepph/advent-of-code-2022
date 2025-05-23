import day02.Day02
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day02Test {
    val input = readLines("Day02")
    val testInput = readLines("Day02", env="test")

    @Test
    fun testPart1() {
        assertEquals(expected = 15, actual = Day02.part1(testInput))
        assertEquals(expected = 11603, actual = Day02.part1(input))
    }

    @Test
    fun testPart2() {
        assertEquals(expected = 12, actual = Day02.part2(testInput))
        assertEquals(expected = 12725, actual = Day02.part2(input))
    }
}