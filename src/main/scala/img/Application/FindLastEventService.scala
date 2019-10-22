package img.Application

import img.Domain.Event
import img.Infrastructure.RepositoryEvents

class FindLastEventService(repositoryEvent: RepositoryEvents) {

  def run(): Option[Event] = {
    repositoryEvent.findLastEvent()
  }
}
