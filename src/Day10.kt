package day10

import java.util.*

val cyrcleSize = 256

class Node(val value: Int) {
    var prev: Node = this;
    var next: Node = this;
}

fun createCyrcle(): Node {
    var nodes = Array<Node>(cyrcleSize) {Node(it)}
    for (i in 0..cyrcleSize-1) {
        nodes[i].prev = if (i > 0) nodes[i-1] else nodes.last()
        nodes[i].next = if (i < nodes.size-1) nodes[i+1] else nodes.first()
    }
    return nodes.first()
}



fun reverse(cyrcle: Node, size: Int): Node {
    val prev = cyrcle.prev
    var current = cyrcle
    var last = current
    for (i in 1..size) {
        val next = current.next
        current.next = current.prev
        current.prev = next
        last = current
        current = next
    }
    if (size > 0 && size < cyrcleSize) {
        prev.next = last
        prev.next.prev = prev
        cyrcle.next = current
        cyrcle.next.prev = cyrcle
    }
    return last
}

fun move(cyrcle: Node, count: Int): Node {
    val by = if (count>=0) count % cyrcleSize  else count % cyrcleSize + cyrcleSize
    var moved = cyrcle
    for (i in 1..by) moved = moved.next
    return moved
}

fun main(args: Array<String>) {

    var cyrcle = createCyrcle()
    var skipSize = 0
    var totalMove = 0
    val scanner = Scanner(System.`in`).useDelimiter("""(,\s*|\s)""")
    while (scanner.hasNextInt()) {
        val length = scanner.nextInt()
        cyrcle = reverse(cyrcle, length)
        cyrcle = move(cyrcle, length+skipSize)
        totalMove += length+skipSize
        skipSize++
    }

    val first = move(cyrcle, -totalMove)

    println(first.value*first.next.value)

}