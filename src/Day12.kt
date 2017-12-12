package day12

class Node(val id: Int, val edges: List<Int>)

val inputPattern = Regex("""(\d+)\s*<->\s*(.+)""")

fun String.toNode(): Node {
    val result = inputPattern.matchEntire(this)
    if (result == null) throw Exception("Invalid input $this.")
    val id = result.groupValues[1].toInt()
    val edges = result.groupValues[2].split(',').map { it.trim().toInt() }
    return Node(id, edges)
}

fun readInput(): Array<Node> {
    val nodes = mutableListOf<Node>()
    do {
        val line = readLine()
        if (line != null) {
            nodes.add(line.toNode())
        }
    }
    while (line != null)
    return nodes.toTypedArray()
}

tailrec fun findAllEdges(program: Int, nodes: Array<Node>, edges: MutableSet<Int>) {
    edges.add(program)
    nodes[program].edges.forEach {
        if (!edges.contains(it)) {
            findAllEdges(it, nodes, edges)
        }
    }
}

fun main(args: Array<String>) {

    val nodes = readInput()

    //Part1
    val edges = mutableSetOf<Int>()
    findAllEdges(0, nodes, edges)
    println(edges.size)

    //Part2
    val groups = nodes.map { -1 }.toTypedArray()
    groups.forEachIndexed { program, group ->
        if (group < 0) {
            val edges = mutableSetOf<Int>()
            findAllEdges(program, nodes, edges)
            val min = edges.min()!!
            edges.forEach {edge->
                groups[edge] = min
            }
        }
    }
    println(groups.groupBy { it }.size)
}