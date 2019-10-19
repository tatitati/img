package img

sealed trait Team
final case object TeamA extends Team {
  override def toString(): String = {
    s"0"
  }
}
final case object TeamB extends Team {
  override def toString(): String = {
    s"1"
  }
}
