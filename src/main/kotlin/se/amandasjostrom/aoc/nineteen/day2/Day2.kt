package se.amandasjostrom.aoc.nineteen.day2

import java.lang.IllegalArgumentException

class Day2 {
    fun partOne(input: String): Int {
        val integers: MutableList<Int> = input
                .split(',')
                .map { Integer.valueOf(it) }
                .toMutableList()

        restoreGravityAssistProgram(integers)

        runProgram(integers)

        val value = integers[0]
        println("day 2 part 1, value at position 0 is: $value")
        return value
    }

    private fun restoreGravityAssistProgram(integers: MutableList<Int>) {
        integers[1] = 12
        integers[2] = 2
    }

    private fun runProgram(integers: MutableList<Int>) {
        var position = 0
        while (position < integers.size) {
            val opcode = integers[position]
            if (opcode == 99) {
                break
            }

            when (opcode) {
                99 -> return
                1 -> {
                    compute(integers, position, addAction)
                }
                2 -> {
                    compute(integers, position, multiplyAction)
                }
                else -> throw IllegalArgumentException("opcode was not of legal values [1, 2, 99]")
            }
            position += 4
        }
    }

    private fun compute(integers: MutableList<Int>, position: Int, action: Action) {
        val firstValue = integers[integers[position + 1]]
        val secondValue = integers[integers[position + 2]]
        integers[integers[position + 3]] = action.execute(firstValue, secondValue)
    }

    companion object {
        val addAction = object : Action {
            override fun execute(a: Int, b: Int): Int {
                return a + b
            }
        }
        val multiplyAction = object : Action {
            override fun execute(a: Int, b: Int): Int {
                return a * b
            }
        }
    }
}

interface Action {
    fun execute(int1: Int, int2: Int): Int
}