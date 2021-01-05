import sort.bitmap.jennysNumbers
import tools.timed
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

fun main() {
  val outputFile = Paths.get(jennysNumbers.parent.toString(), "jennys-numbers-sorted.txt")

  timed {
    bitVectorSort(outputFile)
  }

  timed {
    systemSort(outputFile)
  }
}

fun systemSort(outputFile: Path) {
  Files.newBufferedWriter(
    outputFile,
    StandardOpenOption.TRUNCATE_EXISTING,
    StandardOpenOption.CREATE
  ).use { writer ->
    Files.newBufferedReader(jennysNumbers)
      .use { reader ->
        reader.lines().map { it.toInt() }.sorted().forEach { i ->
          writer.write(i.toString())
          writer.newLine()
        }
      }
  }
}

private fun bitVectorSort(outputFile: Path) {
  val domainAsBitVector = Array(10_000_000) { 0 }

  Files.newBufferedReader(jennysNumbers)
    .use {
      it.lines().forEach { i ->
        domainAsBitVector[i.toInt()] = 1
      }
    }

  Files.newBufferedWriter(
    outputFile,
    StandardOpenOption.TRUNCATE_EXISTING,
    StandardOpenOption.CREATE
  ).use { writer ->
    domainAsBitVector.forEachIndexed { index, i ->
      if (i == 1) {
        writer.write(index.toString())
        writer.newLine()
      }
    }
  }
}