package com.example.turnikas_back_end.business.team.repository;

import org.jooq.DSLContext;
import org.jooq.generated.tables.records.TournamentRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.jooq.generated.Tables.TOURNAMENT;

@Repository("teamTournamentRepository")
public class TournamentRepository {

    private final DSLContext jooq;

    @Autowired
    public TournamentRepository(DSLContext jooq) {
        this.jooq = jooq;
    }

    public Optional<TournamentRecord> findById(int tournamentId) {
        return Optional.ofNullable(jooq.selectFrom(TOURNAMENT)
                .where(TOURNAMENT.ID.eq(tournamentId))
                .fetchOne());
    }
}
