package img.MemcacheSpec

import img.{EventTeam, Memcache, TeamA, TeamB}
import org.scalatest.FunSuite

class OnFindAllSpec extends FunSuite {
  test("I can add events to the cache, where last events are put in the head of the cache") {
    val givenCache = new Memcache()
    val givenEvent1 = EventTeam().onSecond(3).withTeamScores(2, 0).withNewScore(TeamA, 2)
    val givenEvent2 = EventTeam().onSecond(5).withTeamScores(4, 0).withNewScore(TeamA, 2)

    givenCache
      .addEvent(givenEvent1)
      .addEvent(givenEvent2)

    assert(2 == givenCache.findAllEvents().length)
  }
}
