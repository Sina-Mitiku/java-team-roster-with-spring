package com.launchacademy.teamrosterwithspring.services;


import com.launchacademy.teamrosterwithspring.models.Team;
import java.util.List;

public interface TeamRosterService {
  List<Team> findAll();
  void addToList(Team team);
}
