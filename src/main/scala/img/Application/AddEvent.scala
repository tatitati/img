package img.Application

import img.Domain.{Event, Team1, Team2}
import img.Infrastructure.RepositoryEvents
import scala.annotation.tailrec

class AddEvent(repositoryEvents: RepositoryEvents) {

  type ErrorOrEvent = Either[Error, Event]

  def run(hex: Int): ErrorOrEvent = {
    splitBinaryStream(hex.toBinaryString).flatMap{ binaryChunks =>
      validateBinaryChunks(binaryChunks) match {
        case true => val List(pointsScored, whoscored, team2Total, team1Total, when) = binaryChunks.map(Integer.parseInt(_, 2))
          val newEvent = Event(when, team1Total, team2Total, whoscored match { case 0 => Team1 case 1 => Team2}, pointsScored)
          if (ValidatorEvent.isValid(newEvent, repositoryEvents.findLastEvent())) {
            repositoryEvents.addEvent(newEvent)
            Right(newEvent)
          } else {
            Left(ErrorInvalidEvent)
          }
        case false => Left(ErrorInvalidBinaryStream)
      }
    }
  }

  def validateBinaryChunks(values: List[String]): Boolean = {
    val List(pointsScored, whoscored, team2Total, team1Total, when) = values.map(Integer.parseInt(_, 2))
    pointsScored < 4 && pointsScored > 0 && when > 0 && whoscored < 2 && (team2Total > 0 || team1Total > 0)
  }

  @tailrec
  final def splitBinaryStream(data: String, idxSplits: List[Int] = List(2, 1, 8, 8), accumulator: List[String] = List()): Either[ErrorInvalidBinaryStream.type, List[String]] = {
    idxSplits match {
      case Nil =>
        val chunks = (accumulator :+ data)
        chunks.filter(_.isEmpty).isEmpty match {
          case true => Right(chunks)
          case false =>Left(ErrorInvalidBinaryStream)
        }
      case _ =>
        val nextSplit = data.length - idxSplits.head
        val(binaryhead: String, binarytail: String) = data.splitAt(nextSplit)

        splitBinaryStream(binaryhead, idxSplits.tail, accumulator :+ binarytail)
    }
  }
}
