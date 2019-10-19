package img.MemcacheSpec

import img.{Event, RepoEvents, TeamA, TeamB}
import org.scalatest.FunSuite

class OnFindAllSpec extends FunSuite {
  test("Can append new items to the head of the list") {
    val givenCache = new RepoEvents()
    val givenEvent1 = Event().onSecond(3).withTeamScores(2, 0).withNewScore(TeamA, 2)
    val givenEvent2 = Event().onSecond(5).withTeamScores(4, 0).withNewScore(TeamA, 2)

    givenCache
      .addEvent(givenEvent1)
      .addEvent(givenEvent2)

    assert(2 == givenCache.findAllEvents().length)
  }
}
