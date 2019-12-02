package se.amandasjostrom.aoc.nineteen.day1

import org.junit.Assert.assertEquals
import org.junit.Test

class Day1Test {

    @Test
    fun sumForModules() {
        val sumOfFuelForAllModules = Day1().sumOfFuelForAllModules("""12
1969""")
        assertEquals(656, sumOfFuelForAllModules)
    }

    @Test
    fun fuelForModule_testInput() {
        assertEquals("12", 2, Day1().fuelForModule(12))
        assertEquals("14", 2, Day1().fuelForModule(14))
        assertEquals("1969", 654, Day1().fuelForModule(1969))
        assertEquals("100756", 33583, Day1().fuelForModule(100756))
    }
}
