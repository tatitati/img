package img

import org.scalatest.FunSuite

class ToBaseSpec extends FunSuite {
  test("DEC -> BINARY") {
    assert("100" === 4.toBinaryString)
    assert("11110000001000000000010" === 7868418.toBinaryString, "from the doc")
  }

  test("DEC -> HEX") {
    assert("2A" === 42.toHexString.toUpperCase())
    assert("781002" === 7868418.toHexString.toUpperCase(), "From the doc")
  }

  test("DEC <- BINARY") {
    assert(5 === Integer.parseInt("101", 2))
    assert(7868418 === Integer.parseInt("11110000001000000000010", 2), "From the doc")
  }

  test("DEC <- HEX") {
    assert(42 === Integer.parseInt("2A", 16))
    assert(15732767 === Integer.parseInt("f0101f", 16), "from the doc")
    assert(0xf0101f == 15732767)
  }

  test("HEX -> BIN"){
    val binary1 = Integer.parseInt("781002", 16).toBinaryString
    val binary2 = Integer.parseInt("f0101f", 16).toBinaryString

    assert("11110000001000000000010" == binary1)
    assert("111100000001000000011111" == binary2)
  }
}
