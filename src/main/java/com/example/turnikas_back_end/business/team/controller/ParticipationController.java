package com.example.turnikas_back_end.business.team.controller;

import com.example.turnikas_back_end.business.team.dto.ParticipationDTO;
import com.example.turnikas_back_end.business.team.model.Team;
import com.example.turnikas_back_end.business.team.service.ParticipationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participation")
public class ParticipationController {

    private final ParticipationService participationService;

    @Autowired
    public ParticipationController(ParticipationService participationService) {
        this.participationService = participationService;
    }

    @PostMapping
    @Operation(summary = "Register team for a tournament")
    public void registerTeamForTournament(@RequestBody ParticipationDTO participationDTO) {
        participationService.registerTeamForTournament(participationDTO);
    }

    @GetMapping("/tournament/{tournamentId}/teams")
    @Operation(summary = "Get all the teams that are registered to the tournament by tournament id")
    public List<Team> getAllRegisteredTeams(@PathVariable int tournamentId) {
        return participationService.getAllRegisteredTeams(tournamentId);
    }

    @GetMapping("/tournament/{tournamentId}/eligible-teams")
    @Operation(summary = "Get eligible teams for tournament registration by tournament id")
    public List<Team> getEligibleTeamsForTournamentRegistration(@PathVariable int tournamentId, @RequestParam int userid) {
        int categoryCode = participationService.getCategoryCodeByTournamentId(tournamentId);
        return participationService.getEligibleTeamsForTournament(userid, categoryCode);
    }
}
