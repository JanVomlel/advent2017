package day6

fun getBlocks(line: String) = line
        .split(' ', '\t')
        .filter{!it.isEmpty()}
        .map { it.toInt() }

fun findMaxIndex(blocks: Array<Int>): Int {
    var index = 0
    for ((i, v) in blocks.withIndex()) {
        if (v > blocks[index]) {
            index = i
        }
    }
    return index
}

fun main(args: Array<String>) {
    do {
        val line = readLine();
        if (line != null) {
            val blocks = getBlocks(line).toTypedArray()
            var count = 0
            val history = mutableMapOf<List<Int>, Int>()
            var blocksList = blocks.toList()
            while (!history.containsKey(blocksList)) {
                history[blocksList] = count
                val max = findMaxIndex(blocks)
                val size = blocks[max]
                blocks[max] = 0
                for (i in 1..size) {
                    val index = (max+i) % blocks.size
                    blocks[index]++
                }
                blocksList = blocks.toList()
                count++
            }
            println (count-history[blocksList]!!)
            println (count)
        }
    } while(line != null)

}