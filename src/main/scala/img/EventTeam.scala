package img

object EventTeam{
  def apply(): EventTeam = {
    EventTeam(0, 0, 0, TeamA, 0)
  }
}
case class EventTeam(
                     matchtime: Int,
                     team1PointsTotal: Int,
                     team2PointsTotal: Int,
                     whoScored: Team,
                     pointsScored: Int
 ) {

  def withTeamScores(teamA: Int, teamB: Int): EventTeam = {
    (teamA < 255 && teamA >= 0 && teamB < 255 && teamB >= 0) match  {
      case true => this.copy(team1PointsTotal = teamA, team2PointsTotal = teamB)
      case false => throw new Exception("teams scores total out of range")
    }
  }

  def onSecond(seconds: Int): EventTeam = {
    (seconds < 4095 && seconds >= 0) match  {
      case true => this.copy(matchtime = seconds)
      case false => throw new Exception("seconds out of range")
    }
  }

  def withNewScore(scoredBy: Team, points: Int): EventTeam = {
    (points >= 1 || points <= 3) match  {
      case true => this.copy(pointsScored = points, whoScored = scoredBy)
      case false => throw new Exception("scored by out of range")
    }
  }

  def printBinary(): Unit = {
      val binaryMatchTime = String.format("%0" + 12 + "d", matchtime.toBinaryString.toInt.asInstanceOf[Object])
      val binaryTeam1PointsTotal = String.format("%0" + 8 + "d", team1PointsTotal.toBinaryString.toInt.asInstanceOf[Object])
      val binaryTeam2PointsTotal = String.format("%0" + 8 + "d", team2PointsTotal.toBinaryString.toInt.asInstanceOf[Object])
      val binaryPointsScored = String.format("%0" + 2 + "d", pointsScored.toBinaryString.toInt.asInstanceOf[Object])

    val binary =
      s"""
         |0 ${binaryMatchTime} ${binaryTeam1PointsTotal} ${binaryTeam2PointsTotal} ${whoScored} ${binaryPointsScored}""".stripMargin
    println(binary)
  }
}


