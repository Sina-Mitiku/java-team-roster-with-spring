package com.launchacademy.teamrosterwithspring.services;


import com.launchacademy.teamrosterwithspring.models.League;
import com.launchacademy.teamrosterwithspring.models.Team;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class TeamRosterSessionBasedService implements TeamRosterService {
  League fantasyLeague;

  public TeamRosterSessionBasedService() {
    fantasyLeague = new League();

  }
  public List<Team> findAll() {
    return fantasyLeague.getTeams();
  }

  public void addToList(Team team) {
    fantasyLeague.addTeam(team);
  }

}
