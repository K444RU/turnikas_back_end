package com.example.turnikas_back_end.business.team.repository;

import com.example.turnikas_back_end.business.team.dto.ParticipationDTO;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.jooq.generated.Tables.PARTICIPATION;

@Repository
public class ParticipationRepository {

    private final DSLContext jooq;

    @Autowired
    public ParticipationRepository(DSLContext jooq) {
        this.jooq = jooq;
    }


    public boolean existsByTeamIdAndTournamentId(int teamId, int tournamentId) {
        return jooq.fetchExists(
                jooq.selectFrom(PARTICIPATION)
                        .where(PARTICIPATION.TEAM_ID.eq(teamId))
                        .and(PARTICIPATION.TOURNAMENT_ID.eq(tournamentId))
        );
    }

    public void save(int teamId, int tournamentId) {
        jooq.insertInto(PARTICIPATION)
                .set(PARTICIPATION.TEAM_ID, teamId)
                .set(PARTICIPATION.TOURNAMENT_ID, tournamentId)
                .execute();
    }

    public List<ParticipationDTO> findAllRegisteredTeams(int tournamentId) {
        return jooq
                .selectFrom(PARTICIPATION)
                .where(PARTICIPATION.TOURNAMENT_ID.eq(tournamentId))
                .fetchInto(ParticipationDTO.class);
    }
}
