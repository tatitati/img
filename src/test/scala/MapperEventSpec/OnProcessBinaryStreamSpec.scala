package MapperEventSpec

import img.{Event, MapperEvent}
import org.scalatest.FunSuite

class OnProcessBinaryStreamSpec extends FunSuite {
  test("I can split in a recursive way"){
    val result1 = MapperEvent.processBinaryStream("xxxxxxxxxxxddddddddccccccccbaa", List(2, 1, 8, 8))
    val result2 = MapperEvent.processBinaryStream("xxxxxxxxxxxxxxxxxxxxxxxxddddddddccccccccbaa", List(2, 1, 8, 8))
    val result3 = MapperEvent.processBinaryStream("xxxxxxxxxxxxxxxxxxxxxxxxddddddddccccccccbaa", List(1, 2, 3, 4, 5, 8))

    assert(List("aa", "b", "cccccccc", "dddddddd", "xxxxxxxxxxx") == result1)
    assert(List("aa", "b", "cccccccc", "dddddddd", "xxxxxxxxxxxxxxxxxxxxxxxx") == result2)
    assert(List("a", "ba", "ccc", "cccc", "ddddc", "xxxxdddd", "xxxxxxxxxxxxxxxxxxxx") == result3)
  }
}
