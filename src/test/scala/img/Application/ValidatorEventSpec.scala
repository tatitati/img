package img.Application

import img.Domain.{Event, Team1}
import org.scalatest.FunSuite

class ValidatorEventSpec extends FunSuite {
  test("I can check if a new event is valid") {
    val givenLastEvent = Event(when = 20, 2, 0, Team1, 1)
    val givenNewEvent = Event(when = 10, 3, 0, Team1, 1)

    assert(false == ValidatorEvent.isNewEventAfterLastEvent(givenNewEvent, Some(givenLastEvent)))
  }
}
