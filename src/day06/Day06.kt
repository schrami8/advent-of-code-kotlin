package day06

import readInput

fun String.isUniqueCharacters() = toSet().size == length

// better to use https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/windowed.html
fun getStartOfMessageMarker(line: String, differentChars: Int): Int {
    var i = 0
    while (i + differentChars <= line.length) {
        if (line.substring(i, i + differentChars).isUniqueCharacters())
            return i + differentChars
        ++i
    }
    return -1
}

const val MARKER_4 = 4
const val MARKER_14 = 14


fun main() {
    fun part1(input: List<String>): Int {
        for (line in input) {
            val marker = getStartOfMessageMarker(line, MARKER_4)
            if (marker != -1)
                return marker
        }
        return -1
    }

    fun part2(input: List<String>): Int {
        for (line in input) {
            val marker = getStartOfMessageMarker(line, MARKER_14)
            if (marker != -1)
                return marker
        }
        return -1    }

    // test if implementation meets criteria from the description, like:
    var testInput = readInput("day06/Day06_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 23)
    testInput = readInput("day06/Day06_test2")
    check(part1(testInput) == 11)
    check(part2(testInput) == 26)

    val input = readInput("day06/Day06")
    println(part1(input))
    println(part2(input))
}
