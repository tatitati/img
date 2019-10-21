package img

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
          val hex: String = scala.io.StdIn.readLine()
          val event = MapperEvent.fromHex(hex.trim)
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