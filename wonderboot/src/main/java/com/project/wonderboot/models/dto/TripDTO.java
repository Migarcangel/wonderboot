package com.project.wonderboot.models.dto;

import java.util.List;

public class TripDTO {
    private String name;
    private List<TripCityDTO> cities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TripCityDTO> getCities() {
        return cities;
    }

    public void setCities(List<TripCityDTO> cities) {
        this.cities = cities;
    }
}
