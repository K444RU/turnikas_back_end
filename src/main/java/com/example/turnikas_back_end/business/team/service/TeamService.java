package com.example.turnikas_back_end.business.team.service;

import com.example.turnikas_back_end.business.team.dto.TeamDTO;
import com.example.turnikas_back_end.business.team.dto.TeamPlayerDTO;
import com.example.turnikas_back_end.business.team.model.Category;
import com.example.turnikas_back_end.business.team.model.Stats;
import com.example.turnikas_back_end.business.team.model.Team;
import com.example.turnikas_back_end.business.team.model.TeamPlayer;
import com.example.turnikas_back_end.business.team.repository.TeamRepository;
import com.example.turnikas_back_end.business.team.request.TeamPlayerRegistration;
import com.example.turnikas_back_end.business.team.request.TeamRegistration;
import com.example.turnikas_back_end.business.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    TeamRepository teamRepository;
    UserRepository userRepository;


    @Autowired
    public TeamService(TeamRepository teamRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    public TeamDTO registerTeam(TeamRegistration teamRegistration) {
        Integer teamId = teamRepository.add(teamRegistration);
        teamRegistration.setId(teamId);
        return new TeamDTO(teamId);
    }
    //ToDo: create a Team Player registration
    //ToDo: each Team Player has to be with the same related TeamId
    public TeamPlayerDTO registerTeamPlayer(TeamPlayerRegistration teamPlayerRegistration) {
        Integer teamPlayerId = teamRepository.add2(teamPlayerRegistration);
        teamPlayerRegistration.setId(teamPlayerId);
        return new TeamPlayerDTO(teamPlayerId);
    }

    @SuppressWarnings("unchecked")
    public List<TeamDTO> getAllTeams() {
        return (List<TeamDTO>) teamRepository.findAll();
    }

    public List<Team> getTeamInformationByUserId(int userId) {
        return teamRepository.getTeamInformationByUserId(userId);
    }

    public List<Team> getTeamInformationByTeamId(int teamId) {
        return teamRepository.getTeamInformationByTeamId(teamId);
    }

    public Team updateTeamInformation(int teamId, Team updatedTeam) {
        return teamRepository.updateTeamInformation(teamId, updatedTeam);
    }

    public Team deleteByTeamId(int teamId) {
        return teamRepository.deleteByTeamId(teamId);
    }

    public List<Category> getCategoryNameById(int categoryCode) {
        return teamRepository.getCategoryNameById(categoryCode);
    }

    @SuppressWarnings("unchecked")
    public List<Category> getAllCategories() {
        return (List<Category>) teamRepository.findAllCategories();
    }

    public List<Team> getTeamsByCategoryCode(int categoryCode, int userId) {
        return teamRepository.findAllTeamsByCategoryCode(categoryCode, userId);
    }

    public ResponseEntity<Stats> getStatsByTeamId(int teamId) {
        return teamRepository.getStatsByTeamId(teamId);
    }

    public List<TeamPlayer> getTeamPlayerInformationByTeamId(int teamId) {
        return teamRepository.getTeamPlayerInformationByTeamId(teamId);
    }

    public TeamPlayer getTeamPlayerInformationByPlayerId(int playerId) {
        return teamRepository.getTeamPlayerInformationByPlayerId(playerId);
    }


//    public Team updateTeamStatus(int teamId) {
//        Team team = teamRepository.findTeamById(teamId);
//        if (team != null) {
//            String currentStatus = team.getTeamStatus();
//
//            if ("Active".equals(currentStatus)) {
//                team.setTeamStatus("Inactive");
//            } else if ("Inactive".equals(currentStatus)) {
//                team.setTeamStatus("Active");
//            }
//
//            return teamRepository.update(team);
//        }
//        return null;
//    }
}
