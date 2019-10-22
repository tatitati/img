package img

import img.Application.{AddEventService, FindAllEventsService, FindLastEventService, FindLastNEventsService}
import img.Domain.{Event, Team1, Team2}
import img.Infrastructure.RepositoryEvents
import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}

object Main {

  type ErrorstreamOrEvent = Either[ErrorInvalidBinaryStream.type, Event]
  val repo = new RepositoryEvents

  @tailrec
  def displayMenu(): Unit = {

      println("\n======== MENU =======\n")
      println("1: Add new event (hex)")
      println("2: Find all events")
      println("3: Find last event")
      println("4: Find last N event")
      println("q: quit")
      println("\n=====================\n")

      val choice = scala.io.StdIn.readLine().trim

      choice match{
        case "1" =>
          println("Q: Introduce Hexadecimal (examples: 781002  f0101f  f81037  1982032): ")
          val input: String = scala.io.StdIn.readLine().trim
          val hex = Try{Integer.parseInt(input, 16)}
          hex match {
            case Success(hexValue) =>
              val service = new AddEventService(repo)
              parseInput(hexValue).map(service.run(_))
            case Failure(_) =>
              println("Invalid Hex")
          }

          displayMenu()

        case "2" =>
          val service = new FindAllEventsService(repo)
          service.run().map(println(_))
          displayMenu()

        case "3" =>
          val service = new FindLastEventService(repo)
          service.run().map(println(_))
          displayMenu()

        case "4" =>
          println("Q: Introduce n: ")
          val n = Try{scala.io.StdIn.readInt()}
          n match {
            case Success(num) =>
              val service = new FindLastNEventsService(repo)
              service.run(num).map(println(_))
            case Failure(_) =>
              println("Invalid number")
          }

          displayMenu()

        case "q" => println("Done")
        case _ =>
          println("Invalid option")
          displayMenu()
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
    displayMenu()
  }
}