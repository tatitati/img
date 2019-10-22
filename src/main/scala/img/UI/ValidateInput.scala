package img.UI

import img.Domain.Event

object ValidateInput {
  def isValid(newEvent: Event, lastEvent: Option[Event]): Boolean = {
    newEvent.pointsScored > 0
  }
}
