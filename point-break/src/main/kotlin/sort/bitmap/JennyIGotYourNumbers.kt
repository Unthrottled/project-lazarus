package sort.bitmap

import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import kotlin.random.Random

val TEST_ASSET_DIRECTORY = Paths.get("testAssets").toAbsolutePath()

internal val jennysNumbers = Paths.get(TEST_ASSET_DIRECTORY.toString(), "sort", "bitmap", "jennys-numbers.txt")

fun main() {
  val parentDirectories = jennysNumbers.parent
  if (Files.exists(parentDirectories).not()) {
    Files.createDirectories(parentDirectories)
  }

  val ranbo = Random(System.currentTimeMillis())
  val n = 10_000_000
  val outputs = (0 until n).map { ranbo.nextInt(1_000_000, n) }
    .distinct().shuffled(ranbo)

  Files.newBufferedWriter(
    jennysNumbers,
    StandardOpenOption.TRUNCATE_EXISTING,
    StandardOpenOption.CREATE
  ).use { writer ->
    outputs.forEach {
      writer.append("$it\n")
    }
  }
}

