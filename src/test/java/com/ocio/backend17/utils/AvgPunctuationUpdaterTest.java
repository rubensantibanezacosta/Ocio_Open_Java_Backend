package com.ocio.backend17.utils;

import com.ocio.backend17.dao.PunctuationsDao;
import com.ocio.backend17.dao.ZonesDao;
import com.ocio.backend17.entities.Events;
import com.ocio.backend17.entities.Punctuations;
import com.ocio.backend17.entities.Users;
import com.ocio.backend17.entities.Zones;
import com.ocio.backend17.services.EventsImpl;
import com.ocio.backend17.services.PunctuationsImpl;
import com.ocio.backend17.services.UsersImpl;
import com.ocio.backend17.services.ZonesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AvgPunctuationUpdaterTest {
    @Mock
    PunctuationsDao punctuationsDao;
    @Mock
    EventsImpl eventsImpl;
    @Mock
    UsersImpl usersImpl;
    @Mock
    ZonesDao zonesDao;

    private Users user;
    private Zones zone;
    private Events event;
    private Punctuations punctuationCreated;


    @InjectMocks
    PunctuationsImpl punctuationsService;
    @InjectMocks
    EventsImpl eventsService;
    @InjectMocks
    UsersImpl usersService;
    @InjectMocks
    ZonesImpl zonesService;
    @BeforeEach
    void setUp() {
    }

    @Test
    void updateAllPunctuations() {
    }
}