package day05

import readInput
import java.util.LinkedList

fun makeMovementsPart1(stacks: Array<LinkedList<Char>>, count: Int, from: Int, to: Int) {
    for (i in 1..count) {
        val toMove = stacks[from].first
        stacks[from].removeFirst()
        stacks[to].add(0, toMove)
    }
}

fun makeMovementsPart2(stacks: Array<LinkedList<Char>>, count: Int, from: Int, to: Int) {
        val toMove = stacks[from].take(count)
        stacks[to].addAll(0, toMove)
        for (i in 1..count)
            stacks[from].removeFirst()
}

fun collectStacks(stacks: Array<LinkedList<Char>>, line: String) {
    for (i in line.indices) { // fill default stacks
        if (line[i].isLetter()) {
            val index = i / 4 // get index of stack
            stacks[index].addLast(line[i])
        }
    }
}

fun getTopOfEachStack(stacks: Array<LinkedList<Char>>): String {
    var topOfEachStack = ""
    for (stack in stacks) {
        if (stack.isNotEmpty())
            topOfEachStack += stack.first ?: ""
    }
    return topOfEachStack
}

fun main() {

    fun part1(input: List<String>): String {
        val stacks = Array(input.first().length / 3) { _ -> LinkedList<Char>() }
        for (line in input) {
            if (!line.startsWith("move")) {
                collectStacks(stacks, line)
            } else {
                val movements = line.split(" ")
                makeMovementsPart1(
                    stacks,
                    movements[1].toInt(),
                    movements[3].toInt() - 1,
                    movements[5].toInt() - 1)
            }
        }
        
        return getTopOfEachStack(stacks)
    }

    fun part2(input: List<String>): String {
        val stacks = Array(input.first().length / 3) { _ -> LinkedList<Char>() }
        for (line in input) {
            if (!line.startsWith("move")) {
                collectStacks(stacks, line)
            } else {
                val movements = line.split(" ")
                makeMovementsPart2(
                    stacks,
                    movements[1].toInt(),
                    movements[3].toInt() - 1,
                    movements[5].toInt() - 1)
            }
        }

        return getTopOfEachStack(stacks)
    }

    // test if implementation meets criteria from the description, like:
    var testInput = readInput("day05/Day05_test")
    check(part1(testInput) == "CMZ")

    val input = readInput("day05/Day05")
    println(part1(input))

    testInput = readInput("day05/Day05_test")
    check(part2(testInput) == "MCD")
    println(part2(input))
}