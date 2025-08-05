package com.pm.authservice.dto;

public class LoginResponesDTO {
    private final String token;

    public LoginResponesDTO(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }
}
