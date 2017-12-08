package day8

enum class Operation {
    INC, DEC;

    operator fun invoke(value1: Int, value2: Int) = when(this) {
        INC -> value1+value2
        DEC -> value1-value2
    }
}

fun String.toOperation() = when (this) {
    "inc" -> Operation.INC
    "dec" -> Operation.DEC
    else -> throw Exception("Ibvalid operation $this.")
}

enum class ComparsionOperator {
    EQ, NEQ, LS, LSEQ, GR, GREQ;

    operator fun invoke(value1: Int, value2: Int) = when(this) {
        EQ -> value1 == value2
        NEQ -> value1 != value2
        LS -> value1 < value2
        LSEQ -> value1 <= value2
        GR -> value1 > value2
        GREQ -> value1 >= value2
    }
}

fun String.toComparsionOperator() = when(this) {
    "==" -> ComparsionOperator.EQ
    "!=" -> ComparsionOperator.NEQ
    "<" -> ComparsionOperator.LS
    "<=" -> ComparsionOperator.LSEQ
    ">" -> ComparsionOperator.GR
    ">=" -> ComparsionOperator.GREQ
    else -> throw Exception("Ibvalid comparsion operator $this.")
}

data class Condition(val registry: String, val operator: ComparsionOperator, val value: Int)

val conditionRegex = Regex("""(\w+)\s+(\S+)\s+(\-?[\d]+)""")

fun String.toCondition(): Condition {
    val regexResult = conditionRegex.matchEntire(this)
    if (regexResult == null) throw Exception("Invalid condition $this.")
    return Condition(
            regexResult.groupValues[1],
            regexResult.groupValues[2].toComparsionOperator(),
            regexResult.groupValues[3].toInt())
}

data class Instruction(val registry: String, val operation: Operation, val value: Int, val condition: Condition)

val instructionTegex = Regex("""(\w+)\s+(\w+)\s+(\-?[\d]+)\s+if\s+(.*)""")

fun String.toInstruction(): Instruction {
    val regexResult = instructionTegex.matchEntire(this)
    if (regexResult == null) throw Exception("Invalid instruction $this.")
    return Instruction(
            regexResult.groupValues[1],
            regexResult.groupValues[2].toOperation(),
            regexResult.groupValues[3].toInt(),
            regexResult.groupValues[4].toCondition())
}

fun readInstructions(): List<Instruction> {
    val instructions = mutableListOf<Instruction>()
    do {
        val line = readLine()
        if (line != null) {
            instructions.add(line.toInstruction())
        }
    } while(line != null)
    return instructions
}

fun main(args: Array<String>) {
    val instructions = readInstructions()
    val registries = mutableMapOf<String, Int>()
    var fullMax = registries.values.max()?:0
    for (instruction in instructions) {
        val cond = instruction.condition
        if (cond.operator(registries[cond.registry]?:0, cond.value)) {
            registries[instruction.registry] = instruction.operation(
                    registries[instruction.registry]?:0,
                    instruction.value)
            fullMax = (registries.values+listOf(fullMax)).max()!!
        }
    }
    println(registries.values.max()?:0)
    println(fullMax)
}