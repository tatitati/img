package img.MemcacheSpec

import img.{Event, RepoEvents, TeamA, TeamB}
import org.scalatest.FunSuite

class OnFindLastSpec extends FunSuite {
  test("Can find the last one event") {
    val givenCache = new RepoEvents()
    val givenEvent1 = Event().onSecond(3).withTeamScores(2, 0).withNewScore(TeamA, 2)
    val givenEvent2 = Event().onSecond(5).withTeamScores(4, 0).withNewScore(TeamA, 2)
    val givenEvent3 = Event().onSecond(7).withTeamScores(3, 0).withNewScore(TeamB, 3)

    givenCache
      .addEvent(givenEvent1)
      .addEvent(givenEvent2)
      .addEvent(givenEvent3)

    assert(Some(givenEvent3) == givenCache.findLastEvent())
  }

  test("Edge case: Event list is empty") {
    val givenCache = new RepoEvents()

    assert(None == givenCache.findLastEvent())
  }
}
