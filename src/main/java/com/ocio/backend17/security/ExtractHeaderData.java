package com.ocio.backend17.security;

import com.ocio.backend17.dto.BasicAuthRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class ExtractHeaderData {
    private Logger logger = LoggerFactory.getLogger(ExtractHeaderData.class);
    @Autowired
    JWTUtil jwtUtil;

    public BasicAuthRequest extractBasicAuthCredentials(HttpHeaders headers) {

        try {
            String headerAuthDataEncoded = headers.getValuesAsList("Authorization").get(0).toString().substring(6);
            if (headerAuthDataEncoded != null) {
                String[] decodedData = new String(Base64.getDecoder().decode(headerAuthDataEncoded)).split(":");

                return new BasicAuthRequest(decodedData[0], decodedData[1]);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public String extractJWTUsername(HttpHeaders headers) {

        try {
            String authorizationHeader = headers.getValuesAsList("Authorization").get(0);
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
                String jwt = authorizationHeader.substring(7);
                if (jwtUtil.validateToken(jwt)) {
                    return jwtUtil.extractUsername(jwt);
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "";
        }
        return "";
    }
}
