package mhamido.dmg

object Main {
  def main(args: Array[String]): Unit = args match {
    case Array(path)                  => run(path)
    case Array("--file" | "-f", path) => run(path)
    case _ => println(s"Invalid args: ${args.mkString("{", ", ", "}")}")
  }

  // TODO: Nicer error handling
  def run(romPath: String): Unit = {
    Cartridge(romPath).fold(
      _ => Console.err.println(s"Could not load rom from \"$romPath\""),
      { cart => println(cart.info) }
    )
  }
}
