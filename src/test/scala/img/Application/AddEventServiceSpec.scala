package img.Application

import img.Domain.{Event, Team1}
import img.Infrastructure.RepositoryEvents
import org.scalatest.FunSuite

class AddEventServiceSpec extends FunSuite {
  val repo = new RepositoryEvents
  // im using a memory cache repo, so I share the same for both
  // in a production code this shouldnt be the case as not state should be kept in the repo.
  val addEvent = new AddEventService(repo)
  val findAll = new FindAllEventsService(repo)

//  test("I cannot add wrong streams") {
//    val result = addEvent.run(0x001002)
//
//    assert("1000000000010" == 0x001002.toBinaryString)
//    asserts(Left(ErrorInvalidBinaryStream) == result)
//    assert(List() == findAll.run())
//  }
//
//  test("I cannot add invalid events") {
//    val result = addEvent.run(0x781012)
//    assert(Left(ErrorInvalidEvent) == result)
//
//    assert("11110000001000000000010" == 0x781002.toBinaryString)
//    assert(List() == findAll.run())
//  }
//
//  test("I can add correct events") {
//    val result = addEvent.run(0x781002)
//
//    assert("11110000001000000000010" == 0x781002.toBinaryString)
//    assert(Right(Event(15,2,0,Team1,2)) == result)
//    assert(List(Event(15,2,0,Team1,2)) == findAll.run())
//  }
}
