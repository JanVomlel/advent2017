package day3
import java.lang.Math.abs

fun main(args: Array<String>) {

    do {
        //Read value
        val line = readLine()

        if (line != null) {

            val value = line.toInt()

            val steps = if (value == 1) 0 else {
                //Find the square size
                var n = 1
                while (n*n < value) n += 2

                //find order in the largest square
                val m = value - (n-2)*(n-2)

                //position on the side
                val l = m % (n-1)

                //Steps on the side+steps to the center
                abs(l - (n-1)/2)+(n-1)/2
            }
            println(steps)
        }


    } while (line != null)


}