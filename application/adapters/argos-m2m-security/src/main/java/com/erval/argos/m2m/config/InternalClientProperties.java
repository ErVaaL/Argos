package com.erval.argos.m2m.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "internal")
public record InternalClientProperties(
    Map<String, ClientProps> clients
) {
    public record ClientProps(
        String secret,
        List<String> scopes
    ){}
}
