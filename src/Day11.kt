package day11

fun main(args: Array<String>) {
    //Part1
    println(readLine()!!
            .split(',')
            .map { when (it) {"n"->Pair(2, 0) "nw"->Pair(1, 1) "sw"->Pair(-1, 1) "s"->Pair(-2, 0) "se"->Pair(-1, -1) "ne"->Pair(1, -1) else->Pair(0, 0)}  }
            .fold(Pair(0, 0)) { a, b -> Pair(a.first+b.first, a.second+b.second) }
            .let {Pair(Math.abs(it.first), Math.abs(it.second))}
            .let {if (it.first <= it.second) it.second else it.second+(it.first-it.second)/2}
    )
    //Part2
    println(readLine()!!
            .split(',')
            .map { when (it) {"n"->Pair(2, 0) "nw"->Pair(1, 1) "sw"->Pair(-1, 1) "s"->Pair(-2, 0) "se"->Pair(-1, -1) "ne"->Pair(1, -1) else->Pair(0, 0)}  }
            .fold(listOf(Pair(0, 0))) { a, b -> a+Pair(a.last().first+b.first, a.last().second+b.second) }
            .map {Pair(Math.abs(it.first), Math.abs(it.second))}
            .map {if (it.first <= it.second) it.second else it.second+(it.first-it.second)/2}
            .max()
    )
}