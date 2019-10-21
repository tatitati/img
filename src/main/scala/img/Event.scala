package img

import scala.annotation.tailrec

object Event{
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

  def fromHex(hex: String): Event = {
    val binary = Integer.parseInt(hex, 16).toBinaryString
    val fields = splitBinary(binary, List(2, 1, 8, 8))
    val numbers = fields.map(Integer.parseInt(_, 2))

    Event(
      numbers(4),
      numbers(3),
      numbers(2),
      numbers(1) match {
        case 0 => Team1
        case 1 => Team2
      },
      numbers(0)
    )
  }
}
case class Event(
    when: Int,
    team1PointsTotal: Int,
    team2PointsTotal: Int,
    whoScored: Team,
    pointsScored: Int
 )

