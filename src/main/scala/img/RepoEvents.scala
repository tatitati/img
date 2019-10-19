package img

import scala.util.{Success, Try}

object RepoEvents{
  def apply(): RepoEvents = {
    new RepoEvents()
  }
}

class RepoEvents {
  private var cache: List[Event] = List()

  def addEvent(eventTeam: Event): RepoEvents = {
    this.cache = eventTeam :: this.cache
    this
  }

  def findAllEvents(): List[Event] = {
    this.cache
  }

  def findLastEvents(n: Int): List[Event] = {
    this.cache.take(n)
  }

  def findLastEvent(): Option[Event] = {
    val result = Try{this.cache.head}

    result match {
      case Success(value) => Some(value)
      case _ => None
    }
  }
}
