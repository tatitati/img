package img.Application

import img.Domain.Event
import img.Infrastructure.RepositoryEvents

class AddEventService(repositoryEvents: RepositoryEvents) {

  type ErrorOrEvent = Either[Error, Event]

  def run(newEvent: Event): ErrorOrEvent = {
    ValidatorEvent.isValid(newEvent, repositoryEvents.findLastEvent()) match {
      case true =>
        repositoryEvents.addEvent(newEvent)
        Right(newEvent)
      case false => Left(ErrorInvalidEvent)
    }
  }
}
