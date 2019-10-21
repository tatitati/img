package img

import org.scalatest.FunSuite
import scala.annotation.tailrec

class ParserSpec extends FunSuite {
  test("I can split in a recursive way"){

    @tailrec
    def splitBinary(data: String, idxSplits: List[Int], accumulator: List[String] = List()): List[String] = {
      idxSplits match {
        case Nil => accumulator :+ data
        case values =>
          val nextSplit = data.length - idxSplits(0)
          val(binaryhead: String, binarytail: String) = data.splitAt(nextSplit)

          splitBinary(binaryhead, idxSplits.tail, accumulator :+ binarytail)
      }
    }

    val result1 = splitBinary("xxxxxxxxxxxddddddddccccccccbaa", List(2, 1, 8, 8))
    val result2 = splitBinary("xxxxxxxxxxxxxxxxxxxxxxxxddddddddccccccccbaa", List(2, 1, 8, 8))
    val result3 = splitBinary("xxxxxxxxxxxxxxxxxxxxxxxxddddddddccccccccbaa", List(1, 2, 3, 4, 5, 8))

    assert(List("aa", "b", "cccccccc", "dddddddd", "xxxxxxxxxxx") == result1)
    assert(List("aa", "b", "cccccccc", "dddddddd", "xxxxxxxxxxxxxxxxxxxxxxxx") == result2)
    assert(List("a", "ba", "ccc", "cccc", "ddddc", "xxxxdddd", "xxxxxxxxxxxxxxxxxxxx") == result3)
  }
}
