package com.ocio.backend17.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocio.backend17.dto.EmailDto;
import com.ocio.backend17.dto.ResponseMessage;
import com.ocio.backend17.dto.UsersReportDto;
import com.ocio.backend17.mailing.EmailServiceImpl;
import com.ocio.backend17.services.UsersReportImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;


@CrossOrigin(origins = "${value.frontend.host}")
@RestController
public class ReportsController {
    @Autowired
    UsersReportImpl usersReportImpl;
    @Autowired
    EmailServiceImpl emailService;

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

    @PreAuthorize("hasAuthority('read:users')")
    @PostMapping(value="/api/user/admin/report", consumes = "application/json" )
    @ResponseBody
    public ResponseEntity<?> sendUsersReportMailTo(@RequestBody String email) {
        try {
            ObjectMapper om = new ObjectMapper();
            EmailDto emailDto= om.readValue(email, EmailDto.class);
            UsersReportDto usersReportDto = usersReportImpl.usersReportDto();
            InputStreamResource inputStreamResource = new InputStreamResource(usersReportDto.getStream());
            byte[] fileToByteArray=inputStreamResource.getInputStream().readAllBytes();
            FileOutputStream fos=new FileOutputStream(System.getProperty("user.dir")+"/src/assets/temp/usersReports.pdf");
            fos.write(fileToByteArray);
            File fileToSend=new File(System.getProperty("user.dir")+"/src/assets/temp/usersReports.pdf");
            emailService.sendUsersReportTo(emailDto.getEmail(),fileToSend);
            return new ResponseEntity<>(new ResponseMessage("Email succesfully send"),HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
