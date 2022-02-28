package com.ocio.backend17.services;

import com.ocio.backend17.config.IConfigImpl;
import com.ocio.backend17.dao.RolesDao;
import com.ocio.backend17.entities.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolesImpl implements IRoles {
    @Autowired
    RolesDao rolesDao;
    @Autowired
    IConfigImpl config;

    @Override
    public Optional<Roles> getUserRole() {
        return rolesDao.findByRoleKey(config.getUserRoleKey());
    }
    public Optional<Roles> getAdminRole(){
        return rolesDao.findByRoleKey(config.getAdminRolesKey());
    }

}
