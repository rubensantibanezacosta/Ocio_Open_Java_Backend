package com.ocio.backend17.services;

import com.ocio.backend17.entities.Roles;

import java.util.Optional;

public interface IRoles {
    Optional<Roles> getRole(String roleKey);
}
