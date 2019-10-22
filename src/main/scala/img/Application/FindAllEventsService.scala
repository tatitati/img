package img.Application

import img.Domain.Event
import img.Infrastructure.RepositoryEvents

class FindAllEventsService(repository: RepositoryEvents) {

  def run(): List[Event] = {
    repository.findAllEvents()
  }
}
