package img.Application

import img.Domain.Event
import img.Infrastructure.RepositoryEvents

class ServiceAddEvent(repositoryEvents: RepositoryEvents) {
  type ErrorOrEvent = Either[ErrorInvalidEvent.type, Event]

  def run(newEvent: Event): ErrorOrEvent = {
    ValidatorEvent.isValid(newEvent, repositoryEvents.findLastEvent()) match {
      case true => Right{
          repositoryEvents.addEvent(newEvent)
          newEvent
      }
      case false => Left(ErrorInvalidEvent)
    }
  }
}
