package day5

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
        instr[pos]++
        pos = pos+instr[pos]-1
        count++
    }
    println(count)
}