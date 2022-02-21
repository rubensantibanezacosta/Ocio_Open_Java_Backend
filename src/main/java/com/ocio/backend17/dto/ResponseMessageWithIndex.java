package com.ocio.backend17.dto;

public class ResponseMessageWithIndex {
    private String message;
    private int index;

    public ResponseMessageWithIndex(String message) {
        this.message = message;
    }

    public ResponseMessageWithIndex(String message, int index) {
        this.message = message;
        this.index = index;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
