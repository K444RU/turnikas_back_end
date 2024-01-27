package com.example.turnikas_back_end.business.turnament.service;

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

    public Stadium getStadiumByCityId(int cityId) {
        return tournamentRepository.getStadiumByCityId(cityId);
    }


}
