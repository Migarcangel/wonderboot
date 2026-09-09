package com.project.wonderboot.service;

import com.project.wonderboot.config.LocationApiProperties;
import com.project.wonderboot.errorhandler.LocationResponseErrorHandler;
import com.project.wonderboot.models.City;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
public class CityService {

    private final RestClient restClient;

    public CityService(final RestClient.Builder builder,
                       final LocationApiProperties locationApiProperties,
                       LocationResponseErrorHandler locationResponseErrorHandler) {

        String locationApiHost = locationApiProperties.getHost() + "/api/cities";

        this.restClient = builder.clone()
                .defaultStatusHandler(locationResponseErrorHandler)
                .baseUrl(locationApiHost)
                .build();
    }

    public City[] getCities() {
        String term = "";

        try {
            ResponseEntity<City[]> responseEntity = restClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("term", term)
                            .build())
                    .retrieve()
                    .toEntity(City[].class);

            return responseEntity.getBody();

        } catch (RestClientException e) {
            return new City[0];
        }
    }

    public City[] searchCities(String term) {
        try {
            ResponseEntity<City[]> responseEntity = restClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("term", term)
                            .build())
                    .retrieve()
                    .toEntity(City[].class);

            return responseEntity.getBody();

        } catch (RestClientException e) {
            return new City[0];
        }
    }
}