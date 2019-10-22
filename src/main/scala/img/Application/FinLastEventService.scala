package img.Application

import img.Domain.Event
import img.Infrastructure.RepositoryEvents

class FinLastEventService(repositoryEvent: RepositoryEvents) {

  def run(): Option[Event] = {
    repositoryEvent.findLastEvent()
  }
}
