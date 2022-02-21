package com.ocio.backend17.dao;

import com.ocio.backend17.entities.Users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersDao extends CrudRepository<Users, String> {
    @Query("Select u from Users u order by u.punctuation_avg desc")
    List<Users> findUsersOrderByPunctuacion();
}
