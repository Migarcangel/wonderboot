package com.project.wonderboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "location.api")
public class LocationApiProperties {
    private final String host;

    public LocationApiProperties(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }
}
