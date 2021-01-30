package structs.binary.search.tree

import data.Waifu
import data.WaifuList
import org.assertj.core.api.Assertions
import java.util.LinkedList

class BinarySearchTreeNode<T : Comparable<T>>(
  val data: T,
  var parent: BinarySearchTreeNode<T>? = null,
  var left: BinarySearchTreeNode<T>? = null,
  var right: BinarySearchTreeNode<T>? = null,
) {
  override fun toString(): String {
    return "BinarySearchTreeNode(data=$data, left=${left?.data}, right=${right?.data})"
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is BinarySearchTreeNode<*>) return false

    if (data != other.data) return false

    return true
  }

  override fun hashCode(): Int {
    return data.hashCode()
  }
}


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

  val height = findHeight(treeRoot)
  val furthestLeft = findFurthestLeft(treeRoot)
  println("Height: $height, furthest left: $furthestLeft")
  findWaifusAtLevel(treeRoot)
  println()
  val midGirl = findMidGirl(treeRoot)
  println("Mid Girl is ${midGirl?.data}")
  Assertions.assertThat(midGirl?.data?.name).isEqualTo("Asuna")
  println()
  val secondBestGirl = findSecondBestGirl(treeRoot)
  println("Second Best Girl is ${secondBestGirl?.data}")
  Assertions.assertThat(secondBestGirl?.data?.name).isEqualTo("Ryuko")
  val bestGirl = findBestGirl(treeRoot)
  println("Best Girl is ${bestGirl?.data}")
  Assertions.assertThat(bestGirl?.data?.name).isEqualTo("Zero Two")
}

fun findBestGirl(treeNode: BinarySearchTreeNode<Waifu>?): BinarySearchTreeNode<Waifu>? {
  if (treeNode == null) return null

  return findBestGirl(treeNode.right) ?: treeNode.right ?: treeNode
}

fun findSecondBestGirl(treeNode: BinarySearchTreeNode<Waifu>?): BinarySearchTreeNode<Waifu>? {
  val bestGirl = findBestGirl(treeNode)
  return bestGirl?.left ?: bestGirl?.parent
}

fun findMidGirl(treeNode: BinarySearchTreeNode<Waifu>?): BinarySearchTreeNode<Waifu>? {
  if (treeNode == null) return null

  return findMidGirl(treeNode.left) ?: treeNode.left ?: treeNode
}

fun findWaifusAtLevel(treeRoot: BinarySearchTreeNode<Waifu>) {
  val queue = LinkedList<BinarySearchTreeNode<Waifu>?>()
  queue.push(treeRoot)
  queue.add(null)
  val visited = mutableSetOf<BinarySearchTreeNode<Waifu>>()
  var level = 0
  while (queue.isNotEmpty()) {
    val current = queue.pop()
    if (current == null) {
      level++
      if (queue.isNotEmpty()) {
        queue.add(null)
      }
      print("level $level")
      println()
    } else {
      print("$current ")
      visited.add(current)
      val leftNode = current.left
      if (leftNode != null && visited.contains(leftNode).not()) {
        queue.add(leftNode)
      }

      val rightNode = current.right
      if (rightNode != null && visited.contains(rightNode).not()) {
        queue.add(rightNode)
      }
    }
  }
}

fun findFurthestLeft(treeRoot: BinarySearchTreeNode<Waifu>?): Int {
  if (treeRoot == null) return 0

  val furthestLeft = maxOf(
    findFurthestLeft(treeRoot.left) + 1,
    findFurthestLeft(treeRoot.right) - 1,
  )
  return furthestLeft
}

fun findHeight(treeRoot: BinarySearchTreeNode<Waifu>?): Int {
  if (treeRoot == null) return 0

  val childHeight = maxOf(
    findHeight(treeRoot.left),
    findHeight(treeRoot.right)
  )
  return childHeight + 1
}

tailrec fun insertWaifu(head: BinarySearchTreeNode<Waifu>?, waifu: Waifu) {
  if (head == null) return

  val comparison = head.data.compareTo(waifu)
  if (comparison > 0) {
    if (head.left == null) {
      head.left = BinarySearchTreeNode(waifu, parent = head)
    } else {
      insertWaifu(head.left, waifu)
    }
  } else if (comparison < 0) {
    if (head.right == null) {
      head.right = BinarySearchTreeNode(waifu, parent = head)
    } else {
      insertWaifu(head.right, waifu)
    }
  }
}
