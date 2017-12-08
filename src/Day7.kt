package day7

data class Program(val name: String, val weight: Int, val children: List<String>) {
    var parent: Program? = null
    var childrenPrograms: List<Program> = listOf()
    var totalWeight: Int = 0

    fun computeTotalWeights() {
        totalWeight = weight
        for (child in childrenPrograms) {
            child.computeTotalWeights()
            totalWeight += child.totalWeight
        }
    }

    fun isBalanced(): Boolean {
        if (childrenPrograms.isEmpty()) {
            return true
        }
        val w = childrenPrograms[0].totalWeight
        return childrenPrograms.find { it.totalWeight != w } == null
    }

    fun findUnBalanced(): Program? {
        for (child in childrenPrograms) {
            val p = child.findUnBalanced()
            if (p != null) return p
        }
        if (!isBalanced()) {
            return this
        } else {
            return null
        }
    }

    fun balancedWeight(): Int? {
        if (childrenPrograms.isEmpty()) {
            return 0
        } else if (childrenPrograms.size <= 2) {
            return childrenPrograms[0].totalWeight
        } else {
            val t1 = childrenPrograms[0].totalWeight
            val t2 = childrenPrograms[1].totalWeight
            val t3 = childrenPrograms[2].totalWeight
            return if (t2 == t3) t2 else t1
        }
    }
}

data class ProgramStructure(val programsMap: Map<String, Program>, val root: Program)

fun readPrograms(): ProgramStructure {

    val regex = Regex("""(\w+)\s*\((\d+)\)\s*(->\s*(.*))?""")
    val programsMap = mutableMapOf<String, Program>()
    do {
        val line = readLine()
        if (line != null) {
            val result = regex.matchEntire(line)
            if (result == null) throw Exception("Invalid line $line.")
            val name = result.groupValues[1]
            val weight = result.groupValues[2].toInt()
            val tail = result.groupValues[4]
            val children = tail.split(',').map{it.trim()}.filter{!it.isEmpty()}
            val program = Program(name, weight, children)
            programsMap[program.name] = program
        }
    } while(line != null)

    for (program in programsMap.values) {
        program.childrenPrograms = program.children.map{programsMap[it]!!}
        for (child in program.childrenPrograms) {
            child.parent = program
        }
    }
    var root: Program? = programsMap.values.find { it.parent == null }
    return ProgramStructure(programsMap, root!!)
}

fun main(args: Array<String>) {
    val structure = readPrograms()
    println(structure.root.name)
    structure.root.computeTotalWeights()
    val unbalanced = structure.root.findUnBalanced()
    if (unbalanced != null) {
        val bw = unbalanced.balancedWeight()
        println("${unbalanced.name} (${unbalanced.totalWeight}) (${unbalanced.weight}) (${bw})")
        val bad = unbalanced.childrenPrograms.find{it.totalWeight != bw}!!
        val good = unbalanced.childrenPrograms.find{it.totalWeight == bw}!!

        println("${bad.name} (${bad.totalWeight}) (${bad.weight})")
        println("${good.name} (${good.totalWeight}) (${good.weight})")
        println(bad.weight+good.totalWeight-bad.totalWeight)
    }
}