package com.ocio.backend17.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateFormatterSQLTest {

    @InjectMocks
    DateFormatterSQL dateFormatterSQL;

    private Date date = new Date();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void todaySQLFormat() {
        assertNotEquals(dateFormatterSQL.todaySQLFormat(),new java.sql.Date(new Date().getTime()));
        assertEquals(dateFormatterSQL.todaySQLFormat().getClass(),new java.sql.Date(new Date().getTime()).getClass());
    }

    @Test
    void nowTimestampSQLFormat() {
        assertEquals(dateFormatterSQL.nowTimestampSQLFormat().getClass(),new java.sql.Timestamp(new Date().getTime()).getClass());
    }

}