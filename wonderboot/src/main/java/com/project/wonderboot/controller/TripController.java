package com.project.wonderboot.controller;

import com.project.wonderboot.models.Trip;
import com.project.wonderboot.models.TripCity;
import com.project.wonderboot.models.dto.TripDTO;
import com.project.wonderboot.models.dto.TripDTOAdapter;
import com.project.wonderboot.models.dto.TripResumeDTO;
import com.project.wonderboot.models.user.Profile;
import com.project.wonderboot.repository.TripRepository;
import com.project.wonderboot.repository.WonderUserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/trips")
@Controller
public class TripController {
    private final TripDTOAdapter tripDTOAdapter;
    private final TripRepository tripRepository;
    private final WonderUserRepository wonderUserRepository;

    public TripController(TripDTOAdapter tripDTOAdapter, TripRepository tripRepository, WonderUserRepository wonderUserRepository) {
        this.tripDTOAdapter = tripDTOAdapter;
        this.tripRepository = tripRepository;
        this.wonderUserRepository = wonderUserRepository;
    }

    @GetMapping("")
    String trips(final Model model, Authentication authentication) {
        Profile profile = wonderUserRepository
                .findByProfileUsername(authentication.getName())
                .orElseThrow()
                .getProfile();

        List<Trip> trips = profile.getTrips();
        List<TripResumeDTO> tripResumes = tripDTOAdapter.adaptToView(trips);
        model.addAttribute("trips", tripResumes);

        return "trips";
    }

    @GetMapping("/create")
    String createFormTrip() {
        return "createTrips";
    }

    @GetMapping("/update/{id}")
    String updateFormTrip(@PathVariable Long id, Model model) {
        Trip trip = tripRepository.findById(id).orElseThrow();
        model.addAttribute("trip",trip);
        return "updateTrips";
    }

    @PostMapping("/create")
    String createTrip(@ModelAttribute TripDTO tripDTO, Authentication authentication) {
        Trip trip = tripDTOAdapter.adapt(tripDTO);

        Profile profile = wonderUserRepository.findByProfileUsername(authentication.getName()).orElseThrow().getProfile();
        trip.setProfile(profile);
        tripRepository.save(trip);

        return "redirect:/trips";
    }

    @PostMapping("/update/{id}")
    String updateTrip(@PathVariable Long id, @ModelAttribute TripDTO tripDTO) {

        Trip trip = tripRepository.findById(id).orElseThrow();

        trip.setName(tripDTO.getName());

        trip.getCities().clear();

        List<TripCity> cities = tripDTOAdapter.adaptCities(tripDTO.getCities());

        for (TripCity city : cities) {
            city.setTrip(trip);
        }

        trip.getCities().addAll(cities);

        tripRepository.save(trip);

        return "redirect:/trips";
    }

    @PostMapping("/delete/{id}")
    String deleteTrip(@PathVariable Long id) {
        Trip trip = tripRepository.findById(id).orElseThrow();

        tripRepository.delete(trip);
        return "redirect:/trips";
    }
}
