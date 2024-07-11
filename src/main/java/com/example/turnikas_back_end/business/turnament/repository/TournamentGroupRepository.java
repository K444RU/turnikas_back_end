package com.example.turnikas_back_end.business.turnament.repository;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static org.jooq.generated.Tables.TOURNAMENT_GROUP;

@Repository
public class TournamentGroupRepository {

    private final DSLContext jooq;

    public TournamentGroupRepository(DSLContext jooq) {
        this.jooq = jooq;
    }

    public void save(int tournamentId, int teamId, String groupName) {
        jooq.insertInto(TOURNAMENT_GROUP)
                .set(TOURNAMENT_GROUP.TOURNAMENT_ID, tournamentId)
                .set(TOURNAMENT_GROUP.TEAM_ID, teamId)
                .set(TOURNAMENT_GROUP.NAME, groupName)
                .execute();
    }
}
