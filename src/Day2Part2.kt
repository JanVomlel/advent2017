package day2part2

fun main(args: Array<String>) {
    var checksum = 0
    do {
        val line = readLine()
        if (line != null && !line.isEmpty()) {
            val numbers = line
                    .split(' ', '\t')
                    .filter{ !it.isEmpty() }
                    .map { it.toInt() }
            for (i in numbers.indices) {
                val n1 = numbers[i]
                for (j in numbers.indices.filter { it > i }) {
                    val n2 = numbers[j]
                    val div = if (n1 > n2) n1 / n2 else n2 / n1
                    val mod = if (n1 > n2) n1 % n2 else n2 % n1
                    if (mod == 0) {
                        checksum += div
                    }
                }
            }
        }
    } while (line != null)
    println(checksum)
}