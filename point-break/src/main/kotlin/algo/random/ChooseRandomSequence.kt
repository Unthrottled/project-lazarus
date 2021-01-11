package algo.random

import kotlin.random.Random


fun main() {
  val (m, n) = readLine()?.split(" ")?.map { it.toInt() } ?: emptyList()

  val random = Random(69)
  var toChoose = m
  for(i in (0..n)) {
    if(random.nextLong() % (n - i) < toChoose) {
      println("$i,")
      toChoose--
      if(toChoose < 1) return
    }
  }
}