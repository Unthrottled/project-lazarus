package structs.binary.search.tree

import data.Waifu
import data.WaifuList

data class BinarySearchTreeNode<T: Comparable<T>>(
  val data: T,
  var parent: BinarySearchTreeNode<T>? = null,
  var left: BinarySearchTreeNode<T>? = null,
  var right: BinarySearchTreeNode<T>? = null,
) {
  override fun toString(): String {
    return "BinarySearchTreeNode(data=$data, left=$left, right=$right)"
  }
}


fun main() {
  val treeRoot = WaifuList.subList(1, WaifuList.size).fold(
    BinarySearchTreeNode(WaifuList.first())
  ) {
    head, waifu ->
    insertWaifu(head, waifu)
    head
  }

//  val height = findHeight(treeRoot)
  println(treeRoot)
}

tailrec fun insertWaifu(head: BinarySearchTreeNode<Waifu>?, waifu: Waifu) {
  if(head == null) return

  val comparison = head.data.compareTo(waifu)
  if(comparison > 0) {
    if(head.left == null) {
      head.left = BinarySearchTreeNode(waifu, parent = head)
    } else {
      insertWaifu(head.left, waifu)
    }
  } else if (comparison < 0) {
    if(head.right == null) {
      head.right = BinarySearchTreeNode(waifu, parent = head)
    } else {
      insertWaifu(head.right, waifu)
    }
  }
}
