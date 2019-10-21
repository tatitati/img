package img

import scala.util.{Success, Try}

object RepoEvents{
  def apply(): RepoEvents = {
    new RepoEvents()
  }
}

class RepoEvents {
  type ListEvent = List[Event]

  private var cache: ListEvent = List()

  def addEvent(newEvent: Event): RepoEvents = {
    if(ValidatorEvent.isValid(newEvent)) {
      this.cache = newEvent :: this.cache
    }

    this
  }

  def findAllEvents(): Option[ListEvent] = {
    this.cache match {
      case Nil => None
      case values => Some(values)
    }
  }

  def findLastNEvents(n: Int): Option[ListEvent] = {
    this.cache.take(n) match {
      case Nil => None
      case events => Some(events)
    }
  }

  def findLastEvent(): Option[Event] = {
    val result = Try{this.cache.head}

    result match {
      case Success(value) => Some(value)
      case _ => None
    }
  }
}
