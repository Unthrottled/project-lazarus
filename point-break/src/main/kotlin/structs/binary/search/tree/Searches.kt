package structs.binary.search.tree

import data.Waifu
import data.WaifuList
import java.util.LinkedList

typealias WaifuNode = BinarySearchTreeNode<Waifu>

fun main() {
  val usableList = WaifuList
    .shuffled()
    .map { BinarySearchTreeNode(it) }

  val treeRoot = usableList
    .subList(1, usableList.size)
    .fold(
      usableList.first()
    ) { head, waifu ->
      insertNode(head, waifu)
      head
    }

  depthFirstTraversalInline(treeRoot)
  println()
  depthFirstTraversalRecursive(treeRoot)
  println()

  val nodeWithSlot = usableList.first { it.left == null || it.right == null }
  println("Attaching $nodeWithSlot to root node $treeRoot")
  if (nodeWithSlot.right == null) {
    nodeWithSlot.right = treeRoot
  } else {
    nodeWithSlot.left = treeRoot
  }

  val cycle = detectCycle(treeRoot)
  println("Found Cycle on node $cycle")
}

fun detectCycle(
  treeRoot: BinarySearchTreeNode<Waifu>?,
  visited: MutableSet<WaifuNode> = mutableSetOf()
): WaifuNode? {
  if (treeRoot == null) return null

  visited.add(treeRoot)

  val right = treeRoot.right
  val left = treeRoot.left
  if(right != null && visited.contains(right)) {
    return treeRoot
  } else if(left != null && visited.contains(left)) {
    return treeRoot
  }

  return detectCycle(right, visited) ?: detectCycle(left, visited)
}

fun depthFirstTraversalRecursive(
  treeRoot: BinarySearchTreeNode<Waifu>?,
  visited: MutableSet<WaifuNode> = mutableSetOf()
) {
  if (treeRoot == null) return

  val leftNode = treeRoot.left
  if (leftNode != null && visited.contains(leftNode).not()) {
    depthFirstTraversalRecursive(leftNode, visited)
  }

  val rightNode = treeRoot.right
  if (rightNode != null && visited.contains(rightNode).not()) {
    depthFirstTraversalRecursive(rightNode, visited)
  }

  println(treeRoot)
  visited.add(treeRoot)
}

fun depthFirstTraversalInline(treeRoot: WaifuNode) {
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

