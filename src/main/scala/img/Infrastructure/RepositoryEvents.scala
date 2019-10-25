package img.Infrastructure

import img.Domain.Event
import scala.util.{Success, Try}

object RepositoryEvents{
  def apply(): RepositoryEvents = new RepositoryEvents()
}

class RepositoryEvents {
  type ListEvent = List[Event]
  private var cache: ListEvent = List()

  def findAllEvents(): ListEvent = this.cache
  def findLastNEvents(n: Int): ListEvent = this.cache.take(n)

  def addEvent(newEvent: Event): RepositoryEvents = {
    this.cache = newEvent :: this.cache
    this
  }

  def findLastEvent(): Option[Event] = Option(checkExistHead()).flatMap{x =>convert(x)}
  def checkExistHead(): Try[Event] = Try{this.cache.head}

  def convert(result: Try[Event]): Option[Event] = result match {
    case Success(value) => Some(value)
    case _ => None
  }
}
