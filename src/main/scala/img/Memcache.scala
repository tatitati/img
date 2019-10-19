package img

object Memcache{
  def apply(): Memcache = {
    new Memcache()
  }
}

class Memcache {
  private var cache: List[EventTeam] = List()

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
    
  }
}
