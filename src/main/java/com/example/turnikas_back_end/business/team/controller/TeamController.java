package com.example.turnikas_back_end.business.team.controller;

import com.example.turnikas_back_end.business.team.dto.TeamDTO;
import com.example.turnikas_back_end.business.team.dto.TeamPlayerDTO;
import com.example.turnikas_back_end.business.team.model.*;
import com.example.turnikas_back_end.business.team.request.TeamPlayerRegistration;
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

    @PostMapping("/player/register")
    @Operation(summary = "Post team player information(new team player registration)")
    public TeamPlayerDTO registerTeamPlayer(@RequestBody TeamPlayerRegistration teamPlayerRegistration){
        return teamService.registerTeamPlayer(teamPlayerRegistration);
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

    @GetMapping("/profile/info")
    @Operation(summary = "Get team information by Team Id")
    public List<Team> getTeamByTeamId(@RequestParam int teamId) {
        return teamService.getTeamInformationByTeamId(teamId);
    }

    @GetMapping("/players/info")
    @Operation(summary = "Get team players info by team Id")
    public List<TeamPlayer> getTeamPlayerInformationByTeamId(@RequestParam int teamId){
        return teamService.getTeamPlayerInformationByTeamId(teamId);
    }

    @GetMapping("/player/info")
    @Operation(summary = "Get team player info by its player Id")
    public TeamPlayer getTeamPlayerInformationByPlayerId(@RequestParam int playerId){
        return teamService.getTeamPlayerInformationByPlayerId(playerId);
    }

    @GetMapping("/age/category")
    @Operation(summary = "Get age category by categoryCode")
    public List<AgeCategory> getCategoryNameById(@RequestParam int categoryCode) {
        return teamService.getCategoryNameById(categoryCode);
    }

    @GetMapping("/all/age/category")
    @Operation(summary = "Get all age categories")
    public List<AgeCategory> getAllAgeCategories() {
        return teamService.getAllCategories();
    }

    @GetMapping("/category/filter")
    @Operation(summary = "Filter the team table by its categoryCode and userId")
    public List<Team> getTeamsByCategoryCode(@RequestParam int categoryCode, @RequestParam int userId) {
        return teamService.getTeamsByCategoryCode(categoryCode, userId);
    }

    @GetMapping("/role/filter")
    @Operation(summary = "Filter the team table by its roleCode and userId")
    public List<Team> getTeamsByRoleCode(@RequestParam int roleCode, @RequestParam int userId){
        return teamService.getTeamsByRoleCode(roleCode, userId);
    }

    @GetMapping("/role")
    @Operation(summary = "Get all team roles")
    public List<Role> getAllTeamRoles(){
        return teamService.getAllTeamRoles();
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
