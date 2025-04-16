package day02

import printResult
import readLines

enum class Rps(
    val score: Int
) {
    ROCK(1), PAPER(2), SCISSOR(3);
    fun score() = score
}

enum class Result(
    val score: Int
) {
    WIN(6), DRAW(3), LOSE(0);
    fun score() = score
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
    private const val winningScore = 6
    private const val drawingScore = 3
    private const val losingScore = 0

    fun part1(lines: List<String>): Int {
        return lines.map { calculateRoundScore(it, OpponentChoiceDecryption, PlayerChoiceDecryption) }.sum()
    }

    fun part2(lines: List<String>): Int {
        return lines.map { calculateRoundScore2(it, OpponentChoiceDecryption, PlayerChoiceDecryption2) }.sum()
    }

    private fun calculateRoundScore(round: String,
                                    opponentChoiceDecryption: ChoiceDecryption<Rps>,
                                    playerChoiceDecryption: ChoiceDecryption<Rps>
    ): Int {
        val (opponentEnc, playerEnc) = round.split(" ")
        val opponentChoice = opponentChoiceDecryption.decryptChoice(opponentEnc)
        val playerChoice = playerChoiceDecryption.decryptChoice(playerEnc)

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

    private fun calculateRoundScore2(round: String,
                                    opponentChoiceDecryption: ChoiceDecryption<Rps>,
                                    playerChoiceDecryption: ChoiceDecryption<Result>
    ): Int {
        val (opponentEnc, playerEnc) = round.split(" ")
        val opponentChoice = opponentChoiceDecryption.decryptChoice(opponentEnc)
        val playerChoice = playerChoiceDecryption.decryptChoice(playerEnc)

        return when (playerChoice) {
            Result.LOSE -> Result.LOSE.score() + when (opponentChoice) {
                Rps.ROCK -> Rps.SCISSOR.score()
                Rps.PAPER -> Rps.ROCK.score()
                Rps.SCISSOR -> Rps.PAPER.score()
            }
            Result.WIN -> Result.WIN.score() + when (opponentChoice) {
                Rps.ROCK -> Rps.PAPER.score()
                Rps.PAPER -> Rps.SCISSOR.score()
                Rps.SCISSOR -> Rps.ROCK.score()
            }
            Result.DRAW -> Result.DRAW.score() + when (opponentChoice) {
                Rps.ROCK -> Rps.ROCK.score()
                Rps.PAPER -> Rps.PAPER.score()
                Rps.SCISSOR -> Rps.SCISSOR.score()
            }
        }
    }
}

fun main() {
    val input = readLines("Day02")
    printResult("1", Day02.part1(input))
    printResult("2", Day02.part2(input))
}
