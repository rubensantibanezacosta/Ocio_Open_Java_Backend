package com.ocio.backend17.dto;

public class BasicAuthResponse {
    private String token;
    private String expireDate;

    public BasicAuthResponse() {
    }

    public BasicAuthResponse(String token, String expireDate) {
        this.token = token;
        this.expireDate = expireDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
}
