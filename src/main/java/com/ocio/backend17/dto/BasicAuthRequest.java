package com.ocio.backend17.dto;

public class BasicAuthRequest {
    String username;
    String googleToken;

    public BasicAuthRequest(String username, String googleToken) {
        this.username = username;
        this.googleToken = googleToken;
    }

    public BasicAuthRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGoogleToken() {
        return googleToken;
    }

    public void setGoogleToken(String googleToken) {
        this.googleToken = googleToken;
    }
}
