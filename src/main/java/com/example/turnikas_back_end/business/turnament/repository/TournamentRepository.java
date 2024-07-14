package com.example.turnikas_back_end.business.turnament.repository;

import com.example.turnikas_back_end.business.common.repository.TurnikasRepository;
import com.example.turnikas_back_end.business.team.dto.AgeCategoryDTO;
import com.example.turnikas_back_end.business.turnament.dto.PlayerAmountDTO;
import com.example.turnikas_back_end.business.turnament.dto.TournamentDTO;
import com.example.turnikas_back_end.business.turnament.model.City;
import com.example.turnikas_back_end.business.turnament.model.PlayerAmount;
import com.example.turnikas_back_end.business.turnament.model.Stadium;
import com.example.turnikas_back_end.business.turnament.request.TournamentRegistration;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static org.jooq.generated.tables.AgeCategory.AGE_CATEGORY;
import static org.jooq.generated.tables.City.CITY;
import static org.jooq.generated.tables.PlayerAmount.PLAYER_AMOUNT;
import static org.jooq.generated.tables.Stadium.STADIUM;
import static org.jooq.generated.tables.Tournament.TOURNAMENT;

@Repository
public class TournamentRepository implements TurnikasRepository {

    private final DSLContext jooq;

    @Autowired
    public TournamentRepository(DSLContext jooq) {
        this.jooq = jooq;
    }


    @Override
    @Transactional
    public Integer add(Object object) {
        TournamentRegistration tournamentRegistration = (TournamentRegistration) object;
        return jooq
                .insertInto(TOURNAMENT,
                        TOURNAMENT.CATEGORY_CODE,
                        TOURNAMENT.PLAYER_AMOUNT_CODE,
                        TOURNAMENT.CITY_ID,
                        TOURNAMENT.STADIUM_ID,
                        TOURNAMENT.NAME,
                        TOURNAMENT.START_DATE,
                        TOURNAMENT.END_DATE,
                        TOURNAMENT.PARTICIPATION_PRISE,
                        TOURNAMENT.PRIZE,
                        TOURNAMENT.ADDITIONAL_INFO)
                .values(tournamentRegistration.getCategoryCode(),
                        tournamentRegistration.getPlayerAmountCode(),
                        tournamentRegistration.getCityId(),
                        tournamentRegistration.getStadiumId(),
                        tournamentRegistration.getName(),
                        tournamentRegistration.getStartDate(),
                        tournamentRegistration.getEndDate(),
                        tournamentRegistration.getParticipationPrise(),
                        tournamentRegistration.getPrize(),
                        tournamentRegistration.getAdditionalInfo())
                .returning(TOURNAMENT.ID,
                        TOURNAMENT.NAME,
                        TOURNAMENT.CATEGORY_CODE,
                        TOURNAMENT.CITY_ID,
                        TOURNAMENT.START_DATE,
                        TOURNAMENT.END_DATE,
                        TOURNAMENT.PARTICIPATION_PRISE,
                        TOURNAMENT.PRIZE)
                .execute();
    }

    @Override
    public Object delete(Integer id) {
        return null;
    }

    @Override
    public List<?> findAll() {
        return null;
    }

    @Override
    public Object findById(Integer id) {
        return null;
    }

    @Override
    public Object update(Object object) {
        return null;
    }

    public List<TournamentDTO> findAllTournaments() {
        return jooq
                .select(TOURNAMENT.ID,
                        TOURNAMENT.CATEGORY_CODE,
                        TOURNAMENT.PLAYER_AMOUNT_CODE,
                        TOURNAMENT.CITY_ID,
                        TOURNAMENT.STADIUM_ID,
                        TOURNAMENT.NAME,
                        TOURNAMENT.START_DATE,
                        TOURNAMENT.END_DATE,
                        TOURNAMENT.PARTICIPATION_PRISE,
                        TOURNAMENT.PRIZE,
                        TOURNAMENT.ADDITIONAL_INFO)
                .from(TOURNAMENT)
                .orderBy(TOURNAMENT.ID.desc())
                .fetchInto(TournamentDTO.class);
    }
    public Object findAllCities() {
        return jooq
                .select(CITY.ID,
                        CITY.CITY_NAME)
                .from(CITY)
                .fetchInto(City.class);
    }

    public Object findAllStadiums() {
        return jooq
                .select(STADIUM.ID,
                        STADIUM.CITY_ID,
                        STADIUM.NAME)
                .from(STADIUM)
                .fetchInto(Stadium.class);
    }

    public Object findAllPlayerAmounts() {
        return jooq
                .select(PLAYER_AMOUNT.AMOUNT_CODE,
                        PLAYER_AMOUNT.AMOUNT_NAME)
                .from(PLAYER_AMOUNT)
                .fetchInto(PlayerAmount.class);
    }

    public List<Stadium> getStadiumByCityId(int cityId) {
        return Collections.singletonList(jooq
                .selectFrom(STADIUM)
                .where(STADIUM.CITY_ID.eq(cityId))
                .fetchOneInto(Stadium.class));
    }


    public City getCityInformationByCityId(int cityId) {
        return jooq
                .selectFrom(CITY)
                .where(CITY.ID.eq(cityId))
                .fetchOneInto(City.class);
    }

    public TournamentDTO findTournamentInformationByTournamentId(int tournamentId) {
        return jooq
                .select(TOURNAMENT.ID,
                        TOURNAMENT.CATEGORY_CODE,
                        TOURNAMENT.PLAYER_AMOUNT_CODE,
                        TOURNAMENT.CITY_ID,
                        TOURNAMENT.STADIUM_ID,
                        TOURNAMENT.NAME,
                        TOURNAMENT.START_DATE,
                        TOURNAMENT.END_DATE,
                        TOURNAMENT.PARTICIPATION_PRISE,
                        TOURNAMENT.PRIZE,
                        TOURNAMENT.ADDITIONAL_INFO)
                .from(TOURNAMENT)
                .where(TOURNAMENT.ID.eq(tournamentId))
                .fetchOneInto(TournamentDTO.class);
    }

    public PlayerAmountDTO findPlayerAmountById(int amountCode) {
        return jooq
                .select(PLAYER_AMOUNT.AMOUNT_CODE,
                        PLAYER_AMOUNT.AMOUNT_NAME)
                .from(PLAYER_AMOUNT)
                .where(PLAYER_AMOUNT.AMOUNT_CODE.eq(amountCode))
                .fetchOneInto(PlayerAmountDTO.class);
    }

    public AgeCategoryDTO getCategoryNameByAgeCategoryCode(int ageCategoryCode) {
        return jooq
                .select(AGE_CATEGORY.CATEGORY_CODE, AGE_CATEGORY.CATEGORY_NAME)
                .from(AGE_CATEGORY)
                .where(AGE_CATEGORY.CATEGORY_CODE.eq(ageCategoryCode))
                .fetchOneInto(AgeCategoryDTO.class);
    }

    public List<AgeCategoryDTO> findAllAgeCategories() {
        return jooq
                .select(AGE_CATEGORY.CATEGORY_CODE,
                        AGE_CATEGORY.CATEGORY_NAME)
                .from(AGE_CATEGORY)
                .fetchInto(AgeCategoryDTO.class);
    }

    public List<TournamentDTO> findTournamentsByCategoryCode(int categoryCode) {
        return jooq
                .selectFrom(TOURNAMENT)
                .where(TOURNAMENT.CATEGORY_CODE.eq(categoryCode))
                .fetchInto(TournamentDTO.class);
    }

    public List<TournamentDTO> findTournamentsByPlayerAmountCode(int playerAmountCode) {
        return jooq
                .selectFrom(TOURNAMENT)
                .where(TOURNAMENT.PLAYER_AMOUNT_CODE.eq(playerAmountCode))
                .fetchInto(TournamentDTO.class);
    }

    public City findCityNameByCityId(int cityId) {
        return jooq
                .select(CITY.CITY_NAME)
                .from(CITY)
                .where(CITY.ID.eq(cityId))
                .fetchOneInto(City.class);
    }

    public List<TournamentDTO> filterTournamentsByStartDate() {
        return jooq
                .select(TOURNAMENT.ID,
                        TOURNAMENT.CATEGORY_CODE,
                        TOURNAMENT.PLAYER_AMOUNT_CODE,
                        TOURNAMENT.CITY_ID,
                        TOURNAMENT.STADIUM_ID,
                        TOURNAMENT.NAME,
                        TOURNAMENT.START_DATE,
                        TOURNAMENT.END_DATE,
                        TOURNAMENT.PARTICIPATION_PRISE,
                        TOURNAMENT.PRIZE,
                        TOURNAMENT.ADDITIONAL_INFO)
                .from(TOURNAMENT)
                .orderBy(TOURNAMENT.START_DATE.desc())
                .fetchInto(TournamentDTO.class);
    }
}
