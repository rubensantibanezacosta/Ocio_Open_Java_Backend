package com.ocio.backend17.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocio.backend17.dto.ResponseMessage;
import com.ocio.backend17.entities.Assistants;
import com.ocio.backend17.entities.AssistantsPK;
import com.ocio.backend17.security.ExtractHeaderData;
import com.ocio.backend17.services.AssistantImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


@RestController
public class AssistantController {
    @Autowired
    AssistantImpl iAsisstantImpl;
    @Autowired
    ExtractHeaderData extractHeaderData;


    @PreAuthorize("hasAuthority('create:assistants')")
    @PostMapping(value = "/api/assistant", consumes = "application/json")
    @ResponseBody
    ResponseEntity<?> createOrUpdateUser(@RequestBody String jsonAssistant, @RequestHeader HttpHeaders headers) {
        try {
            ObjectMapper om = new ObjectMapper();
            Assistants assistant = om.readValue(jsonAssistant, Assistants.class);
            if (!(assistant.getEvent_id() > 0)) {
                return new ResponseEntity<>(new ResponseMessage("Fields cannot be empty"), HttpStatus.BAD_REQUEST);
            } else {
                assistant.setAssistant(extractHeaderData.extractJWTUsername(headers));

                return new ResponseEntity<>(iAsisstantImpl.add(assistant), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('read:assistants')")
    @GetMapping("/api/assistant/bypk/{event_id}/{assistant}")
    ResponseEntity<?> getByPk(@PathVariable("event_id") Double event_id, @PathVariable("assistant") String assistant) {
        try {
            List<Assistants> assistants = new ArrayList<>();
            if (iAsisstantImpl.findByPk(new AssistantsPK(event_id, assistant)).isPresent()) {
                assistants.add(iAsisstantImpl.findByPk(new AssistantsPK(event_id, assistant)).get());

                return new ResponseEntity<>(assistants,
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(assistants, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('read:assistants')")
    @GetMapping("/api/assistant/byevent/{event_id}")
    ResponseEntity<?> getByEvent(@PathVariable("event_id") Double event_id) {
        try {
            
            return new ResponseEntity<>(iAsisstantImpl.findByEventAndAttendance(event_id, true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('read:assistants')")
    @GetMapping("/api/assistant/byeventnot/{event_id}")
    ResponseEntity<?> getByEventNot(@PathVariable("event_id") Double event_id) {
        try {
            return new ResponseEntity<>(iAsisstantImpl.findByEventAndAttendance(event_id, false), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('read:assistants')")
    @GetMapping("/api/assistant")
    ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(iAsisstantImpl.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('read:assistants')")
    @GetMapping("/api/assistant/count/{assistant}")
    ResponseEntity<?> countAttendees(@PathVariable("assistant") String assistant) {
        try {
            return new ResponseEntity<>(iAsisstantImpl.countAttendees(assistant, true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('update:assistants')")
    @PutMapping("/api/assistant")
    ResponseEntity<?> updateAssistant(@RequestBody String jsonAssistant, @RequestHeader HttpHeaders headers) {
        try {
            ObjectMapper om = new ObjectMapper();
            Assistants assistant = om.readValue(jsonAssistant, Assistants.class);
            assistant.setAssistant(extractHeaderData.extractJWTUsername(headers));
            return new ResponseEntity<>(iAsisstantImpl.updateAssistant(assistant), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
