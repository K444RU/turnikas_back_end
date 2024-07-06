package com.example.turnikas_back_end.business.team.controller;

import com.example.turnikas_back_end.business.team.dto.ParticipationDTO;
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

    @GetMapping("/registered/teams")
    @Operation(summary = "Get all the teams that are registered to the tournament by tournament id")
    public List<ParticipationDTO> getAllRegisteredTeams(@RequestParam int tournamentId) {
        return participationService.getAllRegisteredTeams(tournamentId);
    }
}
