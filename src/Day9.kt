package day9

import java.io.InputStreamReader

fun main(args: Array<String>) {
    val reader = InputStreamReader(System.`in`)
    var score = 0
    var trashSize = 0
    var groupScore = 1
    var trash = false
    var special = false
    do {
        val code = reader.read()
        if (code >= 0) {
            val ch = code.toChar()
            val removeSpecial = special
            var countTrash = trash
            when (ch) {
                '{' -> {
                    if (!trash) {
                        score += groupScore
                        groupScore++
                    }
                }
                '}' -> {
                    if (!trash) {
                        groupScore--
                    }
                }
                '<' -> {
                    if (!trash) {
                        trash = true
                    }
                }
                '>' -> {
                    if (trash && !special) {
                        trash = false
                        countTrash = false
                    }
                }
                '!' -> {
                    if (trash && !special) {
                        special = true
                    }
                }
            }
            if (countTrash && !special) trashSize++
            if (removeSpecial) special = false
        }
    } while (code >= 0)
    println(score)
    println(trashSize)
}