fun main() {

    fun parseInput(lines: List<String>): List<String> = lines

    fun part1(input: List<String>): Int =
        parseInput(input).size

    fun part2(input: List<String>): Int =
        parseInput(input).size


    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
//    check(part2(testInput) == 4)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
