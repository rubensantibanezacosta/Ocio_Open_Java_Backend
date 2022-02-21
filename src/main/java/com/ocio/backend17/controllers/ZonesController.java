package com.ocio.backend17.controllers;

import com.ocio.backend17.entities.Zones;
import com.ocio.backend17.services.ZonesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ZonesController {
    @Autowired
    ZonesImpl zonesImpl;

    @PreAuthorize("hasAuthority('read:zones')")
    @GetMapping("/api/zones")
    public ResponseEntity<List<Zones>> getAll() {
        return new ResponseEntity(zonesImpl.getAll(), HttpStatus.OK);
    }
}
