package com.project.wonderboot.controller;

import com.project.wonderboot.models.City;
import com.project.wonderboot.models.Country;
import com.project.wonderboot.service.CityService;
import com.project.wonderboot.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/api/cities")
    City[] searchCities(@RequestParam String term) {
        return cityService.searchCities(term);
    }
}
