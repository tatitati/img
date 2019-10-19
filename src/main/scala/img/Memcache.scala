package img

import scala.util.{Success, Try}

object Memcache{
  def apply(): Memcache = {
    new Memcache()
  }
}

class Memcache {
  private var cache: List[EventTeam] = List()

  // I didn't receive the file with the fixture data, so I have to create it in any way.
  // I decided to create my fixture in memory. Still I can create inconsistent data like
  // negative scores, events with zero scores......not sure how much inconsistent can be, but it allows me
  // to play with the design
  def addEvent(eventTeam: EventTeam): Memcache = {
    this.cache = eventTeam :: this.cache
    this
  }

  def findAllEvents(): List[EventTeam] = {
    this.cache
  }

  def findLastEvents(n: Int): List[EventTeam] = {
    this.cache.take(n)
  }

  def findLastEvent(): Option[EventTeam] = {
    val result = Try{this.cache.head}

    result match {
      case Success(value) => Some(value)
      case _ => None
    }
  }
}
