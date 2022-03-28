package com.ocio.backend17.services;

import com.ocio.backend17.dao.PunctuationsDao;
import com.ocio.backend17.entities.Punctuations;
import com.ocio.backend17.entities.PunctuationsPK;
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

class PunctuationsImplTest {
    @Mock
    PunctuationsDao punctuationsDao;
    @InjectMocks
    PunctuationsImpl punctuationsImpl;
    @InjectMocks
    DateFormatterSQL dateFormatterSQL;
    @InjectMocks
    EventsImpl eventsImpl;

    private Punctuations punctuation=new Punctuations();
    private Date date = new Date();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        punctuationsImpl.dateFormatterSQL=dateFormatterSQL;
        punctuationsImpl.iEvents=eventsImpl;
        punctuation.setEvent_id(10);
        punctuation.setAssistant("Ruben");
        punctuation.setPunctuation(10);
        punctuation.setUpdatedAt(new java.sql.Date(date.getTime()));
        punctuation.setCreatedAt(new java.sql.Date(date.getTime()));
    }

    @Test
    void createOrUpdate() {
        when(punctuationsDao.save(punctuation)).thenReturn(punctuation);
        assertEquals(punctuationsImpl.createOrUpdate(punctuation),punctuation);
        assertEquals(punctuationsImpl.createOrUpdate(punctuation).getPunctuation(),punctuation.getPunctuation());
        assertEquals(punctuationsImpl.createOrUpdate(punctuation).getAssistant(),punctuation.getAssistant());
        assertEquals(punctuationsImpl.createOrUpdate(punctuation).getEvent_id(),punctuation.getEvent_id());
        assertEquals(punctuationsImpl.createOrUpdate(punctuation).getCreatedAt(),punctuation.getCreatedAt());
        assertEquals(punctuationsImpl.createOrUpdate(punctuation).getUpdatedAt(),punctuation.getUpdatedAt());
    }

    @Test
    void findAll() {
        when(punctuationsDao.findAll()).thenReturn(Arrays.asList(punctuation));
        assertEquals(punctuationsImpl.findAll().size(),1);
        assertEquals(punctuationsImpl.findAll().get(0),punctuation);
    }

    @Test
    void findByEvent() {
        when(punctuationsDao.findPunctuationsByEvent_id(10.0)).thenReturn(Arrays.asList(punctuation));
        assertEquals(punctuationsImpl.findByEvent(10.0).get(0),punctuation);
    }



    @Test
    void findByPK() {
        when(punctuationsDao.findById(new PunctuationsPK(10,"ruben"))).thenReturn(Optional.of(punctuation));
        assertEquals(punctuationsImpl.findByPK(new PunctuationsPK(10,"ruben")).get(),punctuation);
    }

}