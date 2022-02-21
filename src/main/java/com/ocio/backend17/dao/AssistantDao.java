package com.ocio.backend17.dao;

import com.ocio.backend17.entities.Assistants;
import com.ocio.backend17.entities.AssistantsPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssistantDao extends CrudRepository<Assistants, AssistantsPK> {
    @Query("Select u from Assistants u where u.event_id=?1 and u.attendance=?2")
    List<Assistants> findAllByEvent_idAndAttendance(Double event_id, Boolean attendee);

    int countAllByAssistantAndAndAttendance(String email, Boolean attendee);
}
