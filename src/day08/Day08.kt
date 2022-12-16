package day08

import readInput

fun visibleFromLeft(line: ArrayList<Int>, index: Int): Boolean {
    for (i in 0 until index) {
        if (line[i] >= line[index])
            return false
    }
    return true
}

fun visibleFromRight(line: ArrayList<Int>, index: Int): Boolean {
    for (i in index + 1 until line.size) {
        if (line[i] >= line[index])
            return false
    }
    return true
}

fun visibleFromTop(column: ArrayList<ArrayList<Int>>, columnIndex: Int, index: Int): Boolean {
    for (i in 0 until index) {
        if (column[i][columnIndex] >= column[index][columnIndex])
            return false
    }
    return true
}

fun visibleFromBottom(column: ArrayList<ArrayList<Int>>, columnIndex: Int, index: Int): Boolean {
    for (i in index + 1 until column.size)   {
        if (column[i][columnIndex] >= column[index][columnIndex])
            return false
    }
    return true
}

fun visibleFromLeftCnt(line: ArrayList<Int>, index: Int): Int {
    var treesCnt = 0
    for (i in index - 1 downTo 0) {
        treesCnt += 1
        if (line[i] >= line[index])
            break
    }
    return treesCnt
}

fun visibleFromRightCnt(line: ArrayList<Int>, index: Int): Int {
    var treesCnt = 0
    for (i in index + 1 until line.size) {
        treesCnt += 1
        if (line[i] >= line[index])
            break
    }
    return treesCnt
}

fun visibleFromTopCnt(column: ArrayList<ArrayList<Int>>, columnIndex: Int, index: Int): Int {
    var treesCnt = 0
    for (i in index - 1 downTo  0) {
        treesCnt += 1
        if (column[i][columnIndex] >= column[index][columnIndex])
            break
    }
    return treesCnt
}

fun visibleFromBottomCnt(column: ArrayList<ArrayList<Int>>, columnIndex: Int, index: Int): Int {
    var treesCnt = 0
    for (i in index + 1 until column.size)   {
        treesCnt += 1
        if (column[i][columnIndex] >= column[index][columnIndex])
            break
    }
    return treesCnt
}

fun getTrees2D(input: List<String>): ArrayList<ArrayList<Int>> {
    val trees: ArrayList<ArrayList<Int>> = ArrayList(input.size)
    for (line in input.indices) {
        trees.add(ArrayList(input[line].length))
        for (i in input[line]) {
            trees[line].add(i.digitToInt())
        }
    }
    return trees
}

fun main() {

    fun part1(input: List<String>): Int {
        val trees: ArrayList<ArrayList<Int>> = getTrees2D(input)
        var visibleTrees = (2 * input.size + 2 * input[0].length) - 4 // border

        for (i in trees.indices) {
            if (i == 0 || i == trees[i].size - 1)
                continue
            for (j in trees[i].indices) {
                if (j == 0 || j == trees[i].size - 1)
                    continue

            if (visibleFromLeft(trees[i], j) || visibleFromRight(trees[i], j) ||
                visibleFromTop(trees, j, i) || visibleFromBottom(trees, j, i))
                ++visibleTrees
            }
        }
        return visibleTrees
    }

    fun part2(input: List<String>): Int {
        val trees: ArrayList<ArrayList<Int>> = getTrees2D(input)
        var mostVisibleTrees = 0
        for (i in trees.indices) {
            for (j in trees[i].indices) {
                var tmpVisible = visibleFromLeftCnt(trees[i], j)
                tmpVisible *= visibleFromRightCnt(trees[i], j)
                tmpVisible *= visibleFromTopCnt(trees, j, i)
                tmpVisible *= visibleFromBottomCnt(trees, j, i)

                if (mostVisibleTrees < tmpVisible)
                    mostVisibleTrees = tmpVisible
            }
        }

        return mostVisibleTrees
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day08/Day08_test")
    check(part1(testInput) == 21)

    val input = readInput("day08/Day08")
    println(part1(input))
    check(part2(testInput) == 8)
    println(part2(input))
}
