package img.MemcacheSpec

import img.{EventTeam, Memcache, TeamA, TeamB}
import org.scalatest.FunSuite

class OnFindLastSpec extends FunSuite {
  test("Can find the last one event") {
    val givenCache = new Memcache()
    val givenEvent1 = EventTeam().onSecond(3).withTeamScores(2, 0).withNewScore(TeamA, 2)
    val givenEvent2 = EventTeam().onSecond(5).withTeamScores(4, 0).withNewScore(TeamA, 2)
    val givenEvent3 = EventTeam().onSecond(7).withTeamScores(3, 0).withNewScore(TeamB, 3)

    givenCache
      .addEvent(givenEvent1)
      .addEvent(givenEvent2)
      .addEvent(givenEvent3)

    assert(
      List(givenEvent3) == givenCache.findLastEvent()
    )
  }
}
