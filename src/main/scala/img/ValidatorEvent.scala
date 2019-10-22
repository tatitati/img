package img

import scala.util.{Success, Try}

object ValidatorEvent {
  def isValid(newEvent: Event, lastEvent: Option[Event]): Boolean = {
    lazy val pointsArePositive = newEvent.pointsScored > 0

    lazy val isMostRecent = lastEvent match {
        case Some(lastEvent) => newEvent.when > lastEvent.when
        case None => true
      }

    isMostRecent && pointsArePositive
  }
}
