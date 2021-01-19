package structs

import kotlin.random.Random

fun main() {
  val heapSize = 10
  repeat(4) {
    val random = Random(69 + it)
    val heap = ArrayHeap(heapSize)
    repeat(heapSize + 1) {
      val i = random.nextInt(1, Int.MAX_VALUE)
      heap.add(i)
    }
    println(heap)
    println(heap.isHeap)
  }

}

class ArrayHeap(val size: Int) {
  val storage = IntArray(size + 1)
  private var n = 0
  fun add(toAdd: Int) {
    if (n + 1 > size) return

    storage[++n] = toAdd

    siftUp(n)
  }

  val isHeap: Boolean
    get() {
      var i = 1
      while (i < n) {
        val current = storage[i]
        var childIndex = i * 2
        if (childIndex <= n && current > storage[childIndex]) {
          return false
        }

        if (++childIndex <= n && current > storage[childIndex]) {
          return false
        }

        i++
      }
      return true
    }


  private fun siftUp(n: Int) {
    var i = n
    while (i > 1) {
      val currentNode = storage[i]
      val parentIndex = i / 2
      val parent = storage[parentIndex]
      if (parent > currentNode) swap(parentIndex, i)
      else break

      i = parentIndex
    }
  }

  private fun siftDown(toAdd: Int) {
    var i = 0
    while (true) {
      var childIndex = 2 * 1
      if (childIndex > size) break

      if (childIndex + 1 <= size &&
        storage[childIndex + 1] < storage[childIndex]
      ) {
        childIndex++
      }
      if (storage[i] <= storage[childIndex]) break
      swap(i, childIndex)
      i = childIndex
    }
  }

  private fun swap(i: Int, childIndex: Int) {
    val t = storage[i]
    storage[i] = storage[childIndex]
    storage[childIndex] = t
  }

  override fun toString(): String = storage.contentToString()
}