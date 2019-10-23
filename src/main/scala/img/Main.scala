package img

import img.Application.{ServiceAddEvent, ServiceFindAllEvents, ServiceFindLastEvent, ServiceFindLastNEvents}
import img.Domain.Event
import img.Infrastructure.RepositoryEvents
import img.ui.{ErrorInvalidBinaryStream, ParserInput}

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
          val service = new ServiceAddEvent(repo)
          ParserInput.parse(input).map(service.run(_))
          displayMenu()

        case "2" =>
          val service = new ServiceFindAllEvents(repo)
          service.run().map(println(_))
          displayMenu()

        case "3" =>
          val service = new ServiceFindLastEvent(repo)
          service.run().map(println(_))
          displayMenu()

        case "4" =>
          println("Q: Introduce n: ")
          val n = Try{scala.io.StdIn.readInt()}
          n match {
            case Success(num) =>
              val service = new ServiceFindLastNEvents(repo)
              service.run(num).map(println(_))
            case Failure(_) => println("Invalid number")
          }

          displayMenu()

        case "q" => println("Done")
        case _ =>
          println("Invalid option")
          displayMenu()
      }
  }

  def main(args: Array[String]) = displayMenu()
}