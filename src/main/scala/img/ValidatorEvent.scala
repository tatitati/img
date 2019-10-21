package img

import scala.util.{Success, Try}

object ValidatorEvent {
  def isValid(newEvent: Event, history: List[Event] = List()): Boolean = {
    lazy val isDuplicated = if (history.contains(newEvent)) false else true
    lazy val isFromPast = {
      val result = Try {history.head}
      result match {
        case Success(lastEvent) => newEvent.when < lastEvent.when
        case _ => true
      }
    }

    !isDuplicated && !isFromPast
  }
}
