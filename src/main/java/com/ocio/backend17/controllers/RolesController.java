package com.ocio.backend17.controllers;


import com.ocio.backend17.config.IConfigImpl;
import com.ocio.backend17.dto.PermissionsDto;
import com.ocio.backend17.dto.ResponseMessage;
import com.ocio.backend17.services.RolesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://ocioopen.herokuapp.com")
@RestController
public class RolesController {
    @Autowired
    RolesImpl rolesImpl;


    @PreAuthorize("hasAuthority('update:users')")
    @GetMapping("/api/roles/admin")
    public ResponseEntity<?> getRoles() {
        try {
            if (rolesImpl.getAdminRole().isPresent()) {
                return new ResponseEntity<>(new PermissionsDto(rolesImpl.getAdminRole().get().getPermissions()), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ResponseMessage("Not found"), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
