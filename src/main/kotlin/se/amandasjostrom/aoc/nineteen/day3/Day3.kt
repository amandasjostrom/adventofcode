package se.amandasjostrom.aoc.nineteen.day3

import java.lang.Math.abs

class Day3 {

    fun partOne(input: String): Int {
        val (map, centralPort) = plotWiresOnMap(input)
        return map.findDistanceToIntersectionClosestToCentralPort(centralPort)
    }

    fun partTwo(input: String): Int {
        val (map, centralPort) = plotWiresOnMap(input)
        return map.fewestCombinedStepsNeededToReachIntersection(centralPort)
    }

    private fun plotWiresOnMap(input: String): Pair<WireMap, Position> {
        val instructions = input.split('\n')
        val map = WireMap()
        val centralPort = Position(0, 0)
        putWireOnWireMap(instructions[0], "wire0", map, centralPort)
        putWireOnWireMap(instructions[1], "wire1", map, centralPort)
        return Pair(map, centralPort)
    }

    private fun putWireOnWireMap(pathForWire: String, wireId: String, map: WireMap, startPosition: Position) {
        val instructions = pathForWire.split(',')
        var currentPosition = startPosition
        var totalDistance = 0

        for (instruction in instructions) {
            val direction = instruction[0]
            val distance = Integer.valueOf(instruction.substring(1))
            for (x in 1..distance) {
                totalDistance+=1
                when (direction) {
                    'L' -> {
                        currentPosition = Position(currentPosition.x - 1, currentPosition.y)
                    }
                    'R' -> {
                        currentPosition = Position(currentPosition.x + 1, currentPosition.y)
                    }
                    'U' -> {
                        currentPosition = Position(currentPosition.x, currentPosition.y + 1)
                    }
                    'D' -> {
                        currentPosition = Position(currentPosition.x, currentPosition.y - 1)
                    }
                }
                map.placeWireAtPosition(wireId, currentPosition, totalDistance)
            }
        }
    }
}

private class WireMap {

    private val map = emptyMap<Position, MutableMap<String, Int>>().toMutableMap()

    fun placeWireAtPosition(wireId: String, position: Position, distance: Int) {
        if (map.containsKey(position)) {
            map[position]!!.putIfAbsent(wireId, distance)
        } else {
            map[position] = mutableMapOf(Pair(wireId, distance))
        }
    }

    fun findDistanceToIntersectionClosestToCentralPort(centralPort: Position): Int {
        return map.filter { (k, v) -> v.size > 1 && k != centralPort }
                .map { (k, _) -> k to manhattanDistanceBetween(k, centralPort) }
                .sortedBy { (_, v) -> v }
                .first().second
    }

    private fun manhattanDistanceBetween(pos1: Position, pos2: Position): Int {
        return abs(pos1.x - pos2.x) + abs(pos1.y - pos2.y)
    }

    fun fewestCombinedStepsNeededToReachIntersection(centralPort: Position): Int {
        return map.filter { (k, v) -> v.size > 1 && k != centralPort }
                .map { (k, _) -> combinedSteps(k) }
                .sorted()
                .first()
    }

    private fun combinedSteps(position: Position): Int {
        return map[position]!!.map { (_,v) -> v }.sum()
    }
}

private class Position(val x: Int, val y: Int) {

    override fun hashCode(): Int {
        return x.hashCode() + y.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other is Position) {
            return this.x == other.x && this.y == other.y
        }
        return false
    }
}
