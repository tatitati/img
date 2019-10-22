package img.Infrastructure.RepositoryEventsSpec

import img.Domain
import img.Domain.{Event, Team1, Team2}
import img.Infrastructure.RepositoryEvents
import org.scalatest.FunSuite

class FindAllEventsSpec extends FunSuite {
  test("All events are returned") {
      val repo = new RepositoryEvents
      val givenEvent1 = Event(when = 10, team1PointsTotal = 0, team2PointsTotal = 3, whoScored = Team2, pointsScored = 3)
      val givenEvent2 = Domain.Event(when = 20, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team1, pointsScored = 2)

      repo.addEvent(givenEvent1)
      repo.addEvent(givenEvent2)

      assert(List(givenEvent2, givenEvent1) == repo.findAllEvents())
  }
}
