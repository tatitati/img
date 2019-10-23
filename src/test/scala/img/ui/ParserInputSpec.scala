package img.ui

import img.Domain.{Event, Team1, Team2}
import img.Main
import org.scalatest.FunSuite

class ParserInputSpec extends FunSuite {
  test("I can parse valid hexadecimal numbers"){
    assert(Right(Event(30,2,3,Team2,3)) == ParserInput.parse("f0101f"))
  }

  test("Invalid hexadecimals cannot be parsed"){
    assert(Left(ErrorInvalidBinaryStream) == ParserInput.parse("7B"))
  }

  test("test specifics hex provided in the documentation"){

    val result1 = ParserInput.parse("781002")    //  01111 00000010 00000000 0 10
    val result2 = ParserInput.parse("f0101f")    //  11110 00000010 00000011 1 11
    val result22 = ParserInput.parse("F81037")   //  11111 00000010 00000110 1 11
    val result23 = ParserInput.parse("1982032")   // 110011 00000100 00000110 0 10
    val result3 = ParserInput.parse("1310c8a1")
    val result4 = ParserInput.parse("29f981a2")
    val result5 = ParserInput.parse("29f981a2")

    assert(Right(Event(15,2,0,Team1,2)) == result1)
    assert(Right(Event(30,2,3,Team2,3)) == result2)
    assert(Right(Event(31,2,6,Team2,3)) == result22)
    assert(Right(Event(51,4,6,Team1,2)) == result23)
    assert(Right(Event(610,25,20,Team1,1)) == result3)
    assert(Right(Event(1343,48,52,Team1,2)) == result4)
    assert(Right(Event(1343,48,52,Team1,2)) == result5)
  }
}
