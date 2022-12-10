fun <E> List<E>.splitBy(predicate: (E) -> Boolean): List<List<E>> =
    this.fold(mutableListOf(mutableListOf<E>())) { acc, element ->
        if (predicate.invoke(element)) {
            acc += mutableListOf<E>()
        } else {
            acc.last() += element
        }
        acc
    }

fun main() {
    fun part1(input: List<String>): Int {
        var mostCalories = 0
        var tmpCalories = 0
        input.forEach {
            if (it.isNotEmpty()) {
                tmpCalories += it.toInt()
                if (tmpCalories > mostCalories)
                    mostCalories = tmpCalories
            }
            else
                tmpCalories = 0
        }

        return mostCalories
    }

    fun part2(input: List<String>): Int {
        val elves = mutableListOf<Int>()
        var tmpCalories = 0
        input.forEach {
            if (it.isNotEmpty())
                tmpCalories += it.toInt()
            else {
                elves.add(tmpCalories)
                tmpCalories = 0
            }
        }
        println(elves.sortedDescending())
        return elves.sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    //val testInput = readInput("Day01_test")
    //check(part1(testInput) == 1)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
