package day02

import readInput


fun main() {

    val precalculated = hashMapOf(
        "A X" to 4,
        "A Y" to 8,
        "A Z" to 3,
        "B X" to 1,
        "B Y" to 5,
        "B Z" to 9,
        "C X" to 7,
        "C Y" to 2,
        "C Z" to 6
    )

    fun part1(input: List<String>): Int {
        return input.sumOf {
            precalculated.get(it)!!
        }
    }

    val precalculated2 = hashMapOf(
        "A X" to 3,
        "A Y" to 4,
        "A Z" to 8,
        "B X" to 1,
        "B Y" to 5,
        "B Z" to 9,
        "C X" to 2,
        "C Y" to 6,
        "C Z" to 7
    )
    fun part2(input: List<String>): Int {
        return input.sumOf {
            precalculated2.get(it)!!
        }
    }

    // test if implementation meets criteria from the description, like:
    //val testInput = readInput("Day01_test")
    //check(part1(testInput) == 1)

    val input = readInput("day02/Day02")
    println(part1(input))
    println(part2(input))
}