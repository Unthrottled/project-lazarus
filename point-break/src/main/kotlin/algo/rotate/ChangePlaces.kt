package algo.rotate

fun main() {
  val toShift = readLine()!!
  repeat(toShift.length * 2) {
    val shift = it + 1
    println("Shifting '$toShift' $shift times ${rotateString(toShift, shift)}")
  }
}

fun rotateString(
  toShift: String,
  shiftRight: Int,
): String {
  val length = toShift.length
  val offset = shiftRight % length
  return if (offset == 0) {
    toShift
  } else {
    var i = offset
    val shiftedString = CharArray(length)
    for (j in 0 until length) {
      shiftedString[i] = toShift[j]
      i = (i + 1) % length
    }
    String(shiftedString)
  }
}
