package structs.linked.list

import java.util.Optional
import kotlin.random.Random


internal data class Node<T>(
  val data: T,
  val next: Node<T>?
)

internal tailrec fun <T> findValue(
  node: Node<T>,
  predicate: (T) -> Boolean
): Optional<T> {
  return when {
    predicate(node.data) -> Optional.ofNullable(node.data)
    node.next == null -> Optional.empty()
    else -> findValue(node.next, predicate)
  }
}