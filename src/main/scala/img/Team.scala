package img

sealed trait Team {
  def toBinary: String
  def toChar: String
}
final case object TeamA extends Team {
  def toBinary(): String = {
    s"0"
  }

  def toChar(): String = {
    s"A"
  }
}
final case object TeamB extends Team {
  def toBinary(): String = {
    s"1"
  }

  def toChar(): String = {
    s"B"
  }
}
