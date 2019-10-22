package img.Application

sealed trait Error
case object ErrorParsing extends Error