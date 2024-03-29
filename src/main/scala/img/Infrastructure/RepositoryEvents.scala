package img.Infrastructure

import img.Domain.Event

object RepositoryEvents{
  def apply(): RepositoryEvents = new RepositoryEvents()
}

class RepositoryEvents {
  type ListEvent = List[Event]
  private var cache: ListEvent = List()

  def findAllEvents(): ListEvent = this.cache
  def findLastNEvents(n: Int): ListEvent = this.cache.take(n)
  def addEvent(newEvent: Event): Unit = this.cache = newEvent :: this.cache

  def findLastEvent(): Option[Event] = this.cache match {
    case List(head, _*) => Some(head)
    case _ => None
  }
}
