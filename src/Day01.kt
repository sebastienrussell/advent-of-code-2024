import java.math.BigInteger

fun main() {
    fun parseLists(input: List<String>): Pair<List<BigInteger>, List<BigInteger>> {
        val leftList = mutableListOf<BigInteger>()
        val rightList = mutableListOf<BigInteger>()

        // Parsed Lines
        input.map { line ->
            line.split("   ").let {
                leftList.add(it[0].toBigInteger())
                rightList.add(it[1].toBigInteger())
            }
        }
        leftList.sort()
        rightList.sort()
        return Pair(leftList, rightList)
    }

    fun calculateDistanceBetweenLists(leftList: List<BigInteger>, rightList: List<BigInteger>): List<BigInteger> =
        leftList.mapIndexed { index, leftItem ->
            val rightItem = rightList[index]
            when (leftItem.compareTo(rightItem)) {
                -1 -> rightItem - leftItem
                1 -> leftItem - rightItem
                else -> BigInteger.ZERO
            }
        }

    fun calculateSimilaritiesBetweenLists(leftList: List<BigInteger>, rightList: List<BigInteger>): List<BigInteger> =
        leftList.mapIndexed { index, leftItem ->
            val rightItemsCount = rightList.count { it == leftItem }
            leftItem * rightItemsCount.toBigInteger()
        }

    fun part1(input: List<String>): BigInteger =
        parseLists(input).let { (leftList, rightList) ->
            calculateDistanceBetweenLists(leftList, rightList)
        }.sumOf { it }

    fun part2(input: List<String>): BigInteger =
        parseLists(input).let { (leftList, rightList) ->
            calculateSimilaritiesBetweenLists(leftList, rightList)
        }.sumOf { it }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == BigInteger.valueOf(11))
    check(part2(testInput) == BigInteger.valueOf(31))

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
