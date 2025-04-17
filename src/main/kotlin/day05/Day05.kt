package day05

import printResult
import readLines
import java.util.Stack

interface CrateInfra {
    fun build(): Array<Stack<Char>>
}

interface CrateMover {
    fun move(stacks: Array<Stack<Char>>, from: Int, to: Int, count: Int)
}

object OneByOneCrateMover : CrateMover {
    override fun move(stacks: Array<Stack<Char>>, from: Int, to: Int, count: Int) {
        repeat(count) { stacks[to - 1].push(stacks[from - 1].pop()) }
    }
}

object AllAtOnceCrateMover : CrateMover {
    override fun move(stacks: Array<Stack<Char>>, from: Int, to: Int, count: Int) {
        val tempStack = Stack<Char>()
        with (tempStack) {
            repeat(count) { this.push(stacks[from - 1].pop()) }
            repeat(count) { stacks[to - 1].push(this.pop()) }
        }
    }
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
        lines.forEach { executeInstruction(instruction = it, stacks = stacks, mover = OneByOneCrateMover) }
        return stacks.map { it.peek() }.joinToString("")
    }

    fun part2(lines: List<String>, crateInfra: CrateInfra): String {
        val stacks = crateInfra.build()
        lines.forEach { executeInstruction(instruction = it, stacks = stacks, mover = AllAtOnceCrateMover) }
        return stacks.map { it.peek() }.joinToString("")
    }

    private fun executeInstruction(instruction: String, stacks: Array<Stack<Char>>, mover: CrateMover) {
        val regex = "move (\\d+) from (\\d+) to (\\d+)".toRegex()
        val matchedResult = regex.find(instruction) ?: throw IllegalArgumentException("Invalid instruction format: $instruction")
        val (count, from, to) = matchedResult.destructured.toList().map { it.toInt() }
        mover.move(stacks, from, to, count)
    }
}

fun main() {
    val input = readLines("Day05").drop(10)
    printResult("1", Day05.part1(input, InputCrateInfra))
    printResult("2", Day05.part2(input, InputCrateInfra))
}