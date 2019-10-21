package img

import scala.annotation.tailrec

object MapperEvent {

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

  def fromHex(hex: String): Either[Error, Event] = {
    val binary = Integer.parseInt(hex, 16).toBinaryString
    val fields = processBinaryStream(binary, List(2, 1, 8, 8))
      .map(Integer.parseInt(_, 2))

    fields match {
      case List(pointsScored, whoscored, team2Total, team1Total, when) =>
        Right(Event(
          when,
          team1Total,
          team2Total,
          whoscored match {
            case 0 => Team1
            case 1 => Team2
          },
          pointsScored
        ))
      case _ => Left(ErrorParsing)
    }
  }
}
