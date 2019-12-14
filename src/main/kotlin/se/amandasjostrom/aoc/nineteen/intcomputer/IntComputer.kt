package se.amandasjostrom.aoc.nineteen.intcomputer

import java.lang.IllegalArgumentException

fun runIntComputer(integers: MutableList<Int>) {
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

interface Action {
    fun execute(a: Int, b: Int): Int
}