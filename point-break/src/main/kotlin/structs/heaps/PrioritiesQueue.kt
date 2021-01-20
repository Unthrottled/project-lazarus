package structs.heaps

import kotlin.random.Random

fun main() {
  val heapSize = 10
  repeat(4) {
    val random = Random(69)
    val priorityQueue = PrioritiesQueueArrayHeap(heapSize)
    repeat(heapSize + 1) {
      val i = random.nextInt(1, 20)
      priorityQueue.insert(i)
    }
    println(priorityQueue)
    println(priorityQueue.isHeap)
    val extractedQueue = (0 until priorityQueue.size).map {
      priorityQueue.extractMin()
    }
    println(extractedQueue)
    println(extractedQueue
      .filterNotNull()
      .foldIndexed(true) { index, acc, n ->
        acc && (index == 0 || extractedQueue[index - 1]?.compareTo(n) ?: -2 <= 0)
      })
    println()
  }
}

interface PrioritiesQueue<T : Comparable<T>> {
  fun insert(t: T)
  fun extractMin(): T?
}

class PrioritiesQueueArrayHeap(
  val size: Int
) : PrioritiesQueue<Int> {
  val storage = Array(size + 1) { 0 }
  private var n = 0
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

  private fun siftDown(indexToSift: Int) {
    var i = indexToSift
    while (true) {
      var childIndex = 2 * i
      if (childIndex > size || childIndex > n) break

      if (childIndex + 1 <= size &&
        storage[childIndex + 1] < storage[childIndex]
      ) {
        childIndex++
      }
      if (storage[i] <= storage[childIndex] || childIndex > n) break
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

  override fun insert(t: Int) {
    if (n + 1 > size) return

    storage[++n] = t

    siftUp(n)
  }

  override fun extractMin(): Int? {
    if (n < 1) return null

    val min = storage[1]
    storage[1] = storage[n]
    storage[n--] = 0

    siftDown(1)

    return min
  }
}