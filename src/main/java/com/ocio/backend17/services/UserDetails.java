package com.ocio.backend17.services;

import com.ocio.backend17.config.IConfigImpl;

import com.ocio.backend17.entities.Roles;

import com.ocio.backend17.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetails implements UserDetailsService {
    @Autowired
    RolesImpl iRoles;
    @Autowired
    IConfigImpl iConfig;
    @Autowired
    UsersImpl iUsers;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Users user = iUsers.getById(username).get();
        Roles roles = iRoles.getRole(iConfig.getUserRoleKey()).get();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<String> scopes = List.of(roles.getPermissions().split(","));
        scopes.forEach(s -> authorities.add(new SimpleGrantedAuthority(s)));
        return new User(user.getEmail(), "{noop}empty", true, true, true, true, authorities);
    }
}
