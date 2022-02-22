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
        zone=new Zones();
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
        assertEquals(zone, zone);
        assertEquals(zonesImpl.getAll().get(0),zone);
        when(zonesDao.findAll()).thenReturn(Arrays.asList());
        assertEquals(zonesImpl.getAll(), new ArrayList<>());

    }

    @Test
    void updateZoneAvgPunctuation() {
        //Parameters
        String id="GC";
        Double punctuation = 10.0;
        //Two zones to compare
        Zones zoneToModified = new Zones(zone.getId(),zone.getName(),zone.getPunctuationavg(),zone.getCreatedAt(),zone.getUpdatedAt());
        Zones zoneToUseFromDao = new Zones(zone.getId(),zone.getName(),zone.getPunctuationavg(),zone.getCreatedAt(),zone.getUpdatedAt());
        //Set modified zone puntuaction as a diferent punctuation
        zoneToModified.setPunctuationavg(8.0);
        //get original zone by mock
        when(zonesDao.findById(id)).thenReturn(Optional.of(zoneToUseFromDao));
        //save modified zone, and return mocked modified zone
        when(zonesDao.save(zoneToModified)).thenReturn(zoneToModified);
        //use the method will be tested
        zonesImpl.updateZoneAvgPunctuation(id,punctuation);
        //put in Dao the modified object
        when(zonesDao.findById(id)).thenReturn(Optional.of(zoneToModified));
        //Compare mock with manually modified object
        assertEquals(zonesDao.findById(id).get(),zoneToModified);
        //Compare with original zone: Should be different
        assertNotEquals(zonesDao.findById(id).get(),zone);

    }
}