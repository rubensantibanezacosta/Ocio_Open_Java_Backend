package com.ocio.backend17.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocio.backend17.dto.ResponseMessage;
import com.ocio.backend17.dto.UsersDto;
import com.ocio.backend17.entities.Users;
import com.ocio.backend17.services.UsersImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class UsersController {
    @Autowired
    UsersImpl usersImpl;
    private Logger logger = LoggerFactory.getLogger(UsersController.class);

    @PreAuthorize("hasAuthority('read:users')")
    @GetMapping("/api/user")
    ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<List<Users>>(usersImpl.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('read:users')")
    @GetMapping("/api/user/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable("email") String email) {
        try {
            if (usersImpl.getById(email).isPresent()) {
                return new ResponseEntity<>(new UsersDto(usersImpl.getById(email).get()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new UsersDto(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('read:users')")
    @GetMapping("/api/user/position/{email}")
    public ResponseEntity<?> getUserPositionByEmail(@PathVariable("email") String email) {
        try {
            return new ResponseEntity<>(usersImpl.getUserPosition(email), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('delete:users')")
    @DeleteMapping("/api/user/{email}")
    @ResponseBody
    ResponseEntity<ResponseMessage> deleteByEmail(@PathVariable("email") String email) {
        try {
            if (usersImpl.getById(email).isPresent()) {
                usersImpl.deleteById(email);
                return new ResponseEntity<>(new ResponseMessage("User deleted"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseMessage("User not found"), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('create:users')")
    @PostMapping(value = "/api/user/", consumes = "application/json")
    @ResponseBody
    ResponseEntity<?> createOrUpdateUser(@RequestBody String jsonUser) {
        try {
            ObjectMapper om = new ObjectMapper();
            Users user = om.readValue(jsonUser, Users.class);
            return new ResponseEntity<>(new UsersDto(usersImpl.createOrUpdate(user)), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('update:users')")
    @PutMapping(value = "/api/user/{email}", consumes = "application/json")
    @ResponseBody
    ResponseEntity<?> UpdateUserPermissions(@RequestBody String permissions, @PathVariable String email) {
        try {
            return new ResponseEntity<>(usersImpl.updateUserPermissions(permissions,email), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}