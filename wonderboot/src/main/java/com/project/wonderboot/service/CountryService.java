package com.project.wonderboot.service;

import com.project.wonderboot.config.LocationApiProperties;
import com.project.wonderboot.errorhandler.LocationResponseErrorHandler;
import com.project.wonderboot.models.Country;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
public class CountryService {
    private final RestClient restClient;

    public CountryService(final RestClient.Builder builder, final LocationApiProperties locationApiProperties,
                          LocationResponseErrorHandler locationResponseErrorHandler) {
        String locationApiHost = locationApiProperties.getHost() + "/api/cities";

        this.restClient = builder.clone()
                .defaultStatusHandler(locationResponseErrorHandler)
                .baseUrl(locationApiHost)
                .build();
    }

    public Country[] getCountries() {
        String term = "";

        try {
            ResponseEntity<Country[]> responseEntity = restClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("term", term)
                            .build())
                    .retrieve()
                    .toEntity(Country[].class);

            return responseEntity.getBody();

        } catch (RestClientException e) {
            return new Country[0];
        }
    }

    public Country[] searchCountries(String term) {
        try {
            ResponseEntity<Country[]> responseEntity = restClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("term", term)
                            .build())
                    .retrieve()
                    .toEntity(Country[].class);

            return responseEntity.getBody();

        } catch (RestClientException e) {
            return new Country[0];
        }
    }
}
