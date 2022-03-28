package com.ocio.backend17.services;

import com.ocio.backend17.dao.AssistantDao;
import com.ocio.backend17.entities.Assistants;
import com.ocio.backend17.entities.AssistantsPK;
import com.ocio.backend17.utils.DateFormatterSQL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AssistantImplTest {
    @Mock
    AssistantDao assistantDao;
    @InjectMocks
    AssistantImpl assistantImpl;
    @InjectMocks
    DateFormatterSQL dateFormatterSQL;

    private Assistants assistant=new Assistants();
    private Date date=new Date();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        assistantImpl.dateFormatterSQL=dateFormatterSQL;
        assistant.setAssistant("ruben");
        assistant.setAttendance(true);
        assistant.setExcuse("excuse");
        assistant.setEvent_id(1.0);
        assistant.setCreatedAt(new java.sql.Date(date.getTime()));
        assistant.setUpdatedAt(new java.sql.Date(date.getTime()));
    }

    @Test
    void add() {
        when(assistantDao.save(assistant)).thenReturn(assistant);

        assertEquals(assistantImpl.add(assistant).getAssistant(),assistant.getAssistant());
        assertEquals(assistantImpl.add(assistant).getExcuse(),assistant.getExcuse());
        assertEquals(assistantImpl.add(assistant).getEvent_id(),assistant.getEvent_id());
        assertEquals(assistantImpl.add(assistant).getAttendance(),assistant.getAttendance());
        assertEquals(assistantImpl.add(assistant).getCreatedAt(),assistant.getCreatedAt());
        assertEquals(assistantImpl.add(assistant).getUpdatedAt(),assistant.getUpdatedAt());


    }

    @Test
    void findByPk() {
        when(assistantDao.findById(new AssistantsPK(assistant.getEvent_id(), assistant.getAssistant()))).thenReturn(Optional.of(assistant));
        assertEquals(assistantImpl.findByPk(new AssistantsPK(assistant.getEvent_id(), assistant.getAssistant())).get(),assistant);
    }

    @Test
    void findByEventAndAttendance() {
        when(assistantDao.findAllByEvent_idAndAttendance(assistant.getEvent_id(),assistant.getAttendance())).thenReturn(Arrays.asList(assistant));
        assertEquals(assistantImpl.findByEventAndAttendance(assistant.getEvent_id(),assistant.getAttendance()).get(0),assistant);
    }

    @Test
    void findAll() {
        when(assistantDao.findAll()).thenReturn(Arrays.asList(assistant));
        assertEquals(assistantImpl.findAll().get(0),assistant);
    }



    @Test
    void countAttendees() {
        when(assistantDao.countAllByAssistantAndAndAttendance("ruben",true)).thenReturn(10);
        assertEquals(assistantImpl.countAttendees("ruben", true),10);
    }
}