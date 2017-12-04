package day3part2
import java.lang.Math.abs

data class Indices(private val i1: Int, private val i2: Int) {

    fun next(): Indices =
        if (abs(i1) > abs(i2)) {
            if (i1 > 0) Indices(i1, i2+1)
            else Indices(i1, i2-1)
        } else if (abs(i1) < abs(i2)) {
            if (i2 > 0) Indices(i1-1, i2)
            else Indices(i1+1, i2)
        } else {
            if (i1 > 0 && i2 > 0) Indices(i1-1, i2)
            else if (i1 > 0) Indices(i1+1, i2)
            else if (i2 > 0) Indices(i1, i2-1)
            else Indices(i1+1, i2)
        }

    fun neighbours() = listOf(
            Indices(i1-1, i2),
            Indices(i1-1, i2-1),
            Indices(i1-1, i2+1),
            Indices(i1+1, i2),
            Indices(i1+1, i2-1),
            Indices(i1+1, i2+1),
            Indices(i1, i2-1),
            Indices(i1, i2+1))
}

fun sumTo(stopSum: Int): Int {
    val sumMap = mutableMapOf<Indices, Int>()
    var currentIndices = Indices(0,0)
    var currentValue = 1
    sumMap[currentIndices] = currentValue
    while (currentValue <= stopSum) {
        currentIndices = currentIndices.next()
        currentValue = currentIndices.neighbours().map { sumMap[it] ?: 0 }.sumBy { it }
        sumMap[currentIndices] = currentValue
    }
    return currentValue
}

fun main(args: Array<String>) {

    do {
        val line = readLine()
        if (line != null) {

            val value = line.toInt()
            val sum = sumTo(value)
            println(sum)
        }
    } while (line != null)
}