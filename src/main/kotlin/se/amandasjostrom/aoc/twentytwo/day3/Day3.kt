package se.amandasjostrom.aoc.twentytwo.day3

import se.amandasjostrom.aoc.twentytwo.input

fun main(args: Array<String>) {
    val resultPartOne = Day3().runPartOne()
    println("result part one $resultPartOne")

    val resultPartTwo = Day3().runPartTwo()
    println("result part two $resultPartTwo")
}

class Day3 {
    private val input = input("${javaClass.simpleName.toLowerCase()}/input.txt")
        .split("\n")
        .filter { it.isNotEmpty() }

    fun runPartOne(): Int {
        return input
            .map {
                val partLength = it.length / 2
                val compartment1 = it.subSequence(0, partLength).toString()
                val compartment2 = it.subSequence(partLength, it.length).toString()
                Pair(compartment1, compartment2)
            }.map { sharedItem(it) }
            .map { findPriority(it) }
            .sum()
    }

    private fun sharedItem(compartments: Pair<String, String>) =
        compartments.first.first { compartments.second.contains(it) }

    fun runPartTwo(): Int {
        return input
            .chunked(3)
            .map { findBadge(it) }
            .map { findPriority(it) }
            .sum()
    }

    private fun findBadge(group: List<String>): Char {
        return group[0].first {
            group[1].contains(it) && group[2].contains(it)
        }
    }

    private fun findPriority(item: Char): Int {
        val base = item.toInt()
        val priority = if (base > 96) {
            // small letters
            base - 96
        } else {
            // big letters
            base - 64 + 26
        }
        return priority
    }
}
