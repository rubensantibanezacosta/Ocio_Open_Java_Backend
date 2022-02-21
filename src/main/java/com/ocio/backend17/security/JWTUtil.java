package com.ocio.backend17.security;

import com.ocio.backend17.config.IConfigImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JWTUtil {
    private final static Logger logger = LoggerFactory.getLogger(JWTUtil.class);
    @Autowired
    IConfigImpl iConfigImpl;

    public String generateToken(UserDetails userDetails) {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'4Z'");
        Date dateExpiration = new Date((System.currentTimeMillis() + (iConfigImpl.getExpirationTime())));
        return Jwts.builder().setSubject(userDetails.getUsername())
                .claim("username", userDetails.getUsername())
                .claim("scopes",
                        userDetails.getAuthorities().stream().map((x) -> x.getAuthority()).collect(Collectors.toList()))
                .claim("tokenExpiresIn", dateFormatter.format(dateExpiration))
                .setIssuedAt(new Date())
                .setExpiration(dateExpiration)
                .signWith(SignatureAlgorithm.HS256, iConfigImpl.getJwtSecret()).compact();
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(iConfigImpl.getJwtSecret()).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Token mal formado");

        } catch (UnsupportedJwtException e) {
            logger.error("Token no soportado");
        } catch (ExpiredJwtException e) {
            logger.error("Token expirado" + e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Token vacio");
        } catch (SignatureException e) {
            logger.error("Error en la firma");
        }
        return false;
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public String extractExpireTime(String token) {
        return (String) getClaims(token).get("tokenExpiresIn");
    }

    public List<String> extractScopes(String token) {
        return (List<String>) getClaims(token).get("scopes");
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(iConfigImpl.getJwtSecret()).parseClaimsJws(token).getBody();
    }

}
