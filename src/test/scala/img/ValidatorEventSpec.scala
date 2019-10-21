package img

import org.scalatest.FunSuite

class ValidatorEventSpec extends FunSuite {

  test("A duplidate event is not valid") {
    val givenEvent1 = Event(when = 10, team1PointsTotal = 0, team2PointsTotal = 3, whoScored = Team2, pointsScored = 3)
    val givenEvent2 = Event(when = 20, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team1, pointsScored = 2)
    val givenEvent3 = Event(when = 20, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team1, pointsScored = 2)

    val givenHistory = List(givenEvent2, givenEvent1)

    assert(false == ValidatorEvent.isValid(givenEvent3, givenHistory))
  }

  test("we cannot receive data from the past") {
    val givenEvent1 = Event(when = 10, team1PointsTotal = 0, team2PointsTotal = 3, whoScored = Team2, pointsScored = 3)
    val givenEvent2 = Event(when = 20, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team1, pointsScored = 2)
    val givenEvent3 = Event(when = 10, team1PointsTotal = 2, team2PointsTotal = 3, whoScored = Team1, pointsScored = 2)

    val givenHistory = List(givenEvent2, givenEvent1)

    assert(false == ValidatorEvent.isValid(givenEvent3, givenHistory))
  }
}
