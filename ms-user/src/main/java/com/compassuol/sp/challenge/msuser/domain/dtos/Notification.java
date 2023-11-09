package com.compassuol.sp.challenge.msuser.domain.dtos;

import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Notification implements Serializable {
    private String email;
    private String event;
    private String date;

    public Notification(String email, String event) {
        this.email = email;
        this.event = event;
        this.date = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
