package img.ui

import img.Domain.{Event, Team1, Team2}
import img.Main.ErrorstreamOrEvent
import scala.util.{Success, Try}

object ParserInput {
  final def parse(input: String): ErrorstreamOrEvent = {
    Try{Integer.parseInt(input, 16)} match {
      case Success(value) => {
        val binary = value.toBinaryString
        val length = binary.length

        length > 20 match {
          case true =>
            val List(when, team1, team2, who, scores) = List(
              binary.slice(0, length-19),
              binary.slice(length-19, length-11),
              binary.slice(length-11, length-3),
              binary.slice(length-3, length-2),
              binary.slice(length-2, length),
            ).map(Integer.parseInt(_, 2))

            Right(Event(when, team1, team2, if(who == 0) Team1 else Team2, scores))

          case false => Left(ErrorInvalidInput)
        }
      }
      case _ => Left(ErrorInvalidInput)
    }
  }
}
