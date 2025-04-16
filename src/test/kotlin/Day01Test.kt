import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day01Test {
    val input = readInput("Day01", env="test")

    @Test
    fun testPart1() {
        assertEquals(expected = 24000, actual = Day01.part1(input))
    }
}