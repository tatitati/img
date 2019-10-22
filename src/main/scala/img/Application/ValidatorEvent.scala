package img.Application

import img.Domain.Event

object ValidatorEvent {
  def isValid(newevent: Event, lastEvent: Option[Event]): Boolean = {
    isNewEventAfterLastEvent(newevent, lastEvent)
  }

  def isNewEventAfterLastEvent(newevent: Event, lastEvent: Option[Event]): Boolean = {
    lastEvent match {
      case Some(last) => last.when < newevent.when
      case None => true
    }
  }

//  def teamRockingHasScoreUpdated(newevent: Event, lastEvent: Option[Event]): Boolean = {
//    lastEvent match {
//      case Some(last) => last.when < newevent.when
//      case None => true
//    }
//  }
}
