package com.example.turnikas_back_end.business.turnament.repository;

import com.example.turnikas_back_end.business.team.dto.TeamDTO;
import com.example.turnikas_back_end.business.turnament.dto.TournamentGroupDTO;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.jooq.generated.Tables.TEAM;
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

    public boolean existsByTournamentId(int tournamentId) {
        return jooq.fetchExists(
                jooq.selectFrom(TOURNAMENT_GROUP)
                        .where(TOURNAMENT_GROUP.TOURNAMENT_ID.eq(tournamentId))
        );
    }

    public List<TournamentGroupDTO> findGroupsByTournamentId(int tournamentId) {
        List<Record> records = jooq.select()
                .from(TOURNAMENT_GROUP)
                .join(TEAM).on(TOURNAMENT_GROUP.TEAM_ID.eq(TEAM.ID))
                .where(TOURNAMENT_GROUP.TOURNAMENT_ID.eq(tournamentId))
                .fetch();

        Map<String, List<TeamDTO>> groupsMap = records.stream()
                .collect(Collectors.groupingBy(
                        record -> record.get(TOURNAMENT_GROUP.NAME),
                        Collectors.mapping(record -> new TeamDTO(
                                record.get(TEAM.ID),
                                record.get(TEAM.USER_ID),
                                record.get(TEAM.CATEGORY_CODE),
                                record.get(TEAM.STATS_ID),
                                record.get(TEAM.TEAM_NAME),
                                record.get(TEAM.TEAM_LOGO),
                                record.get(TEAM.TEAM_COACH_NAME),
                                record.get(TEAM.ROLE_CODE)
                        ), Collectors.toList())
                ));

        return groupsMap.entrySet().stream()
                .map(entry -> new TournamentGroupDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
