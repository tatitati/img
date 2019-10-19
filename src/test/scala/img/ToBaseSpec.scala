package img

import org.scalatest.FunSuite

class ToBaseSpec extends FunSuite {
  test("DEC -> BINARY") {
    assert("100" === 4.toBinaryString)
  }

  test("DEC -> HEX") {
    assert("2A" === 42.toHexString.toUpperCase())
  }


  test("DEC <- BINARY") {
    assert(5 === Integer.parseInt("101", 2))
  }

  test("DEC <- HEX") {
    assert(42 === Integer.parseInt("2A", 16))
  }
}
