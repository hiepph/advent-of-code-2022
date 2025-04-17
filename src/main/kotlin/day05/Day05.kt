package day05

import printResult
import readLines
import java.util.Stack

interface CrateInfra {
    fun build(): Array<Stack<Char>>
}

object InputCrateInfra : CrateInfra {
    override fun build(): Array<Stack<Char>> {
        return arrayOf(
            Stack<Char>().apply { "DBJV".forEach { crate -> this.push(crate) } },
            Stack<Char>().apply { "PVBWRDF".forEach { crate -> this.push(crate) } },
            Stack<Char>().apply { "RGFLDCWQ".forEach { crate -> this.push(crate) } },
            Stack<Char>().apply { "WJPMLNDB".forEach { crate -> this.push(crate) } },
            Stack<Char>().apply { "HNBPCSQ".forEach { crate -> this.push(crate) } },
            Stack<Char>().apply { "RDBSNG".forEach { crate -> this.push(crate) } },
            Stack<Char>().apply { "ZBPMQFSH".forEach { crate -> this.push(crate) } },
            Stack<Char>().apply { "WLF".forEach { crate -> this.push(crate) } },
            Stack<Char>().apply { "SVFMR".forEach { crate -> this.push(crate) } }
        )
    }
}

object Day05 {
    fun part1(lines: List<String>, crateInfra: CrateInfra): String {
        val stacks = crateInfra.build()
        lines.forEach { executeInstruction(it, stacks) }
        return stacks.map { it.peek() }.joinToString("")
    }

    private fun executeInstruction(instruction: String, stacks: Array<Stack<Char>>) {
        val regex = "move (\\d+) from (\\d+) to (\\d+)".toRegex()
        val matchedResult = regex.find(instruction) ?: throw IllegalArgumentException("Invalid instruction format: $instruction")
        val (count, from, to) = matchedResult.destructured.toList().map { it.toInt() }

        repeat(count) {
            stacks[to-1].push(stacks[from-1].pop())
        }
    }
}

fun main() {
    val input = readLines("Day05").drop(10)
    printResult("1", Day05.part1(input, InputCrateInfra))
}