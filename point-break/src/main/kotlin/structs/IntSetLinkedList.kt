package structs

import java.util.stream.IntStream

internal class IntSetLinkedListNonRecursion(
  maxElements: Int,
) : IntSetLinkedList(maxElements) {

  private var currentIndex = 0

  override fun add(i: Int): Boolean {
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
}


internal class IntSetLinkedListRecursion(
  maxElements: Int,
) : IntSetLinkedList(maxElements) {

  private var itemsInList = 0

  override fun add(i: Int): Boolean {
    val (node, added) = addNode(head, i)
    head = node
    return added
  }

  private fun addNode(node: Node<Int>?, i: Int): Pair<Node<Int>?, Boolean> {
    if (itemsInList >= maxElements) return node to false
    return if (node == null) {
      itemsInList++
      Node(i) to true
    } else if (node.value > i) {
      Node(i, node) to true
    } else if (
      node.value < i &&
      (node.next?.value?.compareTo(i) ?: -2 == 1 || node.next == null)
    ) {
      itemsInList++
      node.next = Node(i, node.next)
      node to true
    } else if (node.value < i && node.next?.value?.compareTo(i) ?: -2 == -1) {
      val (nextNode, added) = addNode(node.next, i)
      node.next = nextNode
      node to added
    } else {
      node to false
    }
  }

}

internal abstract class IntSetLinkedList(
  protected val maxElements: Int,
) {

  protected var head: Node<Int>? = null
  private var itemsInList = 0

  abstract fun add(i: Int): Boolean

  val size: Int
    get() = itemsInList + 1

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

