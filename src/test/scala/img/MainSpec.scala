package img

import img.Application.ErrorInvalidBinaryStream
import img.Domain.{Event, Team1, Team2}
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

  test("I cannot parse an invalid stream") {
    assert(Left(ErrorInvalidBinaryStream) == Main.parseInput(0x10FF1),
      "For binary: " + 0x10FF1.toBinaryString)
  }

  test("test specifics hex provided in the documentation"){
    val result1 = Main.parseInput(0x781002)
    val result2 = Main.parseInput(0xf0101f)
    val result3 = Main.parseInput(0x1310c8a1)
    val result4 = Main.parseInput(0x29f981a2)
    val result5 = Main.parseInput(0x29f981a2)

    assert(Right(Event(15,2,0,Team1,2)) == result1)
    assert(Right(Event(30,2,3,Team2,3)) == result2)
    assert(Right(Event(610,25,20,Team1,1)) == result3)
    assert(Right(Event(1343,48,52,Team1,2)) == result4)
    assert(Right(Event(1343,48,52,Team1,2)) == result5)
  }
}
