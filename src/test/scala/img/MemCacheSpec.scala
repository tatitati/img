package img

import org.scalatest.FunSuite

class MemCacheSpec extends FunSuite {
  test("I can add events to the cache") {
    val cache = new Memcache()

    cache.addEvent(EventTeam()
        .onSecond(3)
        .withTeamScores(2, 0)
        .withNewScore(TeamA, 2)
    ).addEvent(EventTeam()
        .onSecond(5)
        .withTeamScores(4, 0)
        .withNewScore(TeamA, 2)
    )

    assert(2 == cache.findAllEvents().length)
  }
}
