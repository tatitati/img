package img.Infrastructure.RepositoryEventsSpec

import img.Domain.{Event, Team1, Team2}
import img.Infrastructure.RepositoryEvents
import img.Domain
import org.scalatest.FunSuite

class OnAddEventSpec extends FunSuite {
  val repo = new RepositoryEvents

  test("I can add a valid event to memory cache") {
    val givenEvent1 = Event(when = 10, team1PointsTotal = 0, team2PointsTotal = 3, whoScored = Team2, pointsScored = 3)
    val givenEvent2 = Domain.Event(when = 20, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team1, pointsScored = 2)

    repo.addEvent(givenEvent1)
    repo.addEvent(givenEvent2)

    assert(List(givenEvent2, givenEvent1) == repo.findAllEvents())
  }
}
