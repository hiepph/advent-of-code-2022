object Day01 {
    fun part1(input: String): Int {
        return input.split("\n\n")
            .map { calculateCalories(it) }
            .max()
    }

    private fun calculateCalories(caloriesLines: String): Int {
        return caloriesLines.lines()
            .map { it.toInt() }
            .sum()
    }

//    fun part2(input: List<String>): Int {
//        return 0
//    }
}

fun main() {
    val input = readInput("Day01")
    Day01.part1(input).println()
}
