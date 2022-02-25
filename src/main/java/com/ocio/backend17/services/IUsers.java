package com.ocio.backend17.services;

import com.ocio.backend17.entities.Users;

import java.util.List;
import java.util.Optional;

public interface IUsers {
    List<Users> getAll();

    Optional<Users> getById(String email);

    Users createOrUpdate(Users user);

    List<Users> getByLastConnection();

    void deleteById(String email);

    int getUserPosition(String email);

    void updateAvgPunctuation(Double punctuation_avg, String id);

    void updateLastConnection(String email);

}
