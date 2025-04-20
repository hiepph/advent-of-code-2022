package day10
    
import printResult
import readLines

data class Cpu(var cycle: Int, var register: Int) {
    val signals = mutableListOf<Pair<Int, Int>>()

    fun execute(instruction: String) {
        when (instruction) {
            "noop" -> increaseCycle()
            else -> { // "addx <value>"
                val value = instruction.split(" ")[1].toInt()
                increaseCycle()
                register += value
                increaseCycle()
            }
        }
    }

    private fun increaseCycle() {
        cycle++
        if ((cycle - 20) % 40 == 0) {
            signals.add(Pair(cycle, register))
        }
    }

}

object Day10 {
    fun part1(lines: List<String>): Int {
        val cpu = Cpu(cycle = 1, register = 1)
        lines.forEach { instruction -> cpu.execute(instruction) }
        return cpu.signals.sumOf { it.first * it.second }
    }

    fun part2(lines: List<String>): Int {
        return -1
    }
}

fun main() {
    val input = readLines("Day10")
    printResult("1", Day10.part1(input))
    // printResult("2", Day10.part2(input))
}
