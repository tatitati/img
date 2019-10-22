package img.Infrastructure

import img.Domain.{Event, Team}

import scala.util.{Success, Try}

object RepositoryEvents{
  def apply(): RepositoryEvents = {
    new RepositoryEvents()
  }
}

class RepositoryEvents {
  type ListEvent = List[Event]

  private var cache: ListEvent = List()

  def addEvent(newEvent: Event): RepositoryEvents = {
    this.cache = newEvent :: this.cache

    this
  }

  def findAllEvents(): ListEvent = {
    this.cache
  }

  def findLastNEvents(n: Int): ListEvent = {
    this.cache.take(n)
  }

  def findLastEvent(): Option[Event] = {
    val result = Try{this.cache.head}

    result match {
      case Success(value) => Some(value)
      case _ => None
    }
  }

  def findLastEventByTeam(team: Team): Option[Event] = {
    val events: List[Event] = this.cache.filter(_.whoScored == team)
     events match {
      case Nil => None
      case values => Some(values.head)
    }
  }
}
