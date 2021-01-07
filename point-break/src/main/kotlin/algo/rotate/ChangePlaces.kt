package algo.rotate

fun main() {
  val toShift = readLine()!!
  repeat(toShift.length * 2) {
    val shift = it + 1
    println("Shifting right '$toShift' $shift times ${rotateString(toShift, shift, Direction.RIGHT)}")
    println("Shifting left '$toShift' $shift times ${rotateString(toShift, shift, Direction.LEFT)}")
  }
}

internal enum class Direction {
  LEFT, RIGHT
}

private fun rotateString(
  toShift: String,
  shiftPlaces: Int,
  direction: Direction,
): String {
  val length = toShift.length
  val offset = shiftPlaces % length
  return if (offset == 0) {
    toShift
  } else {
    var i = if (direction == Direction.RIGHT) offset
    else length - offset
    val shiftedString = CharArray(length)
    for (j in 0 until length) {
      shiftedString[i] = toShift[j]
      i = (i + 1) % length
    }
    String(shiftedString)
  }
}

