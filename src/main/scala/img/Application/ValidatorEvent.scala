package img.Application

import img.Domain.{Event, Team1, Team2}

object ValidatorEvent {
  def isValid(newevent: Event, lastEvent: Option[Event]): Boolean = {
    isNewEventAfterLastEvent(newevent, lastEvent) &&
    goalsAreMovingForward(newevent, lastEvent) &&
    teamNotScoringRemainTheSame(newevent, lastEvent)
  }

  def isNewEventAfterLastEvent(newevent: Event, lastEvent: Option[Event]): Boolean = {
    lastEvent match {
      case Some(last) => last.when < newevent.when
      case None => true
    }
  }

  def goalsAreMovingForward(newevent: Event, lastEvent: Option[Event]): Boolean = {
    lastEvent match {
      case Some(last) if newevent.whoScored == Team1 => newevent.team1PointsTotal == last.team1PointsTotal && newevent.team1PointsTotal == last.team1PointsTotal + newevent.pointsScored
      case Some(last) if newevent.whoScored == Team2 => newevent.team2PointsTotal > last.team2PointsTotal && newevent.team2PointsTotal == last.team2PointsTotal + newevent.pointsScored
      case None if newevent.whoScored == Team1 => newevent.team1PointsTotal == newevent.pointsScored && newevent.team1PointsTotal == newevent.pointsScored
      case None if newevent.whoScored == Team2 => newevent.team2PointsTotal == newevent.pointsScored && newevent.team2PointsTotal == newevent.pointsScored
    }
  }

  def teamNotScoringRemainTheSame(newevent: Event, lastEvent: Option[Event]): Boolean = {
    lastEvent match {
      case Some(last) if newevent.whoScored == Team1 => newevent.team2PointsTotal == last.team2PointsTotal
      case Some(last) if newevent.whoScored == Team2 => newevent.team1PointsTotal == last.team1PointsTotal
      case None if newevent.whoScored == Team1 => newevent.team2PointsTotal == 0
      case None if newevent.whoScored == Team2 => newevent.team1PointsTotal == 0
    }
  }
}
