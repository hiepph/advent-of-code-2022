package day10
    
import printResult
import readLines

data class Cpu(var cycle: Int, var register: Int) {
    val signals = mutableListOf<Pair<Int, Int>>()
    var crt: Array<Char>

    init {
        crt = Array(240) { '.' }
        crt[0] = '#'
    }

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

    fun showCrt(): String {
        return crt.toList()
            .chunked(40)
            .joinToString("\n") { it.joinToString("") }
    }

    private fun increaseCycle() {
        cycle++

        addSignal()
        drawSprite()
    }

    fun addSignal() {
        if ((cycle - 20) % 40 == 0) {
            signals.add(Pair(cycle, register))
        }

    }

    private fun drawSprite() {
        val pixel = (cycle - 1) % 40
        if (pixel in (register - 1)..(register + 1)) {
            crt[cycle - 1] = '#'
        }
    }
}

object Day10 {
    fun part1(lines: List<String>): Int {
        val cpu = Cpu(cycle = 1, register = 1)
        lines.forEach { instruction -> cpu.execute(instruction) }
        return cpu.signals.sumOf { it.first * it.second }
    }

    fun part2(lines: List<String>): String {
        val cpu = Cpu(cycle = 1, register = 1)
        lines.forEach { instruction -> cpu.execute(instruction) }
        return cpu.showCrt()
    }
}

fun main() {
    val input = readLines("Day10")
    printResult("1", Day10.part1(input))
    printResult("2", "\n" + Day10.part2(input))
}
