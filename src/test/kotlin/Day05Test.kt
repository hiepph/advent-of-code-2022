import day05.CrateInfra
import day05.Day05
import day05.InputCrateInfra
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

object TestCrateInfra : CrateInfra {
    override fun build(): Array<Stack<Char>> {
        return arrayOf(
            Stack<Char>().apply { push('Z'); push('N') },
            Stack<Char>().apply { push('M'); push('C'); push('D') },
            Stack<Char>().apply { push('P') }
        )
    }
}

class Day05Test {
    val testInput = readLines("Day05", env = "test").drop(5)
    val input = readLines("Day05").drop(10)

    @Test
    fun testPart1() {
        assertEquals(expected = "CMZ", actual = Day05.part1(testInput, TestCrateInfra))
        assertEquals(expected = "BSDMQFLSP", actual = Day05.part1(input, InputCrateInfra))
    }

    @Test
    fun testPart2() {
        assertEquals(expected = "MCD", actual = Day05.part2(testInput, TestCrateInfra))
        assertEquals(expected = "PGSQBFLDP", actual = Day05.part2(input, InputCrateInfra))
    }
}