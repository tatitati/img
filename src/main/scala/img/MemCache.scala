package img

class MemCache {
  private var cache: List[EventTeam] = List(
    EventTeam()
      .onSecond(1)
      .withNewScore(TeamA, 1)
      .withTeamScores(1, 0),
    EventTeam()
      .onSecond(1)
      .withNewScore(TeamA, 1)
      .withTeamScores(1, 0),
    EventTeam()
      .onSecond(1)
      .withNewScore(TeamA, 1)
      .withTeamScores(1, 0),
    EventTeam()
      .onSecond(1)
      .withNewScore(TeamA, 1)
      .withTeamScores(1, 0),
    EventTeam()
      .onSecond(1)
      .withNewScore(TeamA, 1)
      .withTeamScores(1, 0),
  )
}
