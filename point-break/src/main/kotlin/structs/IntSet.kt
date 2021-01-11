package structs

import java.util.stream.IntStream
import kotlin.random.Random

fun main() {
  val upperBounds = 10
  val intSet = IntSet(upperBounds)
  val random = Random(69)
  repeat(upperBounds * 2) {
    intSet.add(random.nextInt(upperBounds))
  }

  intSet.elements.forEach { println(it) }
}

class IntSet(
  private val maxElements: Int,
) {

  private val items = Array<Int?>(maxElements) { null }
  private var currentIndex = 0

  fun add(i: Int): Boolean {
    val needsToAdd = (0..currentIndex).none { items[it] == i }
    if (needsToAdd && currentIndex < maxElements) {
      items[currentIndex++] = i
    }
    return needsToAdd
  }

  val size: Int
    get() = currentIndex + 1

  val elements: IntStream
    get() = IntStream.range(0, currentIndex)
      .map { items[it]!! }

}