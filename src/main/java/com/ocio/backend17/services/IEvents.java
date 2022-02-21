package com.ocio.backend17.services;

import com.ocio.backend17.entities.Events;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IEvents {
    public Events createEvent(Events event);

    List<Events> findAllEventsDesc();

    List<Events> findAllEventsAsc();

    List<Events> findAllByDate(Date date);

    List<Events> findEventsByOrganizerAsc(String organizer);

    List<Events> findEventsByOrganizerDesc(String organizer);

    List<Events> findEventsByZone(String zone);

    Optional<Events> findEventById(double event_id);

    int updateEvent(Events event);

    int updateEventPunctuationAvg(double event_id, double punctuation_avg);

    int deleteEvent(Double event_id);

}
