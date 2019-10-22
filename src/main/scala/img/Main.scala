package img

import img.Application.{ErrorInvalidBinaryStream, ErrorInvalidEvent, ValidatorEvent}
import img.Domain.{Event, Team1, Team2}
import img.Infrastructure.RepositoryEvents

import scala.annotation.tailrec

object Main {

  type ErrorstreamOrEvent = Either[ErrorInvalidBinaryStream.type, Event]

  @tailrec
  def displayMenu(repo: RepositoryEvents): Unit = {

      println("\n=====================\n")
      println("1: Add new event (hex)")
      println("2: Find all events")
      println("3: Find last event")
      println("4: Find last N event")
      println("5: quit")
      println("\n=====================\n")

      val choice = scala.io.StdIn.readInt()

      choice match{
        case 1 =>
          println("Introduce Hexadecimal (example: f0101f): ")
          val hex: Int = scala.io.StdIn.readInt()
//          val event = MapperEvent.fromHex(hex)
//          event.map(repo.addEvent(_))
          displayMenu(repo)

        case 2 =>
          repo.findAllEvents().map(println(_))
          displayMenu(repo)

        case 3 =>
          repo.findLastEvent().map(println(_))
          displayMenu(repo)

        case 4 =>
          println("Introduce n: ")
          val n=scala.io.StdIn.readInt()
          repo.findLastNEvents(n).map(println(_))
          displayMenu(repo)

        case 5 => println("Done")
      }
  }

  def parseInput(hex: Int): Either[ErrorInvalidBinaryStream.type, Event] = {
    splitBinaryStream(hex.toBinaryString) match {
      case Right(chunks) =>
        val List(pointsScored, whoscored, team2Total, team1Total, when) = chunks.map {Integer.parseInt(_, 2)}
        Right(Event(when, team1Total, team2Total, if(whoscored == 0) Team1 else Team2, pointsScored))
      case Left(error) => Left(error)
    }
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

  def main(args: Array[String]) {
    val repo = new RepositoryEvents
    displayMenu(repo)
  }
}