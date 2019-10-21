package img.RepoEventsSpec

import img.{Event, RepoEvents, Team1, Team2}
import org.scalatest.FunSuite

class OnFindLastSpec extends FunSuite {
  test("Can find the last event"){
    val repo = new RepoEvents

    val givenEvent1 = Event(when = 10, team1PointsTotal = 0, team2PointsTotal = 3, whoScored = Team2, pointsScored = 3)
    val givenEvent2 = Event(when = 20, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team1, pointsScored = 2)

    repo.addEvent(givenEvent1)
    repo.addEvent(givenEvent2)

    assert(Some(givenEvent2) == repo.findLastEvent())
  }
}
