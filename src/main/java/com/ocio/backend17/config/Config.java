package com.ocio.backend17.config;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class Config {
    @Value("${value.userrolekey}")
    private String userrolekey;
    @Value("${value.jwtsecret}")
    private String jwtSecret;
    @Value("${value.expirationTime}")
    private int expirationTime;
    @Value("${value.accepted.domains}")
    private String acceptedDomains;
    @Value("${value.google.url.tokenvalidation}")
    private String googleApiUrl;
    @Value("${value.frontend.host}")
    private String frontendHost;
    @Value("${value.google.mail}")
    private String mail;
    @Value("${value.google.mail.password}")
    private String password;


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Config() {
    }

    public String getUserrolekey() {
        return userrolekey;
    }

    public void setUserrolekey(String userrolekey) {
        this.userrolekey = userrolekey;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public int getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(int expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getAcceptedDomains() {
        return acceptedDomains;
    }

    public String getGoogleApiUrl() {
        return googleApiUrl;
    }

    public void setGoogleApiUrl(String googleApiUrl) {
        this.googleApiUrl = googleApiUrl;
    }

    public void setAcceptedDomains(String acceptedDomains) {
        this.acceptedDomains = acceptedDomains;
    }

    public String getFrontendHost() {
        return frontendHost;
    }

    public void setFrontendHost(String frontendHost) {
        this.frontendHost = frontendHost;
    }
}