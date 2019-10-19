package img

import eu.timepit.refined.boolean._
import eu.timepit.refined._
import eu.timepit.refined.numeric._

object Build{
  type OnSeconds = Not[Less[W.`0`.T]] And Not[Greater[W.`4095`.T]] // [0, 2^12-1]
  type TeamPointsTotal = Not[Less[W.`0`.T]] And Not[Greater[W.`255`.T]] // [0, 2^8-1]
  type WhoScored = Not[Less[W.`0`.T]] And Not[Greater[W.`1`.T]] // [0, 1]
  type PointsScored = Not[Less[W.`1`.T]] And Not[Greater[W.`3`.T]] // [1, 3]

  case class Update(
                  matchtime: OnSeconds,
                  team1PointsTotal: TeamPointsTotal,
                  team2PointsTotal: TeamPointsTotal,
                  whoScored: WhoScored,
                  pointsScored: PointsScored
  )

  def any(): Update = {
    Update(0, 0, 0, 0, 0)
  }

}


