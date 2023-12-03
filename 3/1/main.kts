import java.io.File

val input = File("input.txt").readLines()

var sumOfAll = 0

File("input.txt").readLines().forEachIndexed { lineIndex, line ->
  Regex("\\d+").findAll(line).forEach {
    val (range, value) = it.range to it.value.toInt()

    var hasSymbol = false

    if(lineIndex > 0 && Regex("[^\\d.]").findAll(input[lineIndex-1]).map { it.range.first }.toList().any { ((range.first - 1)..(range.last + 1)).contains(it)})
      hasSymbol = true

    if (Regex("[^\\d.]").findAll(line).map { it.range.first }.toList().any { ((range.first - 1)..(range.last + 1)).contains(it)})
      hasSymbol = true

    if(input.size > lineIndex+1 && Regex("[^\\d.]").findAll(input[lineIndex+1]).map { it.range.first }.toList().any { ((range.first - 1)..(range.last + 1)).contains(it)})
      hasSymbol = true

    if (hasSymbol) {
      sumOfAll += value
    }
  }
}

println(sumOfAll)