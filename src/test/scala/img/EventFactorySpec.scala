package img

import org.scalatest.FunSuite

class EventFactorySpec extends FunSuite {
  test("after 15 seconds of play, Team 1 scores 2 points, then the following will be received"){
    val event = Event.fromHex("781002")
    assert(Event(15,2,0,Team1,2) == event)
  }

  test("If 15 seconds later, Team 2 replies with 3 points, then the following will be received"){
    val event = Event.fromHex("f0101f")
    assert(Event(30,2,3,Team2,3) == event)
  }

  test("inconsistent HEX 1310c8a1"){
    val event = Event.fromHex("1310c8a1")
    assert(Event(610,25,20,Team1,1) == event)
  }

  test("inconsistent HEX 29f981a2"){
    val event = Event.fromHex("29f981a2")
    assert(Event(1343,48,52,Team1,2) == event)
  }

  test("inconsistent HEX 48332327"){
    val event = Event.fromHex("48332327")
    assert(Event(2310,100,100,Team2,3) == event)
  }
}
