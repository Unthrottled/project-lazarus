package structs

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
      traverseTree(head, bob).build()
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
}