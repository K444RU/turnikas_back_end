package com.example.turnikas_back_end.business.team.repository;

import com.example.turnikas_back_end.business.common.repository.TurnikasRepository;
import com.example.turnikas_back_end.business.team.dto.TeamDTO;
import com.example.turnikas_back_end.business.team.model.*;
import com.example.turnikas_back_end.business.team.request.TeamPlayerRegistration;
import com.example.turnikas_back_end.business.team.request.TeamRegistration;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.StatsRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import static org.jooq.generated.Tables.ROLE;
import static org.jooq.generated.tables.AgeCategory.AGE_CATEGORY;
import static org.jooq.generated.tables.Stats.STATS;
import static org.jooq.generated.tables.Team.TEAM;
import static org.jooq.generated.tables.TeamPlayer.TEAM_PLAYER;

@Repository
public class TeamRepository implements TurnikasRepository {

    private final DSLContext jooq;

    @Autowired
    public TeamRepository(DSLContext jooq) {
        this.jooq = jooq;
    }

    public Integer createDefaultTeam(Integer userId, LocalDate userDateOfBirth) {

        Integer statsId = defaultTeamStats();
        Integer categoryCode = calculateCategoryCode(userDateOfBirth);

        return jooq.insertInto(TEAM,
                        TEAM.USER_ID,
                        TEAM.STATS_ID,
                        TEAM.CATEGORY_CODE,
                        TEAM.TEAM_NAME,
                        TEAM.TEAM_LOGO,
                        TEAM.TEAM_COACH_NAME,
                        TEAM.ROLE_CODE)
                .values(userId, statsId, categoryCode, "TEAM №" + userId, null, null, 1)
                .returning(TEAM.ID,
                        TEAM.USER_ID,
                        TEAM.STATS_ID,
                        TEAM.CATEGORY_CODE,
                        TEAM.TEAM_NAME,
                        TEAM.TEAM_LOGO,
                        TEAM.TEAM_COACH_NAME,
                        TEAM.ROLE_CODE)
                .execute();
    }

    private int calculateCategoryCode(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            return -1;
        }

        LocalDate currentDate = LocalDate.now();
        int age = Period.between(dateOfBirth, currentDate).getYears();

        if (age < 7) {
            return 1;
        } else if (age <= 9) {
            return 2;
        } else if (age <= 11) {
            return 3;
        } else if (age <= 13) {
            return 4;
        } else if (age <= 15) {
            return 5;
        } else if (age <= 17) {
            return 6;
        } else {
            return 7;
        }
    }

    @Override
    @Transactional
    public Integer add(Object object) {
        TeamRegistration teamRegistration = (TeamRegistration) object;
        Integer statsId = defaultTeamStats();

        System.out.println("Team Registration Details: " + teamRegistration.toString());

        return jooq
                .insertInto(TEAM,
                        TEAM.USER_ID,
                        TEAM.CATEGORY_CODE,
                        TEAM.STATS_ID,
                        TEAM.TEAM_NAME,
                        TEAM.TEAM_LOGO,
                        TEAM.TEAM_COACH_NAME,
                        TEAM.ROLE_CODE)
                .values(teamRegistration.getUserId(),
                        teamRegistration.getCategoryCode(),
                        statsId,
                        teamRegistration.getTeamName(),
                        (teamRegistration.getTeamLogo() != null) ? teamRegistration.getTeamLogo().getBytes() : null,
                        teamRegistration.getTeamCoachName(),
                        teamRegistration.getRoleCode())
                .returning(TEAM.ID, TEAM.CATEGORY_CODE, TEAM.USER_ID, TEAM.ROLE_CODE)
                .execute();
    }

    public Integer add2(TeamPlayerRegistration teamPlayerRegistration) {
        return jooq
                .insertInto(TEAM_PLAYER,
                        TEAM_PLAYER.TEAM_ID,
                        TEAM_PLAYER.FIRST_NAME,
                        TEAM_PLAYER.LAST_NAME)
                .values(teamPlayerRegistration.getTeamId(),
                        teamPlayerRegistration.getFirstName(),
                        teamPlayerRegistration.getLastName())
                .returning(TEAM_PLAYER.ID, TEAM_PLAYER.TEAM_ID)
                .execute();
    }

    private Integer defaultTeamStats() {
        StatsRecord stats = jooq.insertInto(STATS,
                        STATS.PLAYED, STATS.WON, STATS.LOST,
                        STATS.DRAW, STATS.GOAL_FOR, STATS.GOAL_AGAINST)
                .values(0, 0, 0, 0, 0, 0)
                .returning(STATS.ID)
                .fetchOne();

        if (stats != null) {
            return stats.getId();
        }
        return null;
    }


    @Override
    public Object delete(Integer id) {
        return null;
    }

    @Override
    public List<?> findAll() {
        return jooq
                .select(TEAM.ID,
                        TEAM.USER_ID,
                        TEAM.CATEGORY_CODE,
                        TEAM.STATS_ID,
                        TEAM.TEAM_NAME,
                        TEAM.TEAM_LOGO,
                        TEAM.TEAM_COACH_NAME,
                        TEAM.ROLE_CODE)
                .from(TEAM)
                .fetchInto(TeamDTO.class);
    }

    @Override
    public Object findById(Integer id) {
        return null;
    }

    @Override
    public Object update(Object object) {
        return null;
    }

    public List<Team> getTeamInformationByUserId(int userId) {
        return jooq
                .selectFrom(TEAM)
                .where(TEAM.USER_ID.eq(userId))
                .fetchInto(Team.class);
    }

    public List<Team> getTeamInformationByTeamId(int teamId) {
        return jooq
                .selectFrom(TEAM)
                .where(TEAM.ID.eq(teamId))
                .fetchInto(Team.class);
    }


    public Team updateTeamInformation(int teamId, Team updatedTeam) {
        int updatedRows = jooq
                .update(TEAM)
                .set(TEAM.TEAM_NAME, updatedTeam.getTeamName())
                .set(TEAM.TEAM_COACH_NAME, updatedTeam.getTeamCoachName())
                .set(TEAM.CATEGORY_CODE, updatedTeam.getCategoryCode())
                .set(TEAM.TEAM_LOGO, updatedTeam.getTeamLogo())
                .where(TEAM.ID.eq(teamId))
                .execute();
        if (updatedRows > 0) {
            return updatedTeam;
        } else {
            return null;
        }
    }

    public Team deleteByTeamId(int teamId) {
        return jooq
                .delete(TEAM)
                .where(TEAM.ID.eq(teamId))
                .returning()
                .fetchOne()
                .into(Team.class);
    }

    public List<AgeCategory> getCategoryNameById(int categoryCode) {
        return jooq
                .selectFrom(AGE_CATEGORY)
                .where(AGE_CATEGORY.CATEGORY_CODE.eq(categoryCode))
                .fetchInto(AgeCategory.class);
    }

    public List<?> findAllCategories() {
        return jooq
                .select(
                        AGE_CATEGORY.CATEGORY_CODE,
                        AGE_CATEGORY.CATEGORY_NAME)
                .from(AGE_CATEGORY)
                .fetchInto(AgeCategory.class);
    }

    public List<Team> findAllTeamsByCategoryCode(int categoryCode, int userId) {
        return jooq
                .selectFrom(TEAM)
                .where(TEAM.CATEGORY_CODE.eq(categoryCode)
                        .and(TEAM.USER_ID.eq(userId)))
                .fetchInto(Team.class);
    }

    public ResponseEntity<Stats> getStatsByTeamId(int teamId) {
        Stats stats = jooq.select()
                .from(STATS)
                .join(TEAM).on(STATS.ID.eq(TEAM.STATS_ID))
                .where(TEAM.ID.eq(teamId))
                .fetchOneInto(Stats.class);

        if (stats != null) {
            return ResponseEntity.ok(stats);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<TeamPlayer> getTeamPlayerInformationByTeamId(int teamId) {
        return jooq
                .selectFrom(TEAM_PLAYER)
                .where(TEAM_PLAYER.TEAM_ID.eq(teamId))
                .fetchInto(TeamPlayer.class);
    }

    public TeamPlayer getTeamPlayerInformationByPlayerId(int playerId) {
        return jooq
                .selectFrom(TEAM_PLAYER)
                .where(TEAM_PLAYER.ID.eq(playerId))
                .fetchOneInto(TeamPlayer.class);
    }

    public List<Role> findAllTeamRoles() {
        return jooq
                .select(ROLE.ROLE_CODE,
                        ROLE.ROLE_NAME)
                .from(ROLE)
                .fetchInto(Role.class);
    }
}
