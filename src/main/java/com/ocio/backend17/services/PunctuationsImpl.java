package com.ocio.backend17.services;

import com.ocio.backend17.dao.PunctuationsDao;
import com.ocio.backend17.entities.Events;
import com.ocio.backend17.entities.Punctuations;
import com.ocio.backend17.entities.PunctuationsPK;
import com.ocio.backend17.utils.DateFormatterSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PunctuationsImpl implements IPunctuations {

    @Autowired
    PunctuationsDao punctuationsDao;
    @Autowired
    EventsImpl iEvents;

    @Autowired
    DateFormatterSQL dateFormatterSQL;

    @Override
    public Punctuations createOrUpdate(Punctuations punctuation) {
        if (punctuationsDao.findById(new PunctuationsPK(punctuation.getEvent_id(), punctuation.getAssistant())).isPresent()) {
            Punctuations oldPunctuation = punctuationsDao.findById(new PunctuationsPK(punctuation.getEvent_id(), punctuation.getAssistant())).get();
            oldPunctuation.setPunctuation(punctuation.getPunctuation());
            oldPunctuation.setUpdatedAt(dateFormatterSQL.todaySQLFormat());
            return punctuationsDao.save(oldPunctuation);
        } else {
            Punctuations punctuationToSave = punctuation;
            punctuationToSave.setCreatedAt(dateFormatterSQL.todaySQLFormat());
            punctuationToSave.setUpdatedAt(dateFormatterSQL.todaySQLFormat());
            return punctuationsDao.save(punctuationToSave);
        }
    }

    @Override
    public List<Punctuations> findAll() {
        return (List<Punctuations>) punctuationsDao.findAll();

    }

    @Override
    public List<Punctuations> findByEvent(Double event_id) {
        return punctuationsDao.findPunctuationsByEvent_id(event_id);

    }

    @Override
    public List<Punctuations> findByOrganizer(String email) {
        List<Events> eventsByOrganizer = iEvents.findEventsByOrganizerAsc(email);
        List<Punctuations> punctuations = new ArrayList<>();
        for (Events events : eventsByOrganizer) {
            if (events.getDate().before(new Timestamp(new Date().getTime()))) {
                punctuations.addAll(punctuationsDao.findPunctuationsByEvent_id(events.getEvent_id()));
            }
        }
        return punctuations;
    }

    @Override
    public Optional<Punctuations> findByPK(PunctuationsPK punctuationsPK) {
        return punctuationsDao.findById(punctuationsPK);
    }

    @Override
    public int deleteByPk(PunctuationsPK punctuationsPK) {
        if (punctuationsDao.findById(punctuationsPK).isPresent()) {
            punctuationsDao.deleteById(punctuationsPK);
            return 1;
        }
        return 0;
    }
}
