package structs

import java.util.stream.Collectors
import java.util.stream.IntStream
import kotlin.random.Random

fun main() {
  val upperBounds = 10
  val intSet = IntSet(upperBounds)
  repeat(5) {
    val random = Random(69 + it)
    repeat(upperBounds * 2) {
      intSet.add(random.nextInt(upperBounds))
    }
    println(
      intSet.elements.mapToObj {
        it.toString()
      }.collect(Collectors.joining(", "))
    )
  }
}

internal data class Node<T>(
  val value: T,
  var next: Node<T>? = null
)

class IntSet(
  private val maxElements: Int,
) {

  private var head: Node<Int>? = null
  private var currentIndex = 0

  fun add(i: Int): Boolean {
    if (currentIndex >= maxElements) return false
    return when {
      head == null -> {
        currentIndex++
        head = Node(i)
        true
      }
      head?.value?.compareTo(i) ?: -2 == 1 -> {
        currentIndex++
        head = Node(i, head)
        true
      }
      else -> {
        var current = head
        while (current != null) {
          if (
            i < current.value ||
            (i > current.value &&
              (current.next == null ||
                current.next?.value?.compareTo(i) ?: -2 == 1))
          ) {
            current.next = Node(i, current.next)
            currentIndex++
            return true
          } else if (current.value == i) {
            return false
          } else {
            current = current.next
          }
        }
        false
      }
    }
  }

  val size: Int
    get() = currentIndex + 1

  val elements: IntStream
    get() = if (head == null) IntStream.empty()
    else {
      val bob = IntStream.builder()
      var current = head
      while (current != null) {
        bob.add(current.value)
        current = current.next
      }
      bob.build()
    }
}