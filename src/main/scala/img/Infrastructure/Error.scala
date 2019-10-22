package img.Infrastructure

sealed trait Error
final case object ErrorParsing extends Error