package img

import img.Infrastructure.{MapperEvent, RepoEvents}

import scala.annotation.tailrec

object Main {

  @tailrec
  def ask(repo: RepoEvents): Unit = {

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
          val event = MapperEvent.fromHex(hex)
          event.map(repo.addEvent(_))
          ask(repo)

        case 2 =>
          repo.findAllEvents().map(println(_))
          ask(repo)

        case 3 =>
          repo.findLastEvent().map(println(_))
          ask(repo)

        case 4 =>
          println("Introduce n: ")
          val n=scala.io.StdIn.readInt()
          repo.findLastNEvents(n).map(println(_))
          ask(repo)

        case 5 => println("Done")
      }
  }

  def main(args: Array[String]) {
    val repo = new RepoEvents
    ask(repo)
  }
}