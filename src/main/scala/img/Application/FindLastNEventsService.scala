package img.Application

import img.Domain.Event
import img.Infrastructure.RepositoryEvents

class FindLastNEventsService(repositoryEvents: RepositoryEvents){

  def run(n: Int): List[Event] = {
    repositoryEvents.findLastNEvents(n)
  }
}
