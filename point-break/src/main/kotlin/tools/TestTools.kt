package tools

import java.time.Duration
import java.time.Instant


fun timed (runner: () -> Unit) {
  val before = Instant.now()
  runner()
  println("Process took: ${Duration.between(before, Instant.now()).toMillis()}ms")
}