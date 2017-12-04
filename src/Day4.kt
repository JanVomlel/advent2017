package day4

fun main(args: Array<String>) {
    var count = 0
    do {
        val line = readLine()
        if (line != null) {
           val words = line.split(' ').filter{!it.isEmpty()}
            val wordsSet = words.toSet()
            if (words.size != wordsSet.size) count++
        }
    } while (line != null)
    println(count)
}