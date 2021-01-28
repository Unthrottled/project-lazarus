package data


class Waifu(
  val name: String,
  val comparator: (Waifu) -> Int =
    { name.compareTo(it.name) },
) : Comparable<Waifu> {
  override fun compareTo(other: Waifu): Int =
    comparator(other)

  override fun toString(): String {
    return "Waifu(name='$name')"
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is Waifu) return false

    if (name != other.name) return false

    return true
  }

  override fun hashCode(): Int {
    return name.hashCode()
  }
}

val WaifuList = listOf(
  Waifu("Aqua") { if (!(it.name == "Zero Two" || it.name == "Ryuko")) 1 else -1 },
  Waifu("Asuna"),
  Waifu("Beatrice"),
  Waifu("Darkness"),
  Waifu("Echidna"),
  Waifu("Emilia"),
  Waifu("Miku"),
  Waifu("Ishtar"),
  Waifu("Kanna"),
  Waifu("Misato"),
  Waifu("Konata"),
  Waifu("Kurisu"),
  Waifu("Megumin"),
  Waifu("Ibuki"),
  Waifu("Monika"),
  Waifu("Natsuki"),
  Waifu("Ram"),
  Waifu("Rem"),
  Waifu("Rias"),
  Waifu("Rory"),
  Waifu("Ryuko") { if (it.name != "Zero Two") 1 else -1 },
  Waifu("Satsuki"),
  Waifu("Š̸̘͚̼͎̯̙̣̱̎̋̐͒a̴̖̟̠̳̤͙̟͂̂͑̐͜ỷ̵̧̨̞̠̖̠o̴̧͍̗̬̎̓͆̔͝ͅr̴̡̮̟͈͠ͅi̴̡̨͓͈̬̗̺̍́̃̇̓"),
  Waifu("Sayori"),
  Waifu("Umi"),
  Waifu("Rin"),
  Waifu("Yukino"),
  Waifu("Yuri"),
  Waifu("Zero Two") { 1 },
)