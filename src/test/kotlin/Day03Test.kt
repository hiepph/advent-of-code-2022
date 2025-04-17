import day03.Day03
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day03Test {
    val testInput = readLines("Day03", env = "test")

    @Test
    fun testPart1() {
        assertEquals(expected = 157, actual = Day03.part1(testInput))
    }
}