package day1

fun main(args: Array<String>) {
    val s = args[0]
    var value = 0
    for (i in s.indices) {
        val c1 = s[i]
        val c2 = if (i < s.length-1) s[i+1] else s[0]
        if (c1 == c2) {
            val dig = c1-'0'
            value += dig
        }
    }
    println(value)
}