package com.example.turnikas_back_end.business.turnament.repository;

import com.example.turnikas_back_end.business.common.repository.TurnikasRepository;
import com.example.turnikas_back_end.business.turnament.dto.TournamentDTO;
import com.example.turnikas_back_end.business.turnament.model.City;
import com.example.turnikas_back_end.business.turnament.model.PlayerAmount;
import com.example.turnikas_back_end.business.turnament.model.Stadium;
import com.example.turnikas_back_end.business.turnament.request.TournamentRegistration;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
                        TOURNAMENT.AGE_CATEGORY_CODE,
                        TOURNAMENT.PLAYER_AMOUNT_CODE,
                        TOURNAMENT.CITY_ID,
                        TOURNAMENT.STADIUM_ID,
                        TOURNAMENT.NAME,
                        TOURNAMENT.START_DATE,
                        TOURNAMENT.END_DATE,
                        TOURNAMENT.PARTICIPATION_PRISE,
                        TOURNAMENT.PRIZE,
                        TOURNAMENT.ADDITIONAL_INFO)
                .values(tournamentRegistration.getAgeCategoryCode(),
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
                        TOURNAMENT.AGE_CATEGORY_CODE,
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
                .select(TOURNAMENT,
                        TOURNAMENT.AGE_CATEGORY_CODE,
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

    public Stadium getStadiumByCityId(int cityId) {
        return jooq
                .selectFrom(STADIUM)
                .where(STADIUM.CITY_ID.eq(cityId))
                .fetchOneInto(Stadium.class);
    }


}
