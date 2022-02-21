package com.ocio.backend17.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocio.backend17.config.IConfigImpl;
import com.ocio.backend17.dto.BasicAuthRequest;

import com.ocio.backend17.dto.BasicAuthResponse;
import com.ocio.backend17.dto.ResponseMessage;
import com.ocio.backend17.entities.Users;
import com.ocio.backend17.security.ExtractHeaderData;
import com.ocio.backend17.security.JWTUtil;

import com.ocio.backend17.security.google.GoogleValidation;
import com.ocio.backend17.services.UsersImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UserDetails;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BasicAuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    ExtractHeaderData extractHeaderData;
    @Autowired
    UsersImpl usersimpl;
    @Autowired
    IConfigImpl iConfig;
    @Autowired
    GoogleValidation googleValidation;
    private Logger logger = LoggerFactory.getLogger(BasicAuthController.class);

    @PostMapping(value = "/api/auth/sign-in", consumes = "application/json")
    public ResponseEntity<?> login(@RequestHeader HttpHeaders headers, @RequestBody String jsonUser) {
        try {
            BasicAuthRequest basicAuthRequest = extractHeaderData.extractBasicAuthCredentials(headers);
            if (iConfig.getAcceptedDomains().contains(basicAuthRequest.getUsername().split("@")[1])) {
                ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                        false);
                Users user = om.readValue(jsonUser, Users.class);

                if (googleValidation.validateToken(basicAuthRequest.getGoogleToken()).getEmail()
                        .equals(basicAuthRequest.getUsername())) {
                    usersimpl.createOrUpdate(user);
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(basicAuthRequest.getUsername(), "empty"));
                    UserDetails userDetails = userDetailsService.loadUserByUsername(basicAuthRequest.getUsername());
                    String jwt = jwtUtil.generateToken(userDetails);
                    String tokenExpiresIn = jwtUtil.extractExpireTime(jwt);
                    return new ResponseEntity<>(new BasicAuthResponse(jwt, tokenExpiresIn), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ResponseMessage("Bad Credentials"), HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(
                        new ResponseMessage("Domain " + basicAuthRequest.getUsername().split("@")[1] + " not allowed"),
                        HttpStatus.UNAUTHORIZED);
            }
        } catch (BadCredentialsException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(new ResponseMessage("Bad Credentials Bad:" + e.getMessage()),
                    HttpStatus.UNAUTHORIZED);
        } catch (JsonMappingException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(new ResponseMessage("Uknown error"), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(new ResponseMessage("Bad Credentials Null"), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(new ResponseMessage("Unknown error"), HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping(value = "/api/auth/register", consumes = "application/json")
    public ResponseEntity<?> register(@RequestHeader HttpHeaders headers, @RequestBody Users user) {
        try {
            BasicAuthRequest basicAuthRequest = extractHeaderData.extractBasicAuthCredentials(headers);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(basicAuthRequest.getUsername(),
                    basicAuthRequest.getGoogleToken()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(basicAuthRequest.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);
            String tokenExpiresIn = jwtUtil.extractExpireTime(jwt);
            return new ResponseEntity<>(new BasicAuthResponse(jwt, tokenExpiresIn), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ResponseMessage("Bad Credentials"), HttpStatus.UNAUTHORIZED);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(new ResponseMessage("Bad Credentials"), HttpStatus.BAD_REQUEST);
        }

    }
}
