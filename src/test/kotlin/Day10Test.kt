import day10.Day10
import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {
    val input = readLines("Day10")
    val testInput = readLines("Day10", env = "test")

    @Test
    fun testPart1() {
        assertEquals(13140, Day10.part1(testInput))
         assertEquals(16480, Day10.part1(input))
    }

    @Test
    fun testPart2() {
        assertEquals("""
##..##..##..##..##..##..##..##..##..##..
###...###...###...###...###...###...###.
####....####....####....####....####....
#####.....#####.....#####.....#####.....
######......######......######......####
#######.......#######.......#######.....
        """.trimIndent(), Day10.part2(testInput))
         assertEquals("""
 ###..#....####.####.#..#.#....###..###..
 #..#.#....#....#....#..#.#....#..#.#..#.
 #..#.#....###..###..#..#.#....#..#.###..
 ###..#....#....#....#..#.#....###..#..#.
 #....#....#....#....#..#.#....#....#..#.
 #....####.####.#.....##..####.#....###..
         """.trimIndent(), Day10.part2(input))
    }
}