package se.amandasjostrom.aoc.twentytwo.day2

import se.amandasjostrom.aoc.twentytwo.day2.RockPaperScissors.PAPER
import se.amandasjostrom.aoc.twentytwo.day2.RockPaperScissors.ROCK
import se.amandasjostrom.aoc.twentytwo.day2.RockPaperScissors.SCISSORS
import se.amandasjostrom.aoc.twentytwo.input

fun main(args: Array<String>) {
    val resultPartOne = Day2().runPartOne()
    println("result part one $resultPartOne")

    val resultPartTwo = Day2().runPartTwo()
    println("result part two $resultPartTwo")
}

class Day2 {
    private val input = input("${javaClass.simpleName.toLowerCase()}/input.txt")

    private val winningCombinations = listOf(
        Round(opponent = ROCK, you = PAPER),
        Round(opponent = PAPER, you = SCISSORS),
        Round(opponent = SCISSORS, you = ROCK)
    )

    private val loosingCombinations = listOf(
        Round(opponent = ROCK, you = SCISSORS),
        Round(opponent = PAPER, you = ROCK),
        Round(opponent = SCISSORS, you = PAPER)
    )

    fun runPartOne(): Int {
        return input.split("\n")
            .filter { it.isNotEmpty() }
            .map { Round(opponent = RockPaperScissors.fromInput(it[0]), you = RockPaperScissors.fromInput(it[2])) }
            .map { score(it) }
            .sum()
    }

    fun runPartTwo(): Int {
        return input.split("\n")
            .filter { it.isNotEmpty() }
            .map { input ->
                val opponent = RockPaperScissors.fromInput(input[0])
                val you = when (val outcome = input[2]) {
                    'X' -> loosingCombinations.first { it.opponent == opponent }.you
                    'Y' -> opponent
                    'Z' -> winningCombinations.first { it.opponent == opponent }.you
                    else -> throw IllegalArgumentException("unexpected outcome in input: $outcome")
                }
                Round(opponent = opponent, you = you)
            }
            .map { score(it) }
            .sum()
    }

    private fun score(round: Round): Int {
        val resultScore = if (winningCombinations.contains(round)) {
            6
        } else if (loosingCombinations.contains(round)) {
            0
        } else {
            3
        }

        val shapeScore = when (round.you) {
            ROCK -> 1
            PAPER -> 2
            SCISSORS -> 3
        }

        return resultScore + shapeScore
    }
}

data class Round(val opponent: RockPaperScissors, val you: RockPaperScissors)

enum class RockPaperScissors {
    ROCK,
    PAPER,
    SCISSORS;

    companion object {
        fun fromInput(value: Char): RockPaperScissors {
            return when (value) {
                'A', 'X' -> ROCK
                'B', 'Y' -> PAPER
                'C', 'Z' -> SCISSORS
                else -> throw IllegalArgumentException("invalid input: $value")
            }
        }
    }
}
