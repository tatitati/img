package img.Application

sealed trait Error
case object ErrorInvalidEvent extends Error