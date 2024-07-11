package com.example.turnikas_back_end.business.team.service;

import com.example.turnikas_back_end.business.turnament.dto.ParticipationDTO;
import com.example.turnikas_back_end.business.team.model.Team;
import com.example.turnikas_back_end.business.turnament.repository.ParticipationRepository;
import com.example.turnikas_back_end.business.turnament.repository.TeamTournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParticipationService {

    private final ParticipationRepository participationRepository;
    private final TeamTournamentRepository tournamentRepository;

    @Autowired
    public ParticipationService(ParticipationRepository participationRepository, TeamTournamentRepository tournamentRepository) {
        this.participationRepository = participationRepository;
        this.tournamentRepository = tournamentRepository;
    }

    @Transactional
    public void registerTeamForTournament(ParticipationDTO participationDTO) {
        if (!participationRepository.existsByTeamIdAndTournamentId(participationDTO.getTeamId(), participationDTO.getTournamentId())) {
            participationRepository.save(participationDTO.getTeamId(), participationDTO.getTournamentId());
        } else {
            throw new IllegalArgumentException("Team is already registered for this tournament.");
        }
    }

    public List<Team> getAllRegisteredTeams(int tournamentId) {
        return participationRepository.findAllRegisteredTeams(tournamentId);
    }

    public int getCategoryCodeByTournamentId(int tournamentId) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new IllegalArgumentException("Tournament not found"))
                .getCategoryCode();
    }

    public List<Team> getEligibleTeamsForTournament(int userId, int categoryCode) {
        return participationRepository.findTeamsByUserIdAndCategoryCode(userId, categoryCode);
    }
}
