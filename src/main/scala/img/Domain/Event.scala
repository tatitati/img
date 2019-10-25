package img.Domain

case class Event(when: Int, team1PointsTotal: Int, team2PointsTotal: Int, whoScored: Team, pointsScored: Int) {
  override def toString(): String = {
    "Event(when=" + when + " team1PointsTotal=" + team1PointsTotal + " team2PointsTotal=" + team2PointsTotal + " whoScored=" + whoScored + " pointsScored="+pointsScored + ")"
  }
}