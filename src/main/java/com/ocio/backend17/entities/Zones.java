package com.ocio.backend17.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Zones {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, length = 7)
    private String id;
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Basic
    @Column(name = "punctuationavg", nullable = true, precision = 0)
    private Double punctuationavg;
    @Basic
    @Column(name = "createdat", nullable = false)
    private Date createdAt;
    @Basic
    @Column(name = "updatedat", nullable = false)
    private Date updatedAt;

    public Zones(String id, String name, Double punctuationavg, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.punctuationavg = punctuationavg;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Zones() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPunctuationavg() {
        return punctuationavg;
    }

    public void setPunctuationavg(Double punctuationavg) {
        this.punctuationavg = punctuationavg;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zones zones = (Zones) o;
        return Objects.equals(id, zones.id) && Objects.equals(name, zones.name) && Objects.equals(punctuationavg, zones.punctuationavg) && Objects.equals(createdAt, zones.createdAt) && Objects.equals(updatedAt, zones.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, punctuationavg, createdAt, updatedAt);
    }
}
