package com.ocio.backend17.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class AssistantsPK implements Serializable {
    @Column(name = "event_id", nullable = false, precision = 0)
    @Id
    private double event_id;
    @Column(name = "assistant", nullable = false, length = 200)
    @Id
    private String assistant;

    public AssistantsPK(double eventId, String assistant) {
        this.event_id = eventId;
        this.assistant = assistant;
    }

    public AssistantsPK() {
    }

    public double getEvent_id() {
        return event_id;
    }

    public void setEvent_id(double eventId) {
        this.event_id = eventId;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssistantsPK that = (AssistantsPK) o;
        return Double.compare(that.event_id, event_id) == 0 && Objects.equals(assistant, that.assistant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(event_id, assistant);
    }
}
