package day20

data class Vector(val x: Int, val y: Int, val z: Int) {
    fun size(): Int = Math.abs(x)+Math.abs(y)+Math.abs(z)
    fun sizeInDirection(dir: Vector): Int = dirSizeHelper(dir.x, x)+dirSizeHelper(dir.y, y)+dirSizeHelper(dir.z, z)
    private fun dirSizeHelper(a: Int, b: Int) = when {
        a == 0 -> Math.abs(b)
        a > 0 -> b
        else -> -b
    }
}

val vectorPattern = Regex("""([+-]?\d+),\s*([+-]?\d+),\s*([+-]?\d+)""")
fun String.toVector(): Vector {
    val result = vectorPattern.matchEntire(this)!!
    return Vector(
            result.groups[1]!!.value.toInt(),
            result.groups[2]!!.value.toInt(),
            result.groups[3]!!.value.toInt()
    )
}

data class Particle(val p: Vector, val v: Vector, val a: Vector) {
    fun toMeasure() = ParticleMeasure(a.size(), v.sizeInDirection(a), p.sizeInDirection(a))
}

val particlePattern = Regex("""p=<([^>]*)>,\s*v=<([^>]*)>,\s*a=<([^>]*)>""")
fun String.toParticle(): Particle {
    val result = particlePattern.matchEntire(this)!!
    return Particle(
    result.groups[1]!!.value.toVector(),
    result.groups[2]!!.value.toVector(),
    result.groups[3]!!.value.toVector()
    )
}

fun readInput(): List<Particle> {
    val particles = mutableListOf<Particle>()
    do {
        val line = readLine()
        if (line != null && line != "") {
            particles.add(line.toParticle())
        }
    }
    while (line != null)
    return particles
}

data class ParticleMeasure(val first: Int, val second: Int, val third: Int): Comparable<ParticleMeasure> {
    override fun compareTo(other: ParticleMeasure): Int {
        if (first != other.first) return first - other.first
        else if (second != other.second) return second-other.second
        else return third-other.third
    }
}

fun main(args: Array<String>) {
    val particles = readInput()
    val min = particles.minBy{it.toMeasure()}!!
    val minParticles = particles
            .withIndex()
            .filter{it.value.toMeasure() == min.toMeasure()}
            .map{it.index}
    println(minParticles)
}