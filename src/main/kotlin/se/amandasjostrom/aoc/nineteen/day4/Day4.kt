package se.amandasjostrom.aoc.nineteen.day4

import java.util.HashMap

class Day4 {

    fun partOne(start: Int, stop: Int) {
        var current: Int = start
        var count: Int = 0
        while (current <= stop) {
            println(current)
            val currentAsArray = current.toString().toCharArray()
            if (isIncreasing(currentAsArray) && hasDouble(currentAsArray)) {
                count++
            }
            current++
        }
        println("day 4 part 1 result: $count")

    }

    fun partTwo(start: Int, stop: Int) {
        var current: Int = start
        var count: Int = 0
        while (current <= stop) {
            println(current)
            val currentAsArray = current.toString().toCharArray()
            if (isIncreasing(currentAsArray) && hasTrueDouble(currentAsArray)) {
                count++
            }
            current++
        }
        println("day 4 part 1 result: $count")

    }

    private fun hasDouble(arr: CharArray): Boolean {
        for (i in 0 until arr.size-1) {
            if (arr[i] == arr[i + 1]) {
                return true
            }
        }
        return false
    }

    private fun isIncreasing(arr: CharArray): Boolean {
        for (i in 0 until arr.size-1) {
            if (arr[i + 1] < arr[i]) {
                return false
            }
        }
        return true

    }

    private fun hasTrueDouble(arr: CharArray): Boolean {
        val numberToCount = HashMap<Char, Int>()
        for (i in arr.indices) {
            val number = arr[i]
            val currentCount = numberToCount[number]
            if (currentCount == null) {
                numberToCount[number] = 1
            } else {
                numberToCount[number] = currentCount + 1
            }
        }
        for (count in numberToCount.values) {
            if (count == 2) {
                return true
            }
        }
        return false
    }
}
