package com.ocio.backend17.controllers;

import com.ocio.backend17.dto.ResponseMessage;
import com.ocio.backend17.dto.UsersReportDto;
import com.ocio.backend17.services.UsersReportImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ReportsController {
    @Autowired
    UsersReportImpl usersReportImpl;

    @PreAuthorize("hasAuthority('read:users')")
    @GetMapping("/api/user/admin/report")
    public ResponseEntity<?> getUsersReport() {
        try {
            UsersReportDto usersReportDto = usersReportImpl.usersReportDto();
            InputStreamResource inputStreamResource = new InputStreamResource(usersReportDto.getStream());
            MediaType mediaType = MediaType.APPLICATION_PDF;
            return ResponseEntity.status(200).header("Content disposition", "inline; filename=\"" + usersReportDto.getFileName() + "\"").contentLength(usersReportDto.getLenght()).contentType(mediaType).body(inputStreamResource);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
