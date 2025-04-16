object Day01 {
    fun part1(input: String): Int {
        return splitCalories(input)
            .map { calculateCalories(it) }
            .max()
    }

    fun part2(input: String): Int {
        return splitCalories(input)
            .map { calculateCalories(it) }
            .sortedDescending()
            .take(3)
            .sum()
    }

    private fun splitCalories(input: String): List<String> {
        return input.split("\n\n")
    }

    private fun calculateCalories(caloriesLines: String): Int {
        return caloriesLines.lines()
            .map { it.toInt() }
            .sum()
    }
}

fun main() {
    val input = readInput("Day01")
    printResult("1", Day01.part1(input))
    printResult("2", Day01.part2(input))
}
