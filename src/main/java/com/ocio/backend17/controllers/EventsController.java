package com.ocio.backend17.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocio.backend17.dto.ResponseMessage;
import com.ocio.backend17.entities.Assistants;
import com.ocio.backend17.entities.Events;
import com.ocio.backend17.mailing.EmailServiceImpl;
import com.ocio.backend17.security.ExtractHeaderData;
import com.ocio.backend17.services.AssistantImpl;
import com.ocio.backend17.services.EventsImpl;
import com.ocio.backend17.utils.DateFormatterSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EventsController {
    @Autowired
    EventsImpl eventsImpl;
    @Autowired
    ExtractHeaderData extractHeaderData;
    @Autowired
    DateFormatterSQL dateFormatterSQL;
    @Autowired
    EmailServiceImpl emailService;
    @Autowired
    AssistantImpl assistantImpl;

    @PreAuthorize("hasAuthority('create:events')")
    @PostMapping(value = "/api/events", consumes = "application/json")
    @ResponseBody
    ResponseEntity<?> createEvent(@RequestBody String jsonEvent, @RequestHeader HttpHeaders headers) throws JsonProcessingException, MessagingException {

        ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Events event = om.readValue(jsonEvent, Events.class);

        if (event.getTittle().isEmpty() || event.getPlace().isEmpty() || event.getZone().isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("Fields cannot be empty"), HttpStatus.BAD_REQUEST);

        } else {
            event.setOrganizer(extractHeaderData.extractJWTUsername(headers));
            Events createdEvent = eventsImpl.createEvent(event);

            emailService.newEventToOrganizer(createdEvent.getOrganizer(), createdEvent);
            emailService.newEventToAllUsers(createdEvent.getOrganizer(), createdEvent);

            return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
        }
    }

    @PreAuthorize("hasAuthority('read:events')")
    @GetMapping("/api/events/ASC")
    @ResponseBody
    ResponseEntity<?> findAllEventsAsc() {
        return new ResponseEntity<>(eventsImpl.findAllEventsAsc(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('read:events')")
    @GetMapping("/api/events/DESC")
    @ResponseBody
    ResponseEntity<?> findAllEventsDsc() {
        return new ResponseEntity<>(eventsImpl.findAllEventsDesc(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('read:events')")
    @GetMapping("/api/events/bydate/{date}")
    @ResponseBody
    ResponseEntity<?> findAllEventsByDate(@PathVariable("date") String stringdate) {
        Date date = dateFormatterSQL.dateToSQLFormat(stringdate);
        return new ResponseEntity<>(eventsImpl.findAllByDate(date), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('read:events')")
    @GetMapping("/api/events/byorganizer/ASC/{organizer}")
    @ResponseBody
    ResponseEntity<?> findAllEventsByOrganizerAsc(@PathVariable("organizer") String organizer) {
        return new ResponseEntity<>(eventsImpl.findEventsByOrganizerAsc(organizer), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('read:events')")
    @GetMapping("/api/events/byorganizer/DESC/{organizer}")
    @ResponseBody
    ResponseEntity<?> findAllEventsByOrganizerDesc(@PathVariable("organizer") String organizer) {
        return new ResponseEntity<>(eventsImpl.findEventsByOrganizerDesc(organizer), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('read:events')")
    @GetMapping("/api/events/byid/{event_id}")
    @ResponseBody
    ResponseEntity<?> findById(@PathVariable("event_id") Double id) {
        if (eventsImpl.findEventById(id).isPresent()) {
            return new ResponseEntity<>(eventsImpl.findEventById(id).get(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(new Assistants(), HttpStatus.OK);
        }
    }

    @PreAuthorize("hasAuthority('update:events')")
    @PutMapping(value = "/api/events", consumes = "application/json")
    @ResponseBody
    ResponseEntity<?> updateEvent(@RequestBody String jsonEvent, @RequestHeader HttpHeaders headers) throws JsonProcessingException, MessagingException {
        ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Events event = om.readValue(jsonEvent, Events.class);

        if (event.getTittle().isEmpty() || event.getPlace().isEmpty() || event.getZone().isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("Fields cannot be empty"), HttpStatus.BAD_REQUEST);

        } else {
            event.setOrganizer(extractHeaderData.extractJWTUsername(headers));
            int modified = eventsImpl.updateEvent(event);

            if (modified > 0) {
                emailService.updatedEventToOrganizer(event.getOrganizer(), event);
                emailService.updateEventToAllAssistants(event.getOrganizer(), event);

            }
            return new ResponseEntity<>(modified, HttpStatus.OK);

        }
    }

    @PreAuthorize("hasAuthority('adminupdate:events')")
    @PutMapping(value = "/api/events/admin", consumes = "application/json")
    @ResponseBody
    ResponseEntity<?> updateEventAdmin(@RequestBody String jsonEvent, @RequestHeader HttpHeaders headers)
            throws JsonProcessingException, MessagingException {
        ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Events event = om.readValue(jsonEvent, Events.class);

        if (event.getTittle().isEmpty() || event.getPlace().isEmpty() || event.getZone().isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("Fields cannot be empty"), HttpStatus.BAD_REQUEST);

        } else {
            int modified = eventsImpl.updateEvent(event);

            if (modified > 0) {
                emailService.updatedEventToOrganizer(event.getOrganizer(), event);
                emailService.updateEventToAllAssistants(event.getOrganizer(), event);

            }
            return new ResponseEntity<>(modified, HttpStatus.OK);
        }
    }

    @PreAuthorize("hasAuthority('delete:events')")
    @DeleteMapping("/api/events/{event_id}")
    @ResponseBody
    ResponseEntity<?> deleteById(@PathVariable("event_id") Double id, @RequestHeader HttpHeaders headers) throws MessagingException {
        if (eventsImpl.findEventById(id).isPresent()) {
            Events events = eventsImpl.findEventById(id).get();


            if (events.getOrganizer().equals(extractHeaderData.extractJWTUsername(headers))) {
                List<Assistants> assistantsList=assistantImpl.findByEventAndAttendance(id, true);

                int deleted = eventsImpl.updateEvent(events);
                if (deleted > 0) {
                    emailService.deletedEventToOrganizer(events.getOrganizer(), events);
                    emailService.deletedEventToAllAssistants(events.getOrganizer(),events, assistantsList);

                }
                return new ResponseEntity<>(eventsImpl.deleteEvent(id), HttpStatus.OK);

            } else {
                return new ResponseEntity<>(new ResponseMessage("Only organizer cans delete his events"), HttpStatus.UNAUTHORIZED);

            }
        }
        return new ResponseEntity<>(new ResponseMessage("Event not found"), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('admindelete:events')")
    @DeleteMapping("/api/events/admin/{event_id}")
    @ResponseBody
    ResponseEntity<?> deleteByIdAdmin(@PathVariable("event_id") Double id, @RequestHeader HttpHeaders headers) {
        if (eventsImpl.findEventById(id).isPresent()) {
            Events events = eventsImpl.findEventById(id).get();
            return new ResponseEntity<>(eventsImpl.deleteEvent(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("Event not found"), HttpStatus.OK);
    }

}
