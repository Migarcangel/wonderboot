package com.project.wonderboot.models.dto;

import com.project.wonderboot.models.Trip;
import com.project.wonderboot.models.TripCity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
public class TripDTOAdapter {

    public List<TripResumeDTO> adaptToView(List<Trip> trips) {
        List<TripResumeDTO> tripResumes = new ArrayList<>();

        for (Trip trip : trips) {
            tripResumes.add(adaptToView(trip));
        }

        return tripResumes;
    }

    public TripResumeDTO adaptToView(Trip trip) {
        TripResumeDTO tripResumeDTO = new TripResumeDTO();
        tripResumeDTO.setId(trip.getId());
        tripResumeDTO.setName(trip.getName());

        List<TripCity> cities = trip.getCities();
        tripResumeDTO.setCitiesCount(cities.size());

        LocalDate startDate = cities.getFirst().getStartDate();
        LocalDate endDate = cities.getLast().getEndDate();

        tripResumeDTO.setStartDate(startDate);
        tripResumeDTO.setEndDate(endDate);
        tripResumeDTO.setTotalDays((int) ChronoUnit.DAYS.between(startDate, endDate));

        BigDecimal totalExpenses = BigDecimal.ZERO;
        for(TripCity tripCity : cities) {
            totalExpenses = totalExpenses.add(tripCity.getExpenses());
        }
        tripResumeDTO.setExpenses(totalExpenses);

        return tripResumeDTO;
    }

    public Trip adapt(TripDTO tripDTO) {
        Trip trip = new Trip();
        trip.setName(tripDTO.getName());
        trip.setCities(adaptCities(tripDTO.getCities()));
        for (TripCity city : trip.getCities()) {
            city.setTrip(trip);
        }

        return trip;
    }

    public List<TripCity> adaptCities(List<TripCityDTO> citiesDTO) {
        return citiesDTO.stream()
                .map(this::adaptCity)
                .toList();
    }

    private TripCity adaptCity(TripCityDTO cityDTO) {
        TripCity city = new TripCity();

        city.setCity(cityDTO.getCity());
        city.setStartDate(cityDTO.getStartDate());
        city.setEndDate(cityDTO.getEndDate());
        city.setExpenses(cityDTO.getExpenses());

        return city;
    }
}
