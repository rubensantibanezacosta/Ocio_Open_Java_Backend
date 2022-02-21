package com.ocio.backend17.security.google;

public class GoogleResponseDto {
    private String email;

    public GoogleResponseDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GoogleResponseDto() {
    }
}
