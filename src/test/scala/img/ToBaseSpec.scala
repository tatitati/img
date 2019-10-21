package img

import org.scalatest.FunSuite

class ToBaseSpec extends FunSuite {
  test("DEC -> HEX") {
    assert("2A" === 42.toHexString.toUpperCase())
    assert("781002" === 7868418.toHexString.toUpperCase(), "From the doc")
  }

  test("DEC <- BINARY") {
    assert(5 === Integer.parseInt("101", 2))
    assert(7868418 === Integer.parseInt("11110000001000000000010", 2), "From the doc")
  }

  test("HEX -> BIN"){
    val binary1 = Integer.parseInt("781002", 16).toBinaryString
    val binary2 = Integer.parseInt("f0101f", 16).toBinaryString

    assert("11110000001000000000010" == binary1)
    assert("111100000001000000011111" == binary2)
  }
}
