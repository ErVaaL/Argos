package com.erval.argos.api.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import com.m2m.internal.auth.server.InternalOAuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/internal/oauth")
@RequiredArgsConstructor
public class InternalTokenController {
    private final InternalOAuthService service;

    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> token(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String auth,
        @RequestParam("grant_type") String grantType,
        @RequestParam(value = "scope", required = false) String scope) {
        InternalOAuthService.TokenResult result = service
            .token(new InternalOAuthService.TokenRequest(auth, grantType, scope));

        if (result instanceof InternalOAuthService.TokenResult.Error(int httpStatus, String errorCode)) {
            return ResponseEntity
                .status(httpStatus)
                .body(Map.of("error", errorCode));
        }

        InternalOAuthService.TokenResult.Success s = (InternalOAuthService.TokenResult.Success) result;
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("access_token", s.accessToken());
        body.put("token_type", s.tokenType());
        body.put("expires_in", s.expiresIn());
        body.put("scope", s.scope());
        body.put("exp", s.exp());
        return ResponseEntity.ok(body);
    }
}
