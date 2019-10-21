package MapperEventSpec

import img.{ErrorParsing, MapperEvent}
import org.scalatest.FunSuite

class HexToBinSpec extends FunSuite{

  test("HEX -> BIN") {
    val result = MapperEvent.hexToBin("781002")
    assert(Right("11110000001000000000010") == result)
  }

  test("Edge case: HEX -> BIN") {
    val result = MapperEvent.hexToBin("")
    assert(Left(ErrorParsing) == result)
  }
}
