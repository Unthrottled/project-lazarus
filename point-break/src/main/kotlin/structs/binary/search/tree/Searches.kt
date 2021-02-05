package structs.binary.search.tree

import data.Waifu
import data.WaifuList
import java.util.LinkedList

typealias WaifuNode = BinarySearchTreeNode<Waifu>

fun main() {
  val usableList = WaifuList
    .shuffled()

  val treeRoot = usableList
    .subList(1, usableList.size)
    .fold(
      BinarySearchTreeNode(usableList.first())
    ) { head, waifu ->
      insertWaifu(head, waifu)
      head
    }

  depthFirstSearchInline(treeRoot)
  println()
  depthFirstSearchRecursive(treeRoot)
}

fun depthFirstSearchRecursive(
  treeRoot: BinarySearchTreeNode<Waifu>?,
  visited: MutableSet<WaifuNode> = mutableSetOf()
) {
  if (treeRoot == null) return

  val leftNode = treeRoot.left
  if (leftNode != null && visited.contains(leftNode).not()) {
    depthFirstSearchRecursive(leftNode, visited)
  }

  val rightNode = treeRoot.right
  if (rightNode != null && visited.contains(rightNode).not()) {
    depthFirstSearchRecursive(rightNode, visited)
  }

  println(treeRoot)
  visited.add(treeRoot)
}

fun depthFirstSearchInline(treeRoot: WaifuNode) {
  val stack = LinkedList<WaifuNode>()
  stack.push(treeRoot)
  val visited = mutableSetOf<WaifuNode>()
  while (stack.isNotEmpty()) {
    val top = stack.peek()
    val leftNode = top.left
    val rightNode = top.right
    if (
      (leftNode == null && rightNode == null) ||
      ((leftNode == null || visited.contains(leftNode)) &&
        (rightNode == null || visited.contains(rightNode)))
    ) {
      println(stack.pop())
      visited.add(top)
    } else {
      if (rightNode != null &&
        visited.contains(rightNode).not()
      ) {
        stack.push(rightNode)
      }
      if (leftNode != null &&
        visited.contains(leftNode).not()
      ) {
        stack.push(leftNode)
      }
    }
  }
}

