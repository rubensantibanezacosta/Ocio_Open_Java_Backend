package com.ocio.backend17.controllers;


import com.ocio.backend17.dto.ResponseMessage;
import com.ocio.backend17.services.FileImpl;
import com.ocio.backend17.services.ImageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@CrossOrigin(origins = "https://ocioopen.herokuapp.com")
@RestController
public class ImagesController {
    @Autowired
    ImageImpl imageImpl;
    @Autowired
    FileImpl fileImpl;

    @PreAuthorize("hasAuthority('create:images')")
    @PostMapping(value = "/api/images", consumes = "multipart/form-data")
    @ResponseBody
    ResponseEntity<?> uploadImage(@RequestParam("uploadedImage") List<MultipartFile> forms, @RequestHeader HttpHeaders headers) {
        try {
            if (forms.get(0).isEmpty()) {
                return new ResponseEntity<>(new ResponseMessage("Content cannot be empty"), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(fileImpl.saveImageFile(forms.get(0)), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('read:images')")
    @GetMapping("/api/images")
    ResponseEntity<?> getImagesUrlList() {
        try {
            return new ResponseEntity<>(imageImpl.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('read:images')")
    @GetMapping("/api/images/{id}")
    ResponseEntity<?> downloadImage(@PathVariable int id) {
        try {
            return new ResponseEntity<>(fileImpl.load(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('delete:images')")
    @DeleteMapping("/api/images/{id}")
    ResponseEntity<?> deleteImage(@PathVariable int id) {
        try {
            return new ResponseEntity<>(fileImpl.load(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Unknown error: "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
