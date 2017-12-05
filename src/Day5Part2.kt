package day5part2

fun readInstr(): Array<Int> {
    val instr = mutableListOf<Int>()
    do {
        val line = readLine()
        if (line != null) instr.add(line.toInt())
    } while (line != null)
    return instr.toTypedArray()
}

fun main(args: Array<String>) {
    val instr = readInstr()
    var count = 0
    var pos = 0
    while (pos >= 0 && pos < instr.size) {
        val offset = instr[pos]
        if (offset >= 3) instr[pos]--
        else instr[pos]++
        pos += offset
        count++
    }
    println(count)
}