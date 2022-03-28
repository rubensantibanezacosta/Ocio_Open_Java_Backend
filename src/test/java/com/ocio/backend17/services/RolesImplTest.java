package com.ocio.backend17.services;


import com.ocio.backend17.dao.RolesDao;
import com.ocio.backend17.entities.Roles;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;



class RolesImplTest {

    @Mock
    RolesDao rolesDao;
    @InjectMocks
    RolesImpl rolesImpl;

    private Roles role=new Roles();
    private Date date = new Date();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        role.setRoleKey("123");
        role.setNumber(1);
        role.setCreatedAt(new java.sql.Date(date.getTime()));
        role.setUpdatedAt(new java.sql.Date(date.getTime()));
        role.setPermissions("permission");

    }

//    @Test
//    void getRole() {
//        when(rolesDao.findByRoleKey("123")).thenReturn(Optional.of(role));
//        assertEquals(rolesImpl.getRole("123").get().getRoleKey(),role.getRoleKey());
//        assertEquals(rolesImpl.getRole("123").get().getPermissions(),role.getPermissions());
//        assertEquals(rolesImpl.getRole("123").get().getCreatedAt(),role.getCreatedAt());
//        assertEquals(rolesImpl.getRole("123").get().getUpdatedAt(),role.getUpdatedAt());
//    }
}