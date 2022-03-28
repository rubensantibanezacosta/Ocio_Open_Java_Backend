package com.ocio.backend17.utils;

import org.springframework.stereotype.Component;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateFormatterSQL {
    DateFormat dateFormatterDate = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat dateFormatterDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DateFormat dateFormatterShortDate = new SimpleDateFormat("YY-MM-dd");

    public java.sql.Date todaySQLFormat() {
        return java.sql.Date.valueOf(dateFormatterDate.format(new Date()));
    }

    public java.sql.Timestamp nowTimestampSQLFormat() {
        return java.sql.Timestamp.valueOf(dateFormatterDateTime.format(new Date()));
    }

    public java.sql.Date dateToSQLFormat(String date) {
        return java.sql.Date.valueOf(dateFormatterDate.format(Date.parse(date)));
    }

    public java.sql.Timestamp nowTimestampSQLFormat(Date date) {
        return java.sql.Timestamp.valueOf(dateFormatterDateTime.format(date.getTime()));
    }

    public String timestampSQLtoShortDateString(java.sql.Timestamp dateTime){
        return dateFormatterShortDate.format(dateTime);
    }
}
