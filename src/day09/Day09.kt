package day09

import readInput
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

data class Location(var x: Int, var y: Int) {
    fun isTouching(location: Location): Boolean {
        return abs(x - location.x) <= 1 && abs(y - location.y) <= 1
    }
}

fun move(direction: String, location: Location) {
    when (direction) {
        "R" -> location.x++
        "L" -> location.x--
        "U" -> location.y++
        "D" -> location.y--
    }
}

fun trackMovement(head: Location, tail: Location) {
    if (!head.isTouching(tail)) {
        if (head.x > tail.x) tail.x++
        if (head.y > tail.y) tail.y++
        if (head.x < tail.x) tail.x--
        if (head.y < tail.y) tail.y--
    }
}

fun createMotions(input: List<String>): List<Pair<String, Int>> {
    return input.map { it ->
        it.split(" ").let { Pair(it[0], it[1].toInt()) }}
}

fun main() {

    fun part1(input: List<Pair<String, Int>>): Int {
        val head = Location(0, 0)
        val tail = Location(0, 0)

        val visited = mutableSetOf<Location>()
        input.forEach { pair ->
            repeat(pair.second) {
                move(pair.first, head)
                trackMovement(head, tail)

                visited.add(tail.copy())
            }
        }
        return visited.size
    }

    fun part2(input: List<Pair<String, Int>>): Int {
        val locations = mutableListOf<Location>()
        for (i in 0..9) {
            val location = Location(0, 0)
            locations.add(location)
        }

        val visited = mutableSetOf<Location>()

        input.forEach { pair ->
            repeat(pair.second) { index ->
                move(pair.first, locations[0]) // head
                for (i in 0..locations.size - 2)
                    trackMovement(locations[i], locations[i + 1])

                visited.add(locations.last().copy())
            }
        }
        return visited.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = createMotions(readInput("day09/Day09_test"))
    check(part1(testInput) == 13)

    val input = createMotions(readInput("day09/Day09"))
    println(part1(input))
    check(part2(testInput) == 1)
    println(part2(input))
}
