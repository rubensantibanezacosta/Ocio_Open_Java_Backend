package com.ocio.backend17.utils;


import com.ocio.backend17.entities.Events;
import com.ocio.backend17.entities.Punctuations;
import com.ocio.backend17.entities.Zones;
import com.ocio.backend17.services.EventsImpl;
import com.ocio.backend17.services.PunctuationsImpl;
import com.ocio.backend17.services.UsersImpl;
import com.ocio.backend17.services.ZonesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AvgPunctuationUpdater {
    @Autowired
    PunctuationsImpl punctuationsService;
    @Autowired
    EventsImpl eventsService;
    @Autowired
    UsersImpl usersService;
    @Autowired
    ZonesImpl zonesService;

    private void updateUserPunctuation(Double event_id) {
        String organizer = eventsService.findEventById(event_id).get().getOrganizer();
        List<Punctuations> userEventsPunctuations = punctuationsService.findByOrganizer(organizer);
        Double totalPunctuation = 0.0;
        for (int i = 0; i < userEventsPunctuations.size(); i++) {
            totalPunctuation += userEventsPunctuations.get(i).getPunctuation();
        }
        usersService.updateAvgPunctuation(totalPunctuation / userEventsPunctuations.size(), organizer);
    }

    private void updateEventPunctuation(Double event_id) {
        List<Punctuations> punctuationsOfEvent = punctuationsService.findByEvent(event_id);
        Double totalPunctuation = 0.0;
        for (int i = 0; i < punctuationsOfEvent.size(); i++) {
            totalPunctuation += punctuationsOfEvent.get(i).getPunctuation();
        }
        eventsService.updateEventPunctuationAvg(event_id, totalPunctuation / punctuationsOfEvent.size());
    }

    private void updateZonesPunctuations() {
        List<Zones> zones = zonesService.getAll();
        for (int i = 0; i < zones.size(); i++) {
            Double totalPunctuation = 0.0;
            List<Events> zoneEvents = eventsService.findEventsByZone(zones.get(i).getId());
            for (int j = 0; j < zoneEvents.size(); j++) {
                totalPunctuation += zoneEvents.get(j).getPunctuationAvg();
            }
            zonesService.updateZoneAvgPunctuation(zones.get(i).getId(), totalPunctuation);
        }

    }

    public void updateAllPunctuations(Double event_id) {
        updateEventPunctuation(event_id);
        updateUserPunctuation(event_id);
        updateZonesPunctuations();
    }
}
