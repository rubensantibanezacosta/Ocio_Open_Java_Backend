package com.ocio.backend17.services;

import com.ocio.backend17.dao.AssistantDao;
import com.ocio.backend17.entities.Assistants;
import com.ocio.backend17.entities.AssistantsPK;
import com.ocio.backend17.utils.DateFormatterSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AssistantImpl implements IAsisstant {
    @Autowired
    AssistantDao assistantDao;
    @Autowired
    DateFormatterSQL dateFormatterSQL;

    @Override
    public Assistants add(Assistants assistant) {

        assistant.setCreatedAt(dateFormatterSQL.todaySQLFormat());
        assistant.setUpdatedAt(dateFormatterSQL.todaySQLFormat());
        return assistantDao.save(assistant);
    }

    @Override
    public Optional<Assistants> findByPk(AssistantsPK assistantsPK) {
        return assistantDao.findById(assistantsPK);
    }

    @Override
    public List<Assistants> findByEventAndAttendance(Double event_id, Boolean attendee) {
        return assistantDao.findAllByEvent_idAndAttendance(event_id, attendee);
    }

    @Override
    public List<Assistants> findAll() {
        return (List<Assistants>) assistantDao.findAll();
    }

    @Override
    public int updateAttendee(AssistantsPK assistantsPK, Boolean attendee) {
        if (assistantDao.findById(assistantsPK).isPresent()) {
            Assistants assistant = assistantDao.findById(assistantsPK).get();
            assistant.setAttendance(attendee);
            assistantDao.save(assistant);
            return 1;
        } else {
            return 0;
        }
    }

    @Transactional
    @Override
    public int updateAssistant(Assistants assistants) {
        if (assistantDao.findById(new AssistantsPK(assistants.getEvent_id(), assistants.getAssistant())).isPresent()) {
            Assistants newAssistant = assistantDao
                    .findById(new AssistantsPK(assistants.getEvent_id(), assistants.getAssistant())).get();
            newAssistant.setAttendance(assistants.getAttendance());
            newAssistant.setExcuse(assistants.getExcuse());
            newAssistant.setUpdatedAt(dateFormatterSQL.todaySQLFormat());
            assistantDao.save(newAssistant);
            return 1;
        } else {
            return 0;
        }
    }

    @Transactional
    @Override
    public int deleteAssistant(AssistantsPK assistantsPK) {
        if (assistantDao.findById(assistantsPK).isPresent()) {
            Assistants assistant = assistantDao.findById(assistantsPK).get();
            assistantDao.delete(assistant);
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int countAttendees(String email, Boolean attendee) {
        return assistantDao.countAllByAssistantAndAndAttendance(email, true);
    }


}
