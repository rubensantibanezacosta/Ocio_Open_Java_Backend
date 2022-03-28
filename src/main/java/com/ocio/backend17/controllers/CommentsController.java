package com.ocio.backend17.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocio.backend17.dto.ResponseMessage;
import com.ocio.backend17.dto.ResponseMessageWithIndex;
import com.ocio.backend17.entities.Comments;

import com.ocio.backend17.security.ExtractHeaderData;
import com.ocio.backend17.services.CommentsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;




@RestController
@CrossOrigin(origins = "${value.frontend.host}")
public class CommentsController {
    @Autowired
    CommentsImpl commentsImpl;
    @Autowired
    ExtractHeaderData extractHeaderData;


    @PreAuthorize("hasAuthority('create:comments')")
    @PostMapping(value = "/api/comments", consumes = "application/json")
    @ResponseBody
    ResponseEntity<?> createOrUpdateComment(@RequestBody String jsonComment, @RequestHeader HttpHeaders headers) {
        try {
            ObjectMapper om = new ObjectMapper();
            Comments comment = om.readValue(jsonComment, Comments.class);
            if (!(comment.getEvent_id() > 0)) {
                return new ResponseEntity<>(new ResponseMessage("Fields cannot be empty"), HttpStatus.BAD_REQUEST);
            } else {
                comment.setAssistant(extractHeaderData.extractJWTUsername(headers));
                return new ResponseEntity<>(commentsImpl.addComment(comment), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('read:comments')")
    @GetMapping("/api/comments/byevent/{event_id}")
    ResponseEntity<?> getAll(@PathVariable("event_id") Double event_id) {
        try {
            return new ResponseEntity<>(commentsImpl.findByEventId(event_id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PreAuthorize("hasAuthority('delete:comments')")
    @DeleteMapping("/api/comments/{comment_id}/{index}")
    @ResponseBody
    ResponseEntity<?> deleteByEmail(@PathVariable("comment_id") long id, @PathVariable("index") int index, @RequestHeader HttpHeaders headers) {
        try {
            if (commentsImpl.findbyId(id).isPresent() && commentsImpl.findbyId(id).get().getAssistant()
                    .equals(extractHeaderData.extractJWTUsername(headers))) {
                return new ResponseEntity<>(new ResponseMessageWithIndex(String.valueOf(commentsImpl.deleteById(id)), index), HttpStatus.OK);
            } else if (commentsImpl.findbyId(id).isPresent() && !(commentsImpl.findbyId(id).get().getAssistant()
                    .equals(extractHeaderData.extractJWTUsername(headers)))) {
                return new ResponseEntity<>(new ResponseMessage("Only author cans delete his own comments"),
                        HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(new ResponseMessage("Comment id not found"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
