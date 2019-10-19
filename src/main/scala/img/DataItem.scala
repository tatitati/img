package img

import eu.timepit.refined.W
import eu.timepit.refined.boolean.{And, Not}
import eu.timepit.refined.numeric.{Greater, Less, Positive}
import eu.timepit.refined.auto._
import eu.timepit.refined.api.Refined

object DataItem{
  def apply(): DataItem = {
    DataItem(0, 0, 0, 0, 0)
  }
}


case class DataItem(
   matchtime: Int,
   team1PointsTotal: Int,
   team2PointsTotal: Int,
   whoScored: Int,
   pointsScored: Int
 ) {

  def withSeconds(seconds: Int): DataItem = {
    (seconds < 4095 && seconds >= 0) match  {
      case true => this.copy(matchtime = seconds)
      case false => throw new Exception("seconds out of range")
    }
  }
  def withTeam1PointsTotal(total: Int): DataItem = {
    (total < 255 && total >= 0) match  {
      case true => this.copy(team1PointsTotal = total)
      case false => throw new Exception("team1 point total out of range")
    }
  }
  def withTeam2PointsTotal(total: Int): DataItem = {
    (total < 255 && total >= 0) match  {
      case true => this.copy(team2PointsTotal = total)
      case false => throw new Exception("team2 point total out of range")
    }
  }
  def withScored(scoredBy: Int): DataItem = {
    (scoredBy == 1 || scoredBy == 0) match  {
      case true => this.copy(whoScored = scoredBy)
      case false => throw new Exception("scored by out of range")
    }
  }
  def withPointsScored(points: Int): DataItem = {
    (points >= 1 || points <= 3) match  {
      case true => this.copy(pointsScored = points)
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
         |0 ${binaryMatchTime} ${binaryTeam1PointsTotal} ${binaryTeam2PointsTotal} ${whoScored.toBinaryString} ${binaryPointsScored}""".stripMargin
    println(binary)
  }
}


