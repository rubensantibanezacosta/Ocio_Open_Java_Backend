package com.ocio.backend17.services;

import com.ocio.backend17.entities.Assistants;
import com.ocio.backend17.entities.AssistantsPK;

import java.util.List;
import java.util.Optional;

public interface IAsisstant {
    Assistants add(Assistants assistant);

    Optional<Assistants> findByPk(AssistantsPK assistantsPK);

    List<Assistants> findByEventAndAttendance(Double event_id, Boolean attendee);

    List<Assistants> findAll();

    int updateAttendee(AssistantsPK assistantsPK, Boolean attendeee);

    int updateAssistant(Assistants assistants);

    int deleteAssistant(AssistantsPK assistantsPK);

    int countAttendees(String email, Boolean attendee);

}
