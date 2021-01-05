import sort.bitmap.jennysNumbers
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.time.Duration
import java.time.Instant

fun main() {
  val before = Instant.now()
  val outputFile = Paths.get(jennysNumbers.parent.toString(), "jennys-numbers-sorted.txt")

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

  println("Process took: ${Duration.between(before, Instant.now()).toMillis()}ms")
}