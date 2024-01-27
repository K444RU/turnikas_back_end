package com.example.turnikas_back_end.business.turnament.controller;

import com.example.turnikas_back_end.business.turnament.dto.TournamentDTO;
import com.example.turnikas_back_end.business.turnament.model.City;
import com.example.turnikas_back_end.business.turnament.model.PlayerAmount;
import com.example.turnikas_back_end.business.turnament.model.Stadium;
import com.example.turnikas_back_end.business.turnament.request.TournamentRegistration;
import com.example.turnikas_back_end.business.turnament.service.TournamentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournament")
public class TournamentController {

    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @PostMapping("/register")
    @Operation(summary = "Post tournament information(new tournament registration)")
    public TournamentDTO registerNewTournament(@RequestBody TournamentRegistration tournamentRegistration){
        return tournamentService.registerNewTournament(tournamentRegistration);
    }

    @GetMapping("/city")
    @Operation(summary = "Get all the tournament related cities")
    public List<City> getAllCities(){
        return tournamentService.getAllCities();
    }

    @GetMapping("/stadium")
    @Operation(summary = "Get all the tournament related stadiums")
    public List<Stadium> getAllStadiums(){
        return tournamentService.getAllTorunaments();
    }

    @GetMapping("/player/amount")
    @Operation(summary = "Get all the torunament related player amounts for registration")
    public List<PlayerAmount> getAllPlayerAmounts(){
        return tournamentService.getAllPlayerAmounts();
    }

    @GetMapping("/city/stadium")
    @Operation(summary = "Get stadium by city Id")
    public Stadium getStadiumByCityId(@RequestParam int cityId){
        return tournamentService.getStadiumByCityId(cityId);
    }
}
