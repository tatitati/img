package img.Infrastructure.RepositoryEventsSpec

import img.Domain.{Event, Team1, Team2}
import img.Infrastructure.RepositoryEvents
import img.Domain
import org.scalatest.FunSuite

class OnFindLastSpec extends FunSuite {
  test("Can find the last event"){
    val repo = new RepositoryEvents

    val givenEvent1 = Event(when = 10, team1PointsTotal = 0, team2PointsTotal = 3, whoScored = Team2, pointsScored = 3)
    val givenEvent2 = Domain.Event(when = 20, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team1, pointsScored = 2)

    repo.addEvent(givenEvent1)
    repo.addEvent(givenEvent2)

    assert(Some(givenEvent2) == repo.findLastEvent())
  }

  test("None is returned if there is no events"){
      val repo = new RepositoryEvents
      assert(None == repo.findLastEvent())
  }
}
