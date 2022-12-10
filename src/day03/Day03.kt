package day03

import readInput

fun charToPriority(char: Char): Int =
  when (char) {
    in 'a'..'z' -> char.code - 96 // 'a' is 97 on the ascii table
    in 'A'..'Z' -> char.code - 64 + 26 // 'A' is 65 on the ascii table
    else -> 0
}

fun main() {

    fun part1(input: List<String>): Int {
        return input.map { it.chunked(it.length / 2) { str -> str.toSet() }}
            .flatMap { it.reduce { acc, next -> acc.intersect(next) } }
            .sumOf { charToPriority(it) }
    }

    fun part2(input: List<String>): Int {
        return input.chunked(3) { it.map { str -> str.toSet() } }
            .flatMap { it.reduce { acc, next -> acc.intersect(next) } }
            .sumOf { charToPriority(it) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day03/Day03_test")
    check(part1(testInput) == 157)

    val input = readInput("day03/Day03")
    println(part1(input))

    val testInput2 = readInput("day03/Day03_test")
    check(part2(testInput2) == 70)
    println(part2(input))
}