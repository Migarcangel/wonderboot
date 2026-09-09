package com.project.wonderboot.controller;

import com.project.wonderboot.models.Country;
import com.project.wonderboot.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/api/countries")
    Country[] searchCountries(@RequestParam String term) {
        return countryService.searchCountries(term);
    }
}
