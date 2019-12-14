package se.amandasjostrom.aoc.nineteen.day2

import se.amandasjostrom.aoc.nineteen.intcomputer.runIntComputer
import java.lang.IllegalArgumentException

class Day2 {
    fun partOne(input: String): Int {
        val integers: MutableList<Int> = input
                .split(',')
                .map { Integer.valueOf(it) }
                .toMutableList()

        restoreGravityAssistProgram(integers)

        runIntComputer(integers)

        val value = integers[0]
        println("day 2 part 1, value at position 0 is: $value")
        return value
    }

    private fun restoreGravityAssistProgram(integers: MutableList<Int>) {
        integers[1] = 12
        integers[2] = 2
    }

    fun partTwo(input: String): Int {
        val integers: MutableList<Int> = input
                .split(',')
                .map { Integer.valueOf(it) }
                .toMutableList()

        val values = findNounAndVerb(integers)

        val result = 100 * values.first + values.second
        println("day 2 part 1, noun: ${values.first} & verb ${values.second}, result: $result")
        return result
    }

    private fun findNounAndVerb(integers: MutableList<Int>): Pair<Int, Int> {
        for (noun in 0..99) {
            for (verb in 0..99) {
                val copy = integers.toMutableList()
                setNounAndVerb(copy, noun, verb)
                runIntComputer(copy)
                val value = copy[0]
                if (value == 19690720) {
                    return Pair(noun, verb)
                }
            }
        }
        throw IllegalArgumentException("no combination of noun and verb created expected outcome")
    }

    private fun setNounAndVerb(integers: MutableList<Int>, noun: Int, verb: Int) {
        integers[1] = noun
        integers[2] = verb
    }
}