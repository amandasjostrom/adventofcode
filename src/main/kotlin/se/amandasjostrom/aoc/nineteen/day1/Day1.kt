package se.amandasjostrom.aoc.nineteen.day1

class Day1 {

    fun runPartOne() {
        val sumOfFuelForAllModules = sumOfFuelForAllModules(input)
        println("Day 1, assignment 1: $sumOfFuelForAllModules")
    }

    fun sumOfFuelForAllModules(input: String) = input.split("\n")
            .map { x -> fuelForModule(Integer.valueOf(x)) }
            .sum()


    fun runPartTwo(): Int {
        return partTwo(input)
    }

    fun partTwo(input: String): Int {
        val sum = input.split("\n")
                .map { x -> Integer.valueOf(x) }
                .map { fuelForModule(it) }
                .map { addFuelForFuel(it) }
                .sum()
        println("Day 1, assignment 2: $sum")
        return sum
    }

    private fun addFuelForFuel(fuelInput: Int): Int {
        var sumTotal = fuelInput
        var newFuel = fuelForModule(fuelInput)
        if (newFuel > 0) {
            sumTotal += addFuelForFuel(newFuel)
        }
        return sumTotal
    }

    fun fuelForModule(module: Int) = module / 3 - 2

    companion object {
        private const val input: String = """102111
81442
104967
146349
97956
148336
55752
110518
136080
79361
101709
71796
72543
88058
87187
67778
75471
135632
101309
67060
87048
64775
52160
119184
55284
83289
94809
78017
117854
76469
94504
92372
133595
107269
57182
85578
83777
91167
86119
114263
60369
72562
69544
92699
135045
147230
83105
143488
56494
63019
112366
128119
58293
132542
93266
76032
111651
127490
107633
61260
120876
116254
101151
128722
111623
103470
53224
93922
87624
131864
82431
90465
148373
90119
123744
118713
143700
113581
140687
119516
149497
72281
108641
111605
148274
69326
116571
56540
87595
55068
120602
56557
125534
133162
51828
117883
94637
54577
135114
83866"""
    }
}
