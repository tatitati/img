package img.Infrastructure

import img.Application.ValidatorEvent
import img.Domain
import img.Domain.{Event, Team1}
import org.scalatest.FunSuite

class ValidatorEventSpec extends FunSuite {

  test("A duplidate event is not valid") {
    val givenNewEvent = Event(when = 20, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team1, pointsScored = 2)
    val givenLastEvent = Domain.Event(when = 20, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team1, pointsScored = 2)

    assert(false == ValidatorEvent.isValid(givenNewEvent, Some(givenLastEvent)))
  }

  test("we cannot receive data from the past") {
    val givenLastEvent = Domain.Event(when = 20, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team1, pointsScored = 2)
    val givenNewEvent = Domain.Event(when = 10, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team1, pointsScored = 2)

    assert(false == ValidatorEvent.isValid(givenNewEvent, Some(givenLastEvent)))
  }

  test("An event with score = 0 is not valid") {
    val givenLastEvent = Domain.Event(when = 20, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team1, pointsScored = 2)
    val givenNewEvent = Domain.Event(when = 10, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team1, pointsScored = 0)

    assert(false == ValidatorEvent.isValid(givenNewEvent, Some(givenLastEvent)))
  }
}
