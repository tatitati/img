package img

import scala.annotation.tailrec

object Event{
  @tailrec
  def processBinaryStream(data: String, idxSplits: List[Int], accumulator: List[String] = List()): List[String] = {
    idxSplits match {
      case Nil => accumulator :+ data
      case _ =>
        val nextSplit = data.length - idxSplits.head
        val(binaryhead: String, binarytail: String) = data.splitAt(nextSplit)

        processBinaryStream(binaryhead, idxSplits.tail, accumulator :+ binarytail)
    }
  }

  def fromHex(hex: String): Event = {
    val binary = Integer.parseInt(hex, 16).toBinaryString
    val fields = processBinaryStream(binary, List(2, 1, 8, 8))
      .map(Integer.parseInt(_, 2))

    Event(
      fields(4),
      fields(3),
      fields(2),
      fields(1) match {
        case 0 => Team1
        case 1 => Team2
      },
      fields(0)
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

