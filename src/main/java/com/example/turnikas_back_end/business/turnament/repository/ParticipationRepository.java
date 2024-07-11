package com.example.turnikas_back_end.business.turnament.repository;

import com.example.turnikas_back_end.business.team.model.Team;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.jooq.generated.Tables.PARTICIPATION;
import static org.jooq.generated.Tables.TEAM;

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

    public List<Team> findAllRegisteredTeams(int tournamentId) {
        return jooq.select(TEAM.fields())
                .from(PARTICIPATION)
                .join(TEAM).on(PARTICIPATION.TEAM_ID.eq(TEAM.ID))
                .where(PARTICIPATION.TOURNAMENT_ID.eq(tournamentId))
                .fetchInto(Team.class);
    }

    public List<Team> findTeamsByUserIdAndCategoryCode(int userId, int categoryCode) {
        return jooq.selectFrom(TEAM)
                .where(TEAM.USER_ID.eq(userId))
                .and(TEAM.CATEGORY_CODE.eq(categoryCode))
                .fetchInto(Team.class);
    }
}
