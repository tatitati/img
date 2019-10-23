package img.ui

import img.Domain.{Event, Team1, Team2}
import img.Main.ErrorstreamOrEvent
import scala.util.{Success, Try}

object ParserInput {
  final def parse(hex: String): ErrorstreamOrEvent = {
    val number = Try{Integer.parseInt(hex, 16)}
    number match {
      case Success(value) => {
        val binary = value.toBinaryString

        binary.length > 20 match {
          case true =>
            val inv = binary.reverse
            val List(scores, who, team2, team1, when) = List(
              inv.slice(0, 2),
              inv.slice(2, 3),
              inv.slice(3, 11),
              inv.slice(11, 19),
              inv.slice(19, binary.length)
            ).map(_.reverse).map(Integer.parseInt(_, 2))

            Right(Event(when, team1, team2, if(who == 0) Team1 else Team2, scores))

          case false => Left(ErrorInvalidInput)
        }
      }
      case _ => Left(ErrorInvalidInput)
    }
  }
}
