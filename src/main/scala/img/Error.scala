package img

sealed trait Error
final case object ErrorParsing extends Error