package com.example.turnikas_back_end.business.team.controller;

import com.example.turnikas_back_end.business.team.dto.TeamDTO;
import com.example.turnikas_back_end.business.team.model.Category;
import com.example.turnikas_back_end.business.team.model.Stats;
import com.example.turnikas_back_end.business.team.model.Team;
import com.example.turnikas_back_end.business.team.request.TeamRegistration;
import com.example.turnikas_back_end.business.team.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/register")
    @Operation(summary = "Post team information (new team register)")
    public TeamDTO registerTeam(@RequestBody TeamRegistration teamRegistration) {
        return teamService.registerTeam(teamRegistration);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all the teams")
    public List<TeamDTO> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/info")
    @Operation(summary = "Get team information by User ID")
    public List<Team> getTeamByUserId(@RequestParam int userId) {
        return teamService.getTeamInformationByUserId(userId);
    }

    @GetMapping("/age/category")
    @Operation(summary = "Get age category by categoryCode")
    public List<Category> getCategoryNameById(@RequestParam int categoryCode) {
        return teamService.getCategoryNameById(categoryCode);
    }

    @GetMapping("/all/age/category")
    @Operation(summary = "Get all age categories")
    public List<Category> getAllAgeCategories() {
        return teamService.getAllCategories();
    }

    @GetMapping("/category/filter")
    @Operation(summary = "Filter the team table by its categoryCode and userId")
    public List<Team> getTeamsByCategoryCode(@RequestParam int categoryCode, @RequestParam int userId) {
        return teamService.getTeamsByCategoryCode(categoryCode, userId);
    }

    @GetMapping("/stats")
    @Operation(summary = "Get team stats by teamId")
    public ResponseEntity<Stats> getStatsByTeamId(@RequestParam int teamId) {
        return teamService.getStatsByTeamId(teamId);
    }

    @PutMapping("/update/{teamId}")
    @Operation(summary = "Update team information")
    public Team updateTeamInformation(@PathVariable int teamId, @RequestBody Team updatedTeam) {
        return teamService.updateTeamInformation(teamId, updatedTeam);
    }

//    @PutMapping("/update/status")
//    @Operation(summary = "Update team status Active or Inactive")
//    public Team updateTeamStatus(@RequestParam int teamId){
//        return teamService.updateTeamStatus(teamId);
//    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete the team by teamId")
    public Team deleteByTeamId(@RequestParam int teamId){
        return teamService.deleteByTeamId(teamId);
    }

}