package com.ocio.backend17.dao;

import com.ocio.backend17.entities.Roles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesDao extends CrudRepository<Roles, Integer> {

    Optional<Roles> findByRoleKey(String roleKey);
}
