package day2

fun main(args: Array<String>) {
    var checksum = 0
    do {
        val line = readLine()
        if (line != null && !line.isEmpty()) {
            val numbers = line
                    .splitToSequence(' ', '\t')
                    .filter{ !it.isEmpty() }
                    .map { it.toInt() }
            var largest = numbers.first()
            var smallest = numbers.first()
            for (number in numbers) {
                if (number < smallest) smallest = number
                if (number > largest) largest = number
            }
            checksum += largest-smallest
        }
    } while (line != null)
    println(checksum)
}