fun main() {

    fun parseInput(lines: List<String>) =
        lines.map { line -> line.split(" ").map { it.toInt() } }

    fun areLevelsSafe(levels: List<Int>, numberOfUnsafeLevel: Int = 0): Boolean {

        /**
         * 5 - 6 = -1 (Increasing)
         * 6 - 5 = 1 (Decreasing)
         */
        val isDecreasing = levels.first() - levels[1] > 0

        return levels.windowed(3).all { levelWithAdjacents ->
            val distanceFirst = levelWithAdjacents[0] - levelWithAdjacents[1]
            val distanceSecond = levelWithAdjacents[1] - levelWithAdjacents[2]

            val safe = if (isDecreasing)
                distanceFirst in 1..3 && distanceSecond in 1..3
            else
                distanceFirst in -3..-1 && distanceSecond in -3..-1

            if (!safe && numberOfUnsafeLevel > 0) {
                List(levels.size) { index ->
                    levels.toMutableList().apply {
                        removeAt(index)
                    }
                }.any {
                    areLevelsSafe(
                        levels = it,
                        numberOfUnsafeLevel = numberOfUnsafeLevel - 1
                    )
                }
            } else
                safe
        }
    }

    fun part1(input: List<String>): Int =
        parseInput(input).count { areLevelsSafe(levels = it) }

    fun part2(input: List<String>): Int =
        parseInput(input).count { areLevelsSafe(levels = it, numberOfUnsafeLevel = 1) }


    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
