package com.ocio.backend17.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocio.backend17.dto.ResponseMessage;
import com.ocio.backend17.entities.Punctuations;
import com.ocio.backend17.entities.PunctuationsPK;
import com.ocio.backend17.security.ExtractHeaderData;
import com.ocio.backend17.services.PunctuationsImpl;
import com.ocio.backend17.utils.AvgPunctuationUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PunctuationsController {

    @Autowired
    PunctuationsImpl punctuationsImpl;

    @Autowired
    ExtractHeaderData extractHeaderData;

    @Autowired
    AvgPunctuationUpdater avgPunctuationUpdater;

    @PreAuthorize("hasAnyAuthority('create:punctuations','update:punctuations')")
    @PostMapping(value = "/api/punctuations", consumes = "application/json")
    @Transactional
    @ResponseBody
    ResponseEntity<?> createOrUpdatePuntuation(@RequestBody String jsonPunctuation, @RequestHeader HttpHeaders headers) {
        try {
            ObjectMapper om = new ObjectMapper();
            Punctuations punctuation = om.readValue(jsonPunctuation, Punctuations.class);
            if (!(punctuation.getPunctuation() > 0) || !(punctuation.getEvent_id() > 0)) {
                return new ResponseEntity<>(new ResponseMessage("Fields cannot be empty"), HttpStatus.BAD_REQUEST);
            } else {
                punctuation.setAssistant(extractHeaderData.extractJWTUsername(headers));
                ResponseEntity response = new ResponseEntity<>(punctuationsImpl.createOrUpdate(punctuation), HttpStatus.CREATED);
                avgPunctuationUpdater.updateAllPunctuations(punctuation.getEvent_id());
                return response;
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('read:punctuations')")
    @GetMapping("/api/punctuations")
    @ResponseBody
    ResponseEntity<?> findAllPunctuations() {
        try {
            return new ResponseEntity<>(punctuationsImpl.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('read:punctuations')")
    @GetMapping("/api/punctuations/byevent/{event_id}")
    @ResponseBody
    ResponseEntity<?> findPunctuationsByEvent(@PathVariable("event_id") Double event_id) {
        try {
            return new ResponseEntity<>(punctuationsImpl.findByEvent(event_id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('read:punctuations')")
    @GetMapping("/api/punctuations/byorganizer/{organizer}")
    @ResponseBody
    ResponseEntity<?> findPunctuationsByEvent(@PathVariable("organizer") String organizer) {
        try {
            return new ResponseEntity<>(punctuationsImpl.findByOrganizer(organizer), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('read:punctuations')")
    @GetMapping("/api/punctuations/bypk/{event_id}/{assistant}")
    @ResponseBody
    ResponseEntity<?> findPunctuationsByPk(@PathVariable("event_id") Double event_id, @PathVariable("assistant") String assistant) {

        try {
            List<Punctuations> punctuationsList = new ArrayList<>();
            if (punctuationsImpl.findByPK(new PunctuationsPK(event_id, assistant)).isPresent()) {
                punctuationsList.add(punctuationsImpl.findByPK(new PunctuationsPK(event_id, assistant)).get());
                return new ResponseEntity<>(punctuationsList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(punctuationsList, HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
