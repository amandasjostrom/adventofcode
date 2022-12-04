package se.amandasjostrom.aoc.twentytwo

class Util

fun input(fileName: String): String {
    val completeFileName = "/twentytwo/$fileName"
    return Util::class.java.getResource(completeFileName)?.readText()
        ?: throw IllegalArgumentException("file not found: $completeFileName")
}
