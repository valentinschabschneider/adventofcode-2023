import java.io.File
import kotlin.math.max
import kotlin.math.min

val input = File("input.txt").readLines()

var gears = mutableMapOf<String, List<Int>>()

File("input.txt").readLines().forEachIndexed { lineIndex, line ->
  Regex("\\d+").findAll(line).forEach {
    val (range, value) = it.range to it.value.toInt()

    if (lineIndex > 0)
      Regex("\\*").findAll(input[lineIndex-1].substring((max(range.first - 1, 0))..(min(range.last + 1, line.length-1)))).map { it.range.first }.toList().forEach {
        gears.compute("${lineIndex-1}-${max(range.first - 1, 0) - 1 +it}") { _, gearNumbers ->
          gearNumbers?.plus(value) ?: listOf(value)
        }
      }

    Regex("\\*").findAll(line.substring((max(range.first - 1, 0))..(min(range.last + 1, line.length-1)))).map { it.range.first }.toList().forEach {
      gears.compute("${lineIndex}-${max(range.first - 1, 0) - 1 +it}") { _, gearNumbers ->
        gearNumbers?.plus(value) ?: listOf(value)
      }
    }

    if (lineIndex < input.size - 1)
      Regex("\\*").findAll(input[lineIndex+1].substring((max(range.first - 1, 0))..(min(range.last + 1, line.length-1)))).map { it.range.first }.toList().forEach {
        gears.compute("${lineIndex+1}-${max(range.first - 1, 0) - 1 +it}") { _, gearNumbers ->
          gearNumbers?.plus(value) ?: listOf(value)
        }
      }
  }
}

println(gears.values.filter { it.size == 2 }.sumOf { it.reduce(Int::times) })
