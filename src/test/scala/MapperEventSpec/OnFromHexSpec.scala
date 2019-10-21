package MapperEventSpec

import img.{ErrorParsing, Event, MapperEvent, Team1, Team2}
import org.scalatest.FunSuite

class OnFromHexSpec extends FunSuite {
  test("From the spec I can parse the provided events"){
    val event1 = MapperEvent.fromHex("781002")
    val event2 = MapperEvent.fromHex("f0101f")
    val event3 = MapperEvent.fromHex("1310c8a1")
    val event4 = MapperEvent.fromHex("29f981a2")
    val event5 = MapperEvent.fromHex("48332327")

    assert(Right(Event(15,2,0,Team1,2)) == event1, "Received on 15 sec")
    assert(Right(Event(30,2,3,Team2,3)) == event2, "Received after 15sec more")
    assert(Right(Event(610,25,20,Team1,1)) == event3, "at 10.10")
    assert(Right(Event(1343,48,52,Team1,2)) == event4, "at 22.23")
    assert(Right(Event(2310,100,100,Team2,3)) == event5, "38.30")
  }
}
