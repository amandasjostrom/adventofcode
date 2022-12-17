package se.amandasjostrom.aoc.twentytwo.day4

import se.amandasjostrom.aoc.twentytwo.input

fun main(args: Array<String>) {
    val resultPartOne = Day4().runPartOne()
    println("result part one $resultPartOne")

    val resultPartTwo = Day4().runPartTwo()
    println("result part two $resultPartTwo")
}

class Day4 {
    private val input = input("${javaClass.simpleName.toLowerCase()}/input.txt")
        .split("\n")
        .filter { it.isNotEmpty() }
        .map { ElfPair.fromString(it) }

    fun runPartOne(): Int {
        return input.count { firstContainedInSecond(it) || secondContainedInFirst(it) }
    }

    private fun firstContainedInSecond(pair: ElfPair): Boolean {
        return pair.firstFrom>=pair.secondFrom && pair.firstTo<=pair.secondTo
    }

    private fun secondContainedInFirst(pair: ElfPair): Boolean {
        return pair.secondFrom >= pair.firstFrom && pair.secondTo <= pair.firstTo
    }

    fun runPartTwo(): Int {
        return input.count { (it.firstFrom <= it.secondTo) and (it.firstTo >= it.secondFrom) }
    }

    data class ElfPair(
        val firstFrom: Int,
        val firstTo: Int,
        val secondFrom: Int,
        val secondTo: Int
    ) {
        companion object {
            fun fromString(input: String): ElfPair {
                val parts = input.split(",")
                val first = parts[0].split("-")
                val second = parts[1].split("-")
                return ElfPair(
                    firstFrom = first[0].toInt(),
                    firstTo = first[1].toInt(),
                    secondFrom = second[0].toInt(),
                    secondTo = second[1].toInt()
                )
            }
        }
    }

}
