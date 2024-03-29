package se.amandasjostrom.aoc.twentytwo.day1

import se.amandasjostrom.aoc.twentytwo.input

fun main(args: Array<String>) {
    val resultPartOne = Day1().runPartOne()
    println("result part one $resultPartOne")

    val resultPartTwo = Day1().runPartTwo()
    println("result part two $resultPartTwo")
}


class Day1 {
    private val input = input("${javaClass.simpleName.toLowerCase()}/input.txt")

    // mostCaloriesCarried
    fun runPartOne(): Int {
        val entriesPerElf = input.split("\n\n")

        return entriesPerElf
            .map { value -> getNumberOfCaloriesForElf(value) }
            .sortedByDescending { it }
            .first()
    }

    // caloriesCarriedByTopThree
    fun runPartTwo(): Int {
        val entriesPerElf = input.split("\n\n")

        return entriesPerElf
            .asSequence()
            .map { getNumberOfCaloriesForElf(it) }
            .sortedByDescending { it }
            .take(3)
            .sum()
    }

    private fun getNumberOfCaloriesForElf(it: String) = it.split("\n")
        .filter { it.isNotEmpty() }
        .sumBy { it.toInt() }
}
