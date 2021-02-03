package structs.binary.search

import data.Waifu
import data.WaifuList


fun main() {
  val sortedWaifu = WaifuList

  println(sortedWaifu.mapIndexed { idx, waifu -> "$idx: $waifu" }.joinToString(", "))

  searchForWaifu(sortedWaifu, sortedWaifu[sortedWaifu.size - 1])
  searchForWaifu(sortedWaifu, sortedWaifu[sortedWaifu.size - 2])
  searchForWaifu(sortedWaifu, sortedWaifu[sortedWaifu.size - 3])
  searchForWaifu(sortedWaifu, Waifu("Mai"))
  searchForWaifu(sortedWaifu, Waifu("Ibuki"))
}

private fun searchForWaifu(sortedWaifu: List<Waifu>, waifu: Waifu) {
  println("$waifu is at ${findWaifu(sortedWaifu, waifu)}")
}

fun findWaifu(
  sortedWaifu: List<Waifu>,
  waifu: Waifu
): Int {
  var left = 0
  var right = sortedWaifu.size
  while (left < right) {
    val mid = (left + right) / 2
    val centerWaifu = sortedWaifu[mid]
    if (centerWaifu == waifu) {
      return mid
    } else if (centerWaifu < waifu) {
      left = mid + 1
    } else {
      right = mid
    }
  }
  return -left
}

