package img.Application

import img.Domain.{Event, Team1}
import org.scalatest.FunSuite

class ValidatorEventSpec extends FunSuite {
  test("I can define what is a valid event") {
    val givenLastEvent = Some(Event(when = 20, 2, 0, Team1, 1))
    val givenNewEvent = Event(when = 30, 3, 0, Team1, 1)

    assert(true == ValidatorEvent.isValid(givenNewEvent, givenLastEvent))
  }

  test("Invalid: a new event that happen in the past") {
    val givenLastEvent = Some(Event(when = 20, 2, 0, Team1, 1))
    val givenNewEvent = Event(when = 10, 3, 0, Team1, 1)

    assert(false == ValidatorEvent.isValid(givenNewEvent, givenLastEvent))
  }

  test("Invalid: Team1 score was not updated") {
    val givenLastEvent = Some(Event(when = 20, 2, 0, Team1, 1))
    val givenNewEvent = Event(when = 30, 2, 0, Team1, 1)

    assert(false == ValidatorEvent.isValid(givenNewEvent, givenLastEvent))
  }

  test("Invalid: Team2 is cheating") {
    val givenLastEvent = None
    val givenNewEvent = Event(when = 30, 2, 1, Team1, 1)

    assert(false == ValidatorEvent.isValid(givenNewEvent, givenLastEvent))
  }
}
