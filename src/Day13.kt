package day13

class Layer(val depth: Int, val range: Int)

val inputPattern = Regex("""(\d+):\s*(\d+)""")

fun String.toLayer(): Layer {
    val result = inputPattern.matchEntire(this)
    if (result == null) throw Exception("Invalid layer $this.")
    val depth = result.groupValues[1].toInt()
    val range = result.groupValues[2].toInt()
    return Layer(depth, range)
}

fun readInput(): List<Layer> {
    val layers = mutableListOf<Layer>()
    do {
        val line = readLine()
        if (line != null) {
            layers.add(line.toLayer())
        }
    }
    while (line != null)
    return layers
}


fun main(args: Array<String>) {
    val layers = readInput()
    //Part1
    println(layers.sumBy { if (it.depth % (2*it.range-2) == 0) it.depth*it.range else 0 })
    //Part2
    var delay = 0
    while (layers.find { (it.depth+delay) % (2*it.range-2) == 0 } != null) delay++
    println(delay)
}