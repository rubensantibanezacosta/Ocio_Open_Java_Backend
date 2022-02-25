package com.ocio.backend17.config;

public interface IConfig {
    String getJwtSecret();

    String getUserRoleKey();

    int getExpirationTime();

    String getAcceptedDomains();

    String getGoogleApiUrl();

    String getFrontendHost();

    String getGoogleMail();

    String getGooglePassword();

    String getAdminRolesKey();
}
