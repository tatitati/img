package img.Domain

case class Event(
  when: Int,
  team1PointsTotal: Int,
  team2PointsTotal: Int,
  whoScored: Team,
  pointsScored: Int
)

