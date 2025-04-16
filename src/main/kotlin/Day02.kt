enum class Rps {
    ROCK, PAPER, SCISSOR
}

class InvalidInputException(message: String) : Exception(message)

object Day02 {
    private const val winningScore = 6
    private const val drawingScore = 3
    private const val losingScore = 0

    fun part1(lines: List<String>): Int {
        return lines.map { calculateRoundScore(it) }.sum()
    }

    private fun calculateRoundScore(line: String): Int {
        val (opponentEnc, playerEnc) = line.split(" ")
        val opponentChoice = when (opponentEnc) {
            "A" -> Rps.ROCK
            "B" -> Rps.PAPER
            "C" -> Rps.SCISSOR
            else -> throw InvalidInputException("Invalid opponent: $opponentEnc")
        }
        val playerChoice = when (playerEnc) {
            "X" -> Rps.ROCK
            "Y" -> Rps.PAPER
            "Z" -> Rps.SCISSOR
            else -> throw InvalidInputException("Invalid player choice: $playerEnc")
        }

        return when (playerChoice) {
            Rps.ROCK -> when (opponentChoice) {
                Rps.ROCK -> 1 + drawingScore
                Rps.PAPER -> 1 + losingScore
                Rps.SCISSOR -> 1 + winningScore
            }
            Rps.PAPER -> when (opponentChoice) {
                Rps.ROCK -> 2 + winningScore
                Rps.PAPER -> 2 + drawingScore
                Rps.SCISSOR -> 2 + losingScore
            }
            Rps.SCISSOR -> when (opponentChoice) {
                Rps.ROCK -> 3 + losingScore
                Rps.PAPER -> 3 + winningScore
                Rps.SCISSOR -> 3 + drawingScore
            }
        }
    }
}

fun main() {
    val input = readLines("Day02")
    printResult("1", Day02.part1(input))
//    printResult("2", Day02.part2(input))
}
