package com.example.turnikas_back_end.business.team.service;

import com.example.turnikas_back_end.business.team.dto.ParticipationDTO;
import com.example.turnikas_back_end.business.team.repository.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParticipationService {

    private final ParticipationRepository participationRepository;

    @Autowired
    public ParticipationService(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    @Transactional
    public void registerTeamForTournament(ParticipationDTO participationDTO) {
        if (!participationRepository.existsByTeamIdAndTournamentId(participationDTO.getTeamId(), participationDTO.getTournamentId())) {
            participationRepository.save(participationDTO.getTeamId(), participationDTO.getTournamentId());
        } else {
            throw new IllegalArgumentException("Team is already registered for this tournament.");
        }
    }

    public List<ParticipationDTO> getAllRegisteredTeams(int tournamentId) {
        return participationRepository.findAllRegisteredTeams(tournamentId);
    }
}