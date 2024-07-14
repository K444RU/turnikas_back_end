package com.example.turnikas_back_end.business.turnament.service;

import com.example.turnikas_back_end.business.team.dto.AgeCategoryDTO;
import com.example.turnikas_back_end.business.turnament.dto.PlayerAmountDTO;
import com.example.turnikas_back_end.business.turnament.dto.TournamentDTO;
import com.example.turnikas_back_end.business.turnament.model.City;
import com.example.turnikas_back_end.business.turnament.model.PlayerAmount;
import com.example.turnikas_back_end.business.turnament.model.Stadium;
import com.example.turnikas_back_end.business.turnament.repository.TournamentRepository;
import com.example.turnikas_back_end.business.turnament.request.TournamentRegistration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {

    TournamentRepository tournamentRepository;

    public TournamentService(TournamentRepository tournamentRepository){
        this.tournamentRepository = tournamentRepository;
    }
    public TournamentDTO registerNewTournament(TournamentRegistration tournamentRegistration) {
    Integer tournamentId = tournamentRepository.add(tournamentRegistration);
    return new TournamentDTO(tournamentId);
    }
    public List<TournamentDTO> getAllTorunaments() {
        return tournamentRepository.findAllTournaments();
    }
    @SuppressWarnings("unchecked")
    public List<City> getAllCities() {
        return (List<City>) tournamentRepository.findAllCities();
    }
    @SuppressWarnings("unchecked")
    public List<Stadium> findAllStadiums() {
        return (List<Stadium>) tournamentRepository.findAllStadiums();
    }
    @SuppressWarnings("unchecked")
    public List<PlayerAmount> getAllPlayerAmounts() {
        return (List<PlayerAmount>) tournamentRepository.findAllPlayerAmounts();
    }

    public List<Stadium> getStadiumByCityId(int cityId) {
        return tournamentRepository.getStadiumByCityId(cityId);
    }


    public City getCityInformationByCityId(int cityId) {
        return tournamentRepository.getCityInformationByCityId(cityId);
    }

    public TournamentDTO getTournamentInformationByTournamentId(int tournamentId) {
        return tournamentRepository.findTournamentInformationByTournamentId(tournamentId);
    }

    public PlayerAmountDTO getPlayerAmountById(int amountCode) {
        return tournamentRepository.findPlayerAmountById(amountCode);
    }

    public AgeCategoryDTO getCategoryNameByAgeCategoryCode(int ageCategoryCode) {
        return tournamentRepository.getCategoryNameByAgeCategoryCode(ageCategoryCode);
    }

    public List<AgeCategoryDTO> getAllAgeCategories() {
        return tournamentRepository.findAllAgeCategories();
    }

    public List<TournamentDTO> getTournamentsByCategoryCode(int categoryCode) {
        return tournamentRepository.findTournamentsByCategoryCode(categoryCode);
    }

    public List<TournamentDTO> getTournamentsByPlayerAmountCode(int playerAmountCode) {
        return tournamentRepository.findTournamentsByPlayerAmountCode(playerAmountCode);
    }

    public City getCityNameByCityId(int cityId) {
        return tournamentRepository.findCityNameByCityId(cityId);
    }

    public List<TournamentDTO> filterTournamentsByStartDate() {
        return tournamentRepository.filterTournamentsByStartDate();
    }
}
