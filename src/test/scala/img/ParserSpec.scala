package img

import org.scalatest.FunSuite

class ParserSpec extends FunSuite {
  test("I can split in a recursive way"){
    val result1 = Event.processBinaryStream("xxxxxxxxxxxddddddddccccccccbaa", List(2, 1, 8, 8))
    val result2 = Event.processBinaryStream("xxxxxxxxxxxxxxxxxxxxxxxxddddddddccccccccbaa", List(2, 1, 8, 8))
    val result3 = Event.processBinaryStream("xxxxxxxxxxxxxxxxxxxxxxxxddddddddccccccccbaa", List(1, 2, 3, 4, 5, 8))

    assert(List("aa", "b", "cccccccc", "dddddddd", "xxxxxxxxxxx") == result1)
    assert(List("aa", "b", "cccccccc", "dddddddd", "xxxxxxxxxxxxxxxxxxxxxxxx") == result2)
    assert(List("a", "ba", "ccc", "cccc", "ddddc", "xxxxdddd", "xxxxxxxxxxxxxxxxxxxx") == result3)
  }
}
