package com.ocio.backend17.security.filterJwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocio.backend17.security.JWTUtil;
import com.ocio.backend17.services.UsersImpl;
import io.jsonwebtoken.Claims;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

@Component
public class JwtFilterRequest extends OncePerRequestFilter {
    @Autowired
    JWTUtil jwtUtil;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    UsersImpl usersService;

    private final static Logger logger = LoggerFactory.getLogger(JwtFilterRequest.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {

                String authorizationHeader = request.getHeader("Authorization");
                    logger.debug("Http jwt: "+authorizationHeader);
                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
                    String jwt = authorizationHeader.substring(7);
                    logger.debug("Http jwt: " + jwt);


                    if (jwtUtil.validateToken(jwt)) {
                        String username = jwtUtil.extractUsername(jwt);
                        usersService.updateLastConnection(username);
                        List<String> scopes = jwtUtil.extractScopes(jwt);
                        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                        scopes.forEach(s -> authorities.add(new SimpleGrantedAuthority(s)));
                        UserDetails userDetails = new User(username, "{noop}empty", true, true, true, true, authorities);
                        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,
                                null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }


        } catch (Exception e) {

            logger.error("Failed doFilter:" + e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
