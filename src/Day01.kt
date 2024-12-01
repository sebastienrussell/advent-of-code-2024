import kotlin.math.absoluteValue

fun main() {
    fun parseLists(input: List<String>): Pair<List<Int>, List<Int>> {
        val leftList = mutableListOf<Int>()
        val rightList = mutableListOf<Int>()

        // Parsed Lines
        input.map { line ->
            line.split("   ").let {
                leftList.add(it[0].toInt())
                rightList.add(it[1].toInt())
            }
        }
        leftList.sort()
        rightList.sort()
        return Pair(leftList, rightList)
    }

    fun calculateSimilaritiesBetweenLists(leftList: List<Int>, rightList: List<Int>): List<Int> =
        leftList.map { leftItem ->
            val rightItemsCount = rightList.count { it == leftItem }
            leftItem * rightItemsCount
        }

    fun part1(input: List<String>): Int =
        parseLists(input).let { (leftList, rightList) -> leftList.zip(rightList) }
            .sumOf { (x, y) -> (x - y).absoluteValue }

    fun part2(input: List<String>): Int =
        parseLists(input).let { (leftList, rightList) ->
            calculateSimilaritiesBetweenLists(leftList, rightList)
        }.sumOf { it }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
