@file:OptIn(ExperimentalStdlibApi::class)

package se.amandasjostrom.aoc.twentytwo.day5

import se.amandasjostrom.aoc.twentytwo.input

fun main(args: Array<String>) {
    val resultPartOne = Day5().runPartOne()
    println("result part one $resultPartOne")

    val resultPartTwo = Day5().runPartTwo()
    println("result part two $resultPartTwo")
}

class Day5 {
    private val input = Input.fromString(input("${javaClass.simpleName.toLowerCase()}/input.txt")
        .split("\n")
        .filter { it.isNotEmpty() }
    )

    fun runPartOne(): String {
        input.instructions.forEach { instruction ->
            repeat(instruction.count) {
                input.stacks[instruction.toStack].addLast(input.stacks[instruction.fromStack].removeLast())
            }
        }
        return input.stacks.map { it.last() }.joinToString(separator = "") { it }
    }

    fun runPartTwo(): String {
        input.instructions.forEach { instruction ->
            val toMove = ArrayDeque<String>()
            repeat(instruction.count){
                toMove.addLast(input.stacks[instruction.fromStack].removeLast())
            }
            toMove.reversed().forEach {
                input.stacks[instruction.toStack].addLast(it)
            }
        }
        return input.stacks.map { it.last() }.joinToString(separator = "") { it }
    }

    data class Input(
        val stacks: List<ArrayDeque<String>>,
        val instructions: List<Instruction>
    ) {
        companion object {
            fun fromString(input: List<String>): Input {
                val (instructions, stacks) = input.partition {
                    it.startsWith("move")
                }

                return Input(
                    buildStacks(stacks),
                    instructions.map { Instruction.fromInput(it) }
                )
            }

            private fun buildStacks(stacks: List<String>): List<ArrayDeque<String>> {
                val indexOfStackToStack = mutableMapOf<Int, ArrayDeque<String>>()
                fun indexOfStack(indexOfChar: Int) = indexOfChar / 4
                stacks.forEach {
                    it.forEachIndexed { index, char ->
                        if (char.isLetter()) {
                            val indexOfStack = indexOfStack(index)
                            val stack = indexOfStackToStack.computeIfAbsent(indexOfStack) { ArrayDeque() }
                            stack.addFirst(char.toString())
                        }
                    }
                }
                println(indexOfStackToStack.toSortedMap().values.toList())
                return indexOfStackToStack.toSortedMap().values.toList()
            }
        }
    }

    data class Instruction(val count: Int, val fromStack: Int, val toStack: Int) {
        companion object {
            fun fromInput(input: String): Instruction {
                    val parts = input.split(" ")
                    return Instruction(
                        parts[1].toInt(),
                        parts[3].toInt() - 1, // because list uses index 0
                        parts[5].toInt() - 1 // because list uses index 0
                )
            }
        }
    }

}
