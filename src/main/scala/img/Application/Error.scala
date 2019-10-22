package img.Application

sealed trait Error
case object ErrorInvalidBinaryStream extends Error
case object ErrorInvalidEvent extends Error