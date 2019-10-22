package img.Infrastructure.RepositoryEventsSpec

import img.Domain.{Event, Team1, Team2}
import img.Infrastructure.RepositoryEvents
import org.scalatest.FunSuite

class OnFindLastEventByTeamSpec extends FunSuite {

  val repo = new RepositoryEvents

  test("I can request the last event of a team") {
    val givenEvent1 = Event(when = 10, team1PointsTotal = 0, team2PointsTotal = 3, whoScored = Team2, pointsScored = 3)
    val givenEvent2 = Event(when = 20, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team2, pointsScored = 2)
    val givenEvent3 = Event(when = 30, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team1, pointsScored = 2)
    val givenEvent4 = Event(when = 40, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team1, pointsScored = 2)

    repo
      .addEvent(givenEvent1)
      .addEvent(givenEvent2)
      .addEvent(givenEvent3)
      .addEvent(givenEvent4)

    assert(Some(givenEvent2) == repo.findLastEventByTeam(Team2))
    assert(Some(givenEvent4) == repo.findLastEventByTeam(Team1))
  }
}
