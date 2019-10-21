package MapperEventSpec

import img.{Event, MapperEvent}
import org.scalatest.FunSuite

class OnProcessBinaryStreamSpec extends FunSuite {
  test("I can split in a recursive way"){
    val result1 = MapperEvent.processBinaryStream("xxxxxxxxxxxddddddddccccccccbaa")
    val result2 = MapperEvent.processBinaryStream("xxxxxxxxxxxxxxxxxxxxxxxxddddddddccccccccbaa")

    assert(List("aa", "b", "cccccccc", "dddddddd", "xxxxxxxxxxx") == result1)
    assert(List("aa", "b", "cccccccc", "dddddddd", "xxxxxxxxxxxxxxxxxxxxxxxx") == result2)
  }

  test("Edge case"){
    val result1 = MapperEvent.processBinaryStream("")

    assert(List("aa", "b", "cccccccc", "dddddddd", "xxxxxxxxxxx") == result1)
  }
}
