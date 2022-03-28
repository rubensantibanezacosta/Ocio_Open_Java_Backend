package com.ocio.backend17.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IConfigImpl implements IConfig {
    @Autowired
    Config config;

    @Override
    public String getJwtSecret() {
        return config.getJwtSecret();
    }

    @Override
    public String getUserRoleKey() {
        return config.getUserrolekey();
    }

    @Override
    public int getExpirationTime() {
        return (config.getExpirationTime() * 1000 * 60);
    }

    @Override
    public String getAcceptedDomains() {
        return config.getAcceptedDomains();
    }

    @Override
    public String getGoogleApiUrl() {
        return config.getGoogleApiUrl();
    }

    @Override
    public String getFrontendHost() {
        return config.getFrontendHost();
    }

    @Override
    public String getGoogleMail() {
        return config.getMail();
    }

    @Override
    public String getGooglePassword() {
        return config.getPassword();
    }

    @Override
    public String getAdminRolesKey() {
        return config.getAdminrolekey();
    }

    @Override
    public String imgbbApiKey() {
        return config.getImgbbApiKey();
    }

    @Override
    public String imgbbUrl() {
        return config.getImgbbUrl();
    }
}
