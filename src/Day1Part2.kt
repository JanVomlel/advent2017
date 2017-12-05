package day1part2

fun main(args: Array<String>) {
    val s = readLine()!!
    val halfSize = s.length/2
    var value = 0
    for (i in s.indices) {
        val c1 = s[i]
        val c2 = if (i < halfSize) s[i+halfSize] else s[i-halfSize]
        if (c1 == c2) {
            val dig = c1-'0'
            value += dig
        }
    }
    println(value)
}