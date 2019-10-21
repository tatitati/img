package img.RepoEventsSpec

import img.{Event, RepoEvents, Team1, Team2}
import org.scalatest.FunSuite

class OnFindLastNSpec extends FunSuite {

  test("I can fetch the last N events") {
    val repo = new RepoEvents
    val givenEvent1 = Event(when = 10, team1PointsTotal = 0, team2PointsTotal = 3, whoScored = Team2, pointsScored = 3)
    val givenEvent2 = Event(when = 20, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team1, pointsScored = 2)
    val givenEvent3 = Event(when = 30, team1PointsTotal = 4, team2PointsTotal = 3, whoScored = Team1, pointsScored = 2)

    repo.addEvent(givenEvent1)
    repo.addEvent(givenEvent2)
    repo.addEvent(givenEvent3)

    assert(Some(List(givenEvent3, givenEvent2)) == repo.findLastNEvents(2))
  }

  test("If no events, Noneis returned") {
    val repo = new RepoEvents

    assert(None == repo.findLastNEvents(2))
  }
}
