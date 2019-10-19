package img

import eu.timepit.refined.W
import eu.timepit.refined.boolean.{And, Not}
import eu.timepit.refined.numeric.{Greater, Less, Positive}
import eu.timepit.refined.auto._
import eu.timepit.refined.api.Refined

object Build{
  type OnSeconds = Not[Less[W.`0`.T]] And Not[Greater[W.`4095`.T]] // [0, 2^12-1]
  type TeamPointsTotal = Not[Less[W.`0`.T]] And Not[Greater[W.`255`.T]] // [0, 2^8-1]
  type WhoScored = Not[Less[W.`0`.T]] And Not[Greater[W.`1`.T]] // [0, 1]
  type PointsScored = Not[Less[W.`1`.T]] And Not[Greater[W.`3`.T]] // [1, 3]

  case class Update(
                  matchtime: Int Refined OnSeconds,
                  team1PointsTotal: Int Refined TeamPointsTotal,
                  team2PointsTotal: Int Refined TeamPointsTotal,
                  whoScored: Int Refined WhoScored,
                  pointsScored: Int Refined PointsScored
  )

  def any(): Update = {
    Update(0, 0, 0, 0, 0)
  }

}


