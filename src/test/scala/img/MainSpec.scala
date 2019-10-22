package img

import img.Application.ErrorInvalidBinaryStream
import img.Domain.{Event, Team2}
import org.scalatest.FunSuite

class MainSpec extends FunSuite {
  test("binary can be parsed") {
    val result = Main.splitBinaryStream("0000000000001111111100000000111")
    assert(Right(List(
      "11", // scored
      "1",  // team
      "00000000",  // team2
      "11111111",  // team1
      "000000000000" // when
    )) == result )
  }


  test("Cannot be parsed invalid streams"){
    val result1 = Main.splitBinaryStream("11")
    assert(Left(ErrorInvalidBinaryStream) == result1)
  }

  test("I can parse a valid stream") {
    assert(Right(Event(2,255,8,Team2,3)) == Main.parseInput(0x17F847),
      "For binary: " + 0x17F847.toBinaryString)
  }

  test("I can parse a valid stream2") {
    val a = "10FF1"
    val should = 0x10FF1

    assert(Integer.parseInt(a, 16) == should)
  }

  test("I cannot parse an invalid stream") {
    assert(Left(ErrorInvalidBinaryStream) == Main.parseInput(0x10FF1),
      "For binary: " + 0x10FF1.toBinaryString)
  }
}
