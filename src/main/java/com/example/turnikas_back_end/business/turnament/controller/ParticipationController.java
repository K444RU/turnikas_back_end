package com.example.turnikas_back_end.business.turnament.controller;

import com.example.turnikas_back_end.business.team.model.Team;
import com.example.turnikas_back_end.business.team.service.ParticipationService;
import com.example.turnikas_back_end.business.turnament.dto.ParticipationDTO;
import com.example.turnikas_back_end.business.turnament.service.TournamentGroupService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participation")
public class ParticipationController {

    private final ParticipationService participationService;
    private final TournamentGroupService tournamentGroupService;

    @Autowired
    public ParticipationController(ParticipationService participationService, TournamentGroupService tournamentGroupService) {
        this.participationService = participationService;
        this.tournamentGroupService = tournamentGroupService;
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
    public List<Team> getEligibleTeamsForTournamentRegistration(@PathVariable int tournamentId, @RequestParam int userId) {
        int categoryCode = participationService.getCategoryCodeByTournamentId(tournamentId);
        return participationService.getEligibleTeamsForTournament(userId, categoryCode);
    }

    @PostMapping("/tournament/{tournamentId}/groups")
    @Operation(summary = "Generate groups for a tournament")
    public ResponseEntity<?> generateGroups(@PathVariable int tournamentId) {
        try {
            List<List<Team>> groups = tournamentGroupService.generateGroups(tournamentId);
            return ResponseEntity.ok(groups);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
