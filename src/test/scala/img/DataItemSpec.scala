package img

import org.scalatest.FunSuite

class DataItemSpec extends FunSuite {
  test("I can create data items"){
    val dataItem1 = DataItem()
      .withSeconds(14)
      .withTeam1PointsTotal(7)

    dataItem1.printBinary()

  }
}
