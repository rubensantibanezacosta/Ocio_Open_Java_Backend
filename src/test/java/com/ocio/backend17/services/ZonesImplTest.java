package com.ocio.backend17.services;

import com.ocio.backend17.dao.ZonesDao;
import com.ocio.backend17.entities.Zones;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class ZonesImplTest {

    @Mock
    ZonesDao zonesDao;
    @InjectMocks
    ZonesImpl zonesImpl;

    Zones zone;
    Date date = new Date();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        zone = new Zones();
        zone.setPunctuationavg(10.0);
        zone.setId("GC");
        zone.setName("Gran Canaria");
        zone.setCreatedAt(new java.sql.Date(date.getTime()));
        zone.setUpdatedAt(new java.sql.Date(date.getTime()));

    }

    @Test
    void getAll() {
        when(zonesDao.findAll()).thenReturn(Arrays.asList(zone));
        assertNotNull(zonesImpl.getAll());
        assertEquals(zonesImpl.getAll().get(0).getId(),zone.getId());
        assertEquals(zonesImpl.getAll().get(0).getCreatedAt(),zone.getCreatedAt());
        assertEquals(zonesImpl.getAll().get(0).getUpdatedAt(),zone.getUpdatedAt());
        assertEquals(zonesImpl.getAll().get(0).getName(),zone.getName());
        assertEquals(zonesImpl.getAll().get(0).getPunctuationavg(),zone.getPunctuationavg());
        assertEquals(zonesImpl.getAll().get(0), zone);
        when(zonesDao.findAll()).thenReturn(Arrays.asList());
        assertEquals(zonesImpl.getAll(), new ArrayList<>());
    }


}