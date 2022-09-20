package mhamido

package object dmg {
  implicit class ByteArrayOpts(val self: Array[Byte]) extends AnyVal {
    // Decode an array of bytes (little endian) into a 4/8 byte integer.
    // TODO: Optimize these

    def toInt: Int = {
      // Curse you java, for your lack of unsigned arithmetic.
      @inline def normalize(x: Byte): Int = if (x < 0) x.toInt + 256 else x
      require(self.length <= 4)

      self.zipWithIndex.map { case (b, i) => normalize(b) << 8 * i }.sum
    }

    def toAscii: String = new String(self, "ascii")
  }
}
