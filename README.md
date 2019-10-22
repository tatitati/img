# Run tests

```
sbt test
```

# Run the tool

```
sbt run
```

# Use case

```
======== MENU =======

1: Add new event (hex)
2: Find all events
3: Find last event
4: Find last N event
q: quit

=====================


> 1
Q: Introduce Hexadecimal (examples: 781002  f0101f  f81037  1982032): 

> 781002


[Repeat process for f0101f and f81037]

======== MENU =======

1: Add new event (hex)
2: Find all events
3: Find last event
4: Find last N event
q: quit

=====================

> 2
Event(when=31 team1PointsTotal=2 team2PointsTotal=6 whoScored=Team2 pointsScored=3) // added for: f81037
Event(when=30 team1PointsTotal=2 team2PointsTotal=3 whoScored=Team2 pointsScored=3) // added for: f0101f
Event(when=15 team1PointsTotal=2 team2PointsTotal=0 whoScored=Team1 pointsScored=2) // added for: 781002


```

# Event restrictions:

1. Events cannot be inserted at any moment. Any new event must be after (in time) the last inserted event
2. None event can be added if the scored point is zero
3. None team can be set randomly at a total points without receiving these events before (they cannot cheat).
4. The total scored points of a new event must be updated properly based on the new scores + scores in the last event
5. When received an event, the total of them team that didn't scored must keep the same amount
6. None event can be added for "zero" seconds




