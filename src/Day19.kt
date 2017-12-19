package day19

typealias Map = Array<Array<Char>>

fun readInput(): Map {
    val rows = mutableListOf<Array<Char>>()
    do {
        val line = readLine()
        if (line != null) {
            rows.add(line.toCharArray().toTypedArray())
        }
    }
    while (line != null)
    return rows.toTypedArray()
}

enum class Direction {NORTH, WEST, SOUTH, EAST;
    fun left() = when(this) {
        EAST -> NORTH
        WEST -> SOUTH
        NORTH -> WEST
        SOUTH -> EAST
    }
    fun right() = when(this) {
        EAST -> SOUTH
        WEST -> NORTH
        NORTH -> EAST
        SOUTH -> WEST
    }
}

data class Position(val x: Int, val y: Int) {
    fun next(direction: Direction) = when(direction) {
            Direction.EAST -> Position(x-1, y)
            Direction.WEST -> Position(x+1, y)
            Direction.NORTH -> Position(x, y-1)
            Direction.SOUTH -> Position(x, y+1)
        }
}

class Walker (val map: Map) {

    val lettersPath = mutableListOf<Char>()
    var pos = Position(map[0].indexOf('|'), 0)
    var direction = Direction.SOUTH
    var steps = 0

    fun isOnMap(pos: Position) = pos.y in map.indices && pos.x in map[pos.y].indices
    fun isOnPath(pos: Position) = isOnMap(pos) && map[pos.y][pos.x] != ' '

    fun next(): Boolean {

        if (!isOnMap(pos)) {
            return false
        }
        when (map[pos.y][pos.x]) {
            '-' -> {
                pos = pos.next(direction)
            }
            '|' -> {
                pos = pos.next(direction)
            }
            '+' -> {
                if (isOnPath(pos.next(direction))) {
                    pos = pos.next(direction)
                } else if (isOnPath(pos.next(direction.left()))) {
                    direction = direction.left()
                    pos = pos.next(direction)
                } else if (isOnPath(pos.next(direction.right()))) {
                    direction = direction.right()
                    pos = pos.next(direction)
                } else return false
            }
            ' ' -> {
                return false
            }
            else -> {
                lettersPath.add(map[pos.y][pos.x])
                pos = pos.next(direction)
            }
        }
        steps++
        return true
    }
}

fun main(args: Array<String>) {
    val map = readInput()
    val walker = Walker(map)
    while(walker.next()) {}
    println(walker.lettersPath.toCharArray())
    println(walker.steps)
}