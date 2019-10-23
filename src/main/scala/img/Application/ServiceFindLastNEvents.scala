package img.Application

import img.Domain.Event
import img.Infrastructure.RepositoryEvents

class ServiceFindLastNEvents(repositoryEvents: RepositoryEvents){

  def run(n: Int): List[Event] = {
    repositoryEvents.findLastNEvents(n)
  }
}
