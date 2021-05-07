package com.launchacademy.teamrosterwithspring.controllers;

import com.launchacademy.teamrosterwithspring.models.League;
import com.launchacademy.teamrosterwithspring.models.Team;
import com.launchacademy.teamrosterwithspring.services.TeamRosterService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;


@Controller
@RequestMapping("/")
public class TeamRostersController {
  TeamRosterService service;

 @Autowired
  public TeamRostersController(TeamRosterService service) {
      this.service = service;
     }

  @GetMapping
  public String getTeams(Model model) {
    League league = League.getLeague();
    model.addAttribute("teams", league.getTeams());
    return "teamRosters/index";
  }

  @GetMapping("/teams/{teamId}")
  public String showTeams(@PathVariable Integer teamId, Model model) {
    League league = League.getLeague();
    if(teamId >= league.getTeams().size() || teamId < 0) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    } else {
      Team team = league.getTeams().get(teamId);
      model.addAttribute("team", team);
      return "teamRosters/show";
    }
  }

  @GetMapping("fantasy/teams/new")
  public String getNewForm(@ModelAttribute Team team) {
    return "teamRosters/new";
  }

  @PostMapping
  public String createTeam(@ModelAttribute Team team) {
    service.addToList(team);
    return "teamRosters/show";
  }

  @GetMapping("/fantasy/teams")
  public String getFantasyTeam(Model model) {
    List<Team> fantasyTeams = service.findAll();
    model.addAttribute("teams", fantasyTeams);
    System.out.println("Test output: " + fantasyTeams);
    return "fantasy/index";
  }
}
