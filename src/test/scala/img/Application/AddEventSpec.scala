package img.Application

import img.Domain.{Event, Team1}
import img.Infrastructure.RepositoryEvents
import org.scalatest.FunSuite

class AddEventSpec extends FunSuite {
  val repo = new RepositoryEvents
  // im using a memory cache repo, so I share the same for both
  // in a production code this shouldnt be the case as not state should be kept in the repo.
  val addEvent = new AddEvent(repo)
  val findAll = new FindAllEvents(repo)

  test("I can split in a recursive way"){
    val result1 = addEvent.splitBinaryStream("xxxxxxxxxxxddddddddccccccccbaa")
    val result2 = addEvent.splitBinaryStream("xxxxxxxxxxxxxxxxxxxxxxxxddddddddccccccccbaa")

    assert(Right(List("aa", "b", "cccccccc", "dddddddd", "xxxxxxxxxxx")) == result1)
    assert(Right(List("aa", "b", "cccccccc", "dddddddd", "xxxxxxxxxxxxxxxxxxxxxxxx")) == result2)
  }

  test("Edge case"){
    val result1 = addEvent.splitBinaryStream("aa")

    assert(Left(ErrorParsing) == result1)
  }

  test("On time 0 is not a valid instant") {
    assert(false == addEvent.validateBinaryChunks(List("11", "1", "00000000", "11111111", "000000000000")))
  }

  test("Zero points scored event are not valid") {
    assert(false == addEvent.validateBinaryChunks(List("00", "1", "00000000", "11111111", "000000000001")))
  }

  test("I cannot have an event triggered by a goal, but not being updated anything for any team"){
    assert(false == addEvent.validateBinaryChunks(List("01", "1", "00000000", "00000000", "000000000001")))
  }

  test("I can define a valid value") {
    assert(true == addEvent.validateBinaryChunks(List("01", "1", "00000000", "11111111", "000000000001")))
  }

  test("I cannot add wrong streams") {
    val result = addEvent.run(0x001002)

    assert("1000000000010" == 0x001002.toBinaryString)
    assert(Left(ErrorParsing) == result)
    assert(List() == findAll.run())
  }

  test("I can add correct events") {
    val result = addEvent.run(0x781002)

    assert("11110000001000000000010" == 0x781002.toBinaryString)
    assert(Right(Event(15,2,0,Team1,2)) == result)
    assert(List(Event(15,2,0,Team1,2)) == findAll.run())
  }
}
