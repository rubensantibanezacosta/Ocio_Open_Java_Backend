package com.ocio.backend17.dto;

import com.ocio.backend17.entities.Users;

import java.sql.Date;
import java.sql.Timestamp;

public class UsersDto {
    private String email;
    private String name;
    private String surname;
    private String image_url;
    private Timestamp lastconnection;
    private Date createdAt;
    private Double punctuation_avg;

    public UsersDto(Users user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.image_url = user.getImage_url();
        this.lastconnection = user.getLastconnection();
        this.createdAt = user.getCreatedAt();
        this.punctuation_avg = user.getPunctuation_avg();
    }

    public UsersDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Timestamp getLastconnection() {
        return lastconnection;
    }

    public void setLastconnection(Timestamp lastconnection) {
        this.lastconnection = lastconnection;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Double getPunctuation_avg() {
        return punctuation_avg;
    }

    public void setPunctuation_avg(Double punctuation_avg) {
        this.punctuation_avg = punctuation_avg;
    }
}
