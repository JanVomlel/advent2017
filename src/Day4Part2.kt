package day4part2

fun main(args: Array<String>) {
    var count = 0
    do {
        val line = readLine()
        if (line != null && !line.isEmpty()) {
            val words = line.split(' ').filter{!it.isEmpty()}.map {it.toList().sorted()}
            val wordsSet = words.toSet()
            if (words.size == wordsSet.size) count++
        }
    } while (line != null)
    println(count)
}