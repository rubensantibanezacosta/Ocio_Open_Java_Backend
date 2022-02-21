package com.ocio.backend17.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Images {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "url", nullable = false, length = 2000)
    private String url;
    @Basic
    @Column(name = "createdat", nullable = false)
    private Date createdAt;
    @Basic
    @Column(name = "updatedat", nullable = false)
    private Date updatedAt;
    @OneToMany(mappedBy = "imagesByImageId")
    private Collection<Events> eventsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        Images images = (Images) o;
        return id == images.id && Objects.equals(url, images.url) && Objects.equals(createdAt, images.createdAt) && Objects.equals(updatedAt, images.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, createdAt, updatedAt);
    }

    public Collection<Events> getEventsById() {
        return eventsById;
    }

    public void setEventsById(Collection<Events> eventsById) {
        this.eventsById = eventsById;
    }
}
