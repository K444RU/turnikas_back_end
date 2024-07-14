package com.example.turnikas_back_end.business.turnament.controller;

import com.example.turnikas_back_end.business.team.dto.AgeCategoryDTO;
import com.example.turnikas_back_end.business.turnament.dto.PlayerAmountDTO;
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

    @GetMapping("/all")
    @Operation(summary = "Get all tournaments")
    public List<TournamentDTO> getAllTournaments(){
        return tournamentService.getAllTorunaments();
    }

    @GetMapping("/info")
    @Operation(summary = "Get tournament info by tournamentId")
    public TournamentDTO getTournamentInformationByTournamentId(int tournamentId){
        return tournamentService.getTournamentInformationByTournamentId(tournamentId);
    }

    @GetMapping("/city")
    @Operation(summary = "Get all the tournament related cities")
    public List<City> getAllCities(){
        return tournamentService.getAllCities();
    }

    @GetMapping("/city/info")
    @Operation(summary = "Get tournament city info by cityId")
    public City getCityInformationByCityId(@RequestParam int cityId){
        return tournamentService.getCityInformationByCityId(cityId);
    }
    @GetMapping("/city-name/info")
    @Operation(summary = "Get city name by cityId")
    public City getCityNameByCityId(@RequestParam int cityId){
        return tournamentService.getCityNameByCityId(cityId);
    }

    @GetMapping("/stadium")
    @Operation(summary = "Get all the tournament related stadiums")
    public List<Stadium> getAllStadiums(){
        return tournamentService.findAllStadiums();
    }

    @GetMapping("/player/amount")
    @Operation(summary = "Get all the torunament related player amounts for registration")
    public List<PlayerAmount> getAllPlayerAmounts(){
        return tournamentService.getAllPlayerAmounts();
    }

    @GetMapping("/player/amount/info")
    @Operation(summary = "Get the tournament player amounts by its Id's")
    public PlayerAmountDTO getPlayerAmountById(@RequestParam int amountCode){
        return tournamentService.getPlayerAmountById(amountCode);
    }

    @GetMapping("/city/stadium")
    @Operation(summary = "Get stadium by city Id")
    public List<Stadium> getStadiumByCityId(@RequestParam int cityId){
        return tournamentService.getStadiumByCityId(cityId);
    }

    @GetMapping("/age-category")
    @Operation(summary = "Get category name by age category code")
    public AgeCategoryDTO getCategoryNameByAgeCategoryCode(@RequestParam int ageCategoryCode){
        return tournamentService.getCategoryNameByAgeCategoryCode(ageCategoryCode);
    }

    @GetMapping("/age-category/info")
    @Operation(summary = "Get all available age catergories for the tournament")
    public List<AgeCategoryDTO> getAllAgeCategories(){
        return tournamentService.getAllAgeCategories();
    }

    @GetMapping("/age-category/filter")
    @Operation(summary = "Filter tournaments by categoryCode")
    public List<TournamentDTO> getTournamentsByCategoryCode(@RequestParam int categoryCode){
        return tournamentService.getTournamentsByCategoryCode(categoryCode);
    }

    @GetMapping("/player-amount-code/filter")
    @Operation(summary = "Filter tournaments by playerAmountCode")
    public List<TournamentDTO> getTournamentsByPlayerAmountCode(@RequestParam int playerAmountCode){
        return tournamentService.getTournamentsByPlayerAmountCode(playerAmountCode);
    }

    @GetMapping("/start-date/filter")
    @Operation(summary = "Filter tournaments by startDate")
    public List<TournamentDTO> filterTournamentsByStartDate() {
        return tournamentService.filterTournamentsByStartDate();
    }


}
