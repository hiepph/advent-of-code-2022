package day02

import printResult
import readLines

enum class Rps(val score: Int) {
    ROCK(1), PAPER(2), SCISSOR(3);
}

enum class Result(val score: Int) {
    WIN(6), DRAW(3), LOSE(0);
}

interface ChoiceDecryption<T> {
    fun decryptChoice(choice: String): T
}

object PlayerChoiceDecryption : ChoiceDecryption<Rps> {
    override fun decryptChoice(choice: String): Rps {
        return when (choice) {
            "X" -> Rps.ROCK
            "Y" -> Rps.PAPER
            "Z" -> Rps.SCISSOR
            else -> throw InvalidInputException("Invalid player choice: $choice")
        }
    }
}

object OpponentChoiceDecryption : ChoiceDecryption<Rps> {
    override fun decryptChoice(choice: String): Rps {
        return when (choice) {
            "A" -> Rps.ROCK
            "B" -> Rps.PAPER
            "C" -> Rps.SCISSOR
            else -> throw InvalidInputException("Invalid opponent choice: $choice")
        }
    }
}

object PlayerChoiceDecryption2 : ChoiceDecryption<Result> {
    override fun decryptChoice(choice: String): Result {
        return when (choice) {
            "X" -> Result.LOSE
            "Y" -> Result.DRAW
            "Z" -> Result.WIN
            else -> throw InvalidInputException("Invalid player choice: $choice")
        }}
}

object Day02 {
    fun part1(lines: List<String>): Int {
        return lines.map { calculateRoundScore(it) }.sum()
    }

    fun part2(lines: List<String>): Int {
        return lines.map { calculateRoundScore2(it) }.sum()
    }

    private fun calculateRoundScore(round: String): Int {
        val (opponentEnc, playerEnc) = round.split(" ")
        val opponentChoice: Rps = OpponentChoiceDecryption.decryptChoice(opponentEnc)
        val playerChoice: Rps = PlayerChoiceDecryption.decryptChoice(playerEnc)

        val choiceScore = playerChoice.score
        val resultScore = when {
            playerChoice == opponentChoice -> Result.DRAW.score
            (playerChoice == Rps.ROCK && opponentChoice == Rps.SCISSOR) ||
                    (playerChoice == Rps.PAPER && opponentChoice == Rps.ROCK) ||
                    (playerChoice == Rps.SCISSOR && opponentChoice == Rps.PAPER) -> Result.WIN.score
            else -> Result.LOSE.score
        }
        return choiceScore + resultScore
    }

    private fun calculateRoundScore2(round: String): Int {
        val (opponentEnc, playerEnc) = round.split(" ")
        val opponentChoice: Rps = OpponentChoiceDecryption.decryptChoice(opponentEnc)
        val playerChoice: Result = PlayerChoiceDecryption2.decryptChoice(playerEnc)

        val resultScore = playerChoice.score
        val choiceScore = when (playerChoice) {
            Result.DRAW -> opponentChoice.score
            Result.WIN -> when (opponentChoice) {
                Rps.ROCK -> Rps.PAPER.score
                Rps.PAPER -> Rps.SCISSOR.score
                Rps.SCISSOR -> Rps.ROCK.score
            }
            Result.LOSE -> when (opponentChoice) {
                Rps.ROCK -> Rps.SCISSOR.score
                Rps.PAPER -> Rps.ROCK.score
                Rps.SCISSOR -> Rps.PAPER.score
            }
        }
        return resultScore + choiceScore
    }
}

fun main() {
    val input = readLines("Day02")
    printResult("1", Day02.part1(input))
    printResult("2", Day02.part2(input))
}
