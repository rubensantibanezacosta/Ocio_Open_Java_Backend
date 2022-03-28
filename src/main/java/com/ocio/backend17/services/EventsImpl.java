package com.ocio.backend17.services;

import com.ocio.backend17.dao.EventsDao;
import com.ocio.backend17.entities.Events;
import com.ocio.backend17.utils.DateFormatterSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventsImpl implements IEvents {
    @Autowired
    EventsDao eventsDao;

    @Autowired
    DateFormatterSQL dateFormatterSQL;

    @Override
    public Events createEvent(Events event) {
        event.setCreatedAt(dateFormatterSQL.todaySQLFormat());
        event.setUpdatedAt(dateFormatterSQL.todaySQLFormat());
        return eventsDao.save(event);
    }

    @Override
    public List<Events> findAllEventsDesc() {
        return eventsDao.findAllOrderDesc();
    }

    @Override
    public List<Events> findAllEventsAsc() {
        return eventsDao.findAllOrderAsc();
    }

    @Override
    public List<Events> findAllByDate(Date date) {


        Timestamp dateTime = dateFormatterSQL.nowTimestampSQLFormat(date);
        Timestamp dateTimeFinish = new Timestamp(dateTime.getTime()+((23*60*60*1000)+(59*60*1000)+(59*1000)+900));



        return eventsDao.findAllByDate(dateTime, dateTimeFinish);
    }

    @Override
    public List<Events> findEventsByOrganizerAsc(String organizer) {
        return eventsDao.findAllByOrganizerOrderByDateAsc(organizer);
    }

    @Override
    public List<Events> findEventsByOrganizerDesc(String organizer) {
        return eventsDao.findAllByOrganizerOrderByDateDesc(organizer);
    }

    @Override
    public List<Events> findEventsByZone(String zone) {
        List<Events> eventsByZone = eventsDao.findAllByZone(zone);
        List<Events> pastEventsByZone = new ArrayList<>();
        for (int i = 0; i < eventsByZone.size(); i++) {
            if(eventsByZone.get(i).getDate().before(new Timestamp(new Date().getTime()))){
                pastEventsByZone.add(eventsByZone.get(i));
            }
        }
        return pastEventsByZone;
    }

    @Override
    public Optional<Events> findEventById(double event_id) {
        return eventsDao.findById(event_id);
    }

    @Transactional
    @Override
    public int updateEvent(Events event) {
        
        if (eventsDao.findById(event.getEvent_id()).isPresent()) {
            Events oldEvent = eventsDao.findById(event.getEvent_id()).get();
            oldEvent.setDate(event.getDate());
            oldEvent.setDescription(event.getDescription());
            oldEvent.setImage_id(event.getImage_id());
            oldEvent.setDate(event.getDate());
            oldEvent.setTittle(event.getTittle());
            oldEvent.setPlace(event.getPlace());
            oldEvent.setZone(event.getZone());
            oldEvent.setUpdatedAt(dateFormatterSQL.todaySQLFormat());
            oldEvent.setDescription(event.getDescription());
            eventsDao.save(oldEvent);
            return 1;

        }
        return 0;
    }

    @Transactional
    @Override
    public int updateEventPunctuationAvg(double event_id, double punctuation_avg) {
        if (eventsDao.findById(event_id).isPresent()) {
            Events event = eventsDao.findById(event_id).get();
            event.setPunctuationAvg(punctuation_avg);
            return 1;
        }
        return 0;
    }

    @Transactional
    @Override
    public int deleteEvent(Double event_id) {
        if (eventsDao.findById(event_id).isPresent()) {
            eventsDao.deleteById(event_id);
            return 1;
        }
        return 0;
    }

}
