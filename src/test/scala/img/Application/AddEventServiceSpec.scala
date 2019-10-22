package img.Application

import img.Domain.{Event, Team1, Team2}
import img.Infrastructure.RepositoryEvents
import org.scalatest.{BeforeAndAfterEach, FunSuite}

class AddEventServiceSpec extends FunSuite with BeforeAndAfterEach {

  var repo = new RepositoryEvents
  var addEvent = new AddEventService(repo)
  var findAll = new FindAllEventsService(repo)

  override def beforeEach(): Unit ={
    // I'm using an in-memory cache, so I have to reset it creating a new inmemory repository
    // usually this is not needed, but for this case make the job
    repo = new RepositoryEvents
    addEvent = new AddEventService(repo)
    findAll = new FindAllEventsService(repo)
  }

  test("I can only add correct events") {
    val event = Event(when=2, team1PointsTotal=3, team2PointsTotal=0, whoScored = Team1, pointsScored = 3)
    val result = addEvent.run(event)

    assert(Right(Event(2,3,0,Team1,3)) == result)
    assert(List(Event(2,3,0,Team1,3)) == findAll.run())
  }

  test("I cannot add invalid events: Team 1 scored the first, however scored of team 2 is not zero") {
    val event = Event(when=2, team1PointsTotal=3, team2PointsTotal=1, whoScored = Team1, pointsScored = 3)
    val result = addEvent.run(event)

    assert(Left(ErrorInvalidEvent) == result)
    assert(List() == findAll.run())
  }

  test("I can add multiple events if they make sense, one by one") {
    val event1 = Event(when=2, team1PointsTotal=3, team2PointsTotal=0, whoScored = Team1, pointsScored = 3)
    val event2 = Event(when=3, team1PointsTotal=3, team2PointsTotal=2, whoScored = Team2, pointsScored = 2)
    val event3 = Event(when=4, team1PointsTotal=5, team2PointsTotal=2, whoScored = Team1, pointsScored = 2)

    addEvent.run(event1)
    addEvent.run(event2)
    addEvent.run(event3)

    assert(List(
      Event(4,5,2,Team1,2),
      Event(3,3,2,Team2,2),
      Event(2,3,0,Team1,3)
    ) == findAll.run())
  }
}
