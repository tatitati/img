package img

import org.scalatest.FunSuite

class ToBaseSpec extends FunSuite {
  test("DEC -> BINARY") {
    assert("100" === 4.toBinaryString)
  }

  test("DEC -> HEX") {
    assert("2A" === 42.toHexString.toUpperCase())
    assert(42 === Integer.parseInt("2A", 16))
  }
}
