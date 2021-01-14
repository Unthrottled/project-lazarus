package structs

import java.util.stream.Collectors
import kotlin.random.Random

fun main() {
  val upperBounds = 10
  repeat(5) {
    val intSetNonRecursion = IntSetLinkedListNonRecursion(upperBounds)
    val intSetRecursion = IntSetLinkedListRecursion(upperBounds)
    val intSetBSTRecursion = IntSetBSTRecursion(upperBounds)
    val random = Random(69 + it)
    repeat(upperBounds * 2) {
      val i = random.nextInt(upperBounds)
      intSetRecursion.add(i)
      intSetNonRecursion.add(i)
      intSetBSTRecursion.add(i)
    }
    printOutput(intSetNonRecursion)
    printOutput(intSetRecursion)
    printOutput(intSetBSTRecursion)
    println()
  }
}

private fun printOutput(intSetNonRecursion: IntSetLinkedList) {
  println(
    intSetNonRecursion.elements.mapToObj {
      it.toString()
    }.collect(Collectors.joining(", "))
  )
}

private fun printOutput(intSetNonRecursion: IntSetBSTRecursion) {
  println(
    intSetNonRecursion.elements.mapToObj {
      it.toString()
    }.collect(Collectors.joining(", "))
  )
}

internal data class Node<T>(
  val value: T,
  var next: Node<T>? = null
)


