package img

import org.scalatest.FunSuite

class ValidatorEventSpec extends FunSuite {

  test("A duplidate event is not valid") {
    val givenNewEvent = Event(when = 20, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team1, pointsScored = 2)
    val givenLastEvent = Event(when = 20, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team1, pointsScored = 2)

    assert(false == ValidatorEvent.isValid(givenNewEvent, Some(givenLastEvent)))
  }

  test("we cannot receive data from the past") {
    val givenLastEvent = Event(when = 20, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team1, pointsScored = 2)
    val givenNewEvent = Event(when = 10, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team1, pointsScored = 2)

    assert(false == ValidatorEvent.isValid(givenNewEvent, Some(givenLastEvent)))
  }
}
