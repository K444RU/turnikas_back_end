package com.example.turnikas_back_end.business.turnament.service;

import com.example.turnikas_back_end.business.team.model.Team;
import com.example.turnikas_back_end.business.turnament.repository.ParticipationRepository;
import com.example.turnikas_back_end.business.turnament.repository.TournamentGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TournamentGroupService {

    private final ParticipationRepository participationRepository;
    private final TournamentGroupRepository tournamentGroupRepository;

    @Autowired
    public TournamentGroupService(ParticipationRepository participationRepository, TournamentGroupRepository tournamentGroupRepository) {
        this.participationRepository = participationRepository;
        this.tournamentGroupRepository = tournamentGroupRepository;
    }

    public List<List<Team>> generateGroups(int tournamentId) {
        if (tournamentGroupRepository.existsByTournamentId(tournamentId)) {
            throw new IllegalArgumentException("Groups have been already created for this tournament.");
        }

        List<Team> registeredTeams = participationRepository.findAllRegisteredTeams(tournamentId);

        Collections.shuffle(registeredTeams);

        int teamsPerGroup = 4;
        List<List<Team>> groups = new ArrayList<>();

        for (int i = 0; i < registeredTeams.size(); i += teamsPerGroup) {
            int end = Math.min(i + teamsPerGroup, registeredTeams.size());
            groups.add(new ArrayList<>(registeredTeams.subList(i, end)));
        }

        for (int i = 0; i < groups.size(); i++) {
            String groupName = String.valueOf((char) ('A' + i));
            for (Team team : groups.get(i)) {
                tournamentGroupRepository.save(tournamentId, team.getId(), groupName);
            }
        }
        return groups;
    }
}
