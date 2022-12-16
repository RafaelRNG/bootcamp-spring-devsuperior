package com.devsuperior.bds02.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public class Format {

    private String title;
    private String message;
    private Instant date;

    public Format() {}

    public Format(String title, String message, Instant date) {
        this.title = title;
        this.message = message;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }
}