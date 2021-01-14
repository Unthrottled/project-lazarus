package structs

import java.util.LinkedList
import java.util.stream.IntStream

internal data class BinaryTreeNode<T>(
  val value: T,
  var left: BinaryTreeNode<T>? = null,
  var right: BinaryTreeNode<T>? = null,
)

internal class IntSetBSTRecursion(maxElements: Int) : IntSetBinarySearchTree(maxElements) {

  override fun add(i: Int): Boolean {
    if (itemsInList >= maxElements) return false
    val (node, added) = addNode(head, i)
    head = node
    return added
  }

  private fun addNode(
    node: BinaryTreeNode<Int>?,
    i: Int
  ): Pair<BinaryTreeNode<Int>, Boolean> {
    return when {
      node == null -> {
        itemsInList++
        BinaryTreeNode(i) to true
      }
      node.value > i -> {
        val (leftNode, added) = addNode(node.left, i)
        node.left = leftNode
        node to added
      }
      node.value < i -> {
        val (rightNode, added) = addNode(node.right, i)
        node.right = rightNode
        node to added
      }
      else -> {
        node to false
      }
    }
  }

}

internal class IntSetBSTInline(maxElements: Int) : IntSetBinarySearchTree(maxElements) {

  override fun add(i: Int): Boolean {
    if (itemsInList >= maxElements) return false

    if (head == null) {
      itemsInList++
      head = BinaryTreeNode(i)
      return true
    }

    val queue = LinkedList<BinaryTreeNode<Int>?>()
    queue.push(head)
    while (queue.isNotEmpty()) {
      val current = queue.pop() ?: continue
      if (i > current.value) {
        if (current.right == null) {
          itemsInList++
          current.right = BinaryTreeNode(i)
          return true
        } else {
          queue.push(current.right)
        }
      } else if (i < current.value) {
        if (current.left == null) {
          itemsInList++
          current.left = BinaryTreeNode(i)
          return true
        } else {
          queue.push(current.left)
        }
      } else {
        return false
      }
    }

    return false
  }
}

internal abstract class IntSetBinarySearchTree(
  protected val maxElements: Int
) {
  protected var head: BinaryTreeNode<Int>? = null
  protected var itemsInList = 0

  abstract fun add(i: Int): Boolean

  val size: Int
    get() = itemsInList + 1

  val elements: IntStream
    get() = if (head == null) IntStream.empty()
    else {
      val bob = IntStream.builder()
      traverseTreeInline(bob).build()
    }

  private fun traverseTree(
    head: BinaryTreeNode<Int>?,
    bob: IntStream.Builder,
  ): IntStream.Builder {
    return if (head == null) {
      bob
    } else if (head.left == null && head.right == null) {
      bob.add(head.value)
    } else {
      val newBob = traverseTree(head.left, bob)
      newBob.add(head.value)
      traverseTree(head.right, bob)
    }
  }

  private fun traverseTreeInline(bob: IntStream.Builder): IntStream.Builder {
    val queue = LinkedList<BinaryTreeNode<Int>?>()
    queue.push(head)
    while (queue.isNotEmpty()) {
      val current = queue.pop() ?: continue
      if (current.left == null && current.right == null) {
        bob.add(current.value)
        queue.peekFirst() ?: continue
        bob.add(queue.pop()?.value ?: continue)
      } else if (current.left == null) {
        bob.add(current.value)
        queue.push(current.right)
      } else if (current.right == null) {
        bob.add(current.value)
        queue.push(current.left)
      } else {
        queue.push(current.right)
        queue.push(current)
        queue.push(current.left)
      }
    }
    return bob
  }
}