package com.project.wonderboot.service;

import com.project.wonderboot.models.Trip;
import com.project.wonderboot.models.TripCity;
import com.project.wonderboot.models.dto.StatisticsDTO;
import com.project.wonderboot.models.user.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StatisticsService {


    public StatisticsDTO getStatistics(Profile profile) {
        List<Trip> trips = profile.getTrips();
        StatisticsDTO statistics = new StatisticsDTO();

        BigDecimal totalExpenses = BigDecimal.ZERO;
        int visitedCities = 0;
        int days = 0;
        Set<Integer> years = new HashSet<>();

        for (Trip trip : trips) {
            for (TripCity tripCity : trip.getCities()) {
                totalExpenses = totalExpenses.add(tripCity.getExpenses());
                visitedCities++;
                days += (int) ChronoUnit.DAYS.between(tripCity.getStartDate(), tripCity.getEndDate());
                years.add(tripCity.getStartDate().getYear());
            }
        }

        statistics.setMoneySpent(totalExpenses);
        statistics.setCitiesVisited(visitedCities);
        statistics.setDaysTravelled(days);

        statistics.setAverageMoneyPerTrip(
                totalExpenses.divide(
                        BigDecimal.valueOf(trips.size()),
                        2,
                        RoundingMode.HALF_UP
                )
        );
        statistics.setAverageMoneyPerYear(totalExpenses.divide(
                BigDecimal.valueOf(years.size()),
                2,
                RoundingMode.HALF_UP
        ));


        return statistics;
    }
}
