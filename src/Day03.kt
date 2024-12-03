fun main() {

    val mulRegExp = "mul\\(([0-9]{1,3},[0-9]{1,3})\\)".toRegex()

    fun parseInput(lines: List<String>): List<Pair<Int, Int>> =
        lines.map { line ->
            mulRegExp.findAll(line).map {
                val parsedLine = it.groups[1]?.value?.split(',') ?: listOf("", "")
                parsedLine[0].toInt() to parsedLine[1].toInt()
            }.toList()
        }.flatten().also { it.println() }


    fun part1(input: List<String>): Int =
        parseInput(input).sumOf { it.first * it.second }

    fun part2(input: List<String>): Int =
        parseInput(input).size


    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)
//    check(part2(testInput) == 4)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
