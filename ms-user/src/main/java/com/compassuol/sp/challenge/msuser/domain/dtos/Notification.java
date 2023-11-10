package com.compassuol.sp.challenge.msuser.domain.dtos;

import com.compassuol.sp.challenge.msuser.domain.enums.EventsEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class Notification implements Serializable {
    @JsonProperty("email")
    private String email;
    @JsonProperty("event")
    private EventsEnum event;
    @JsonProperty("date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String date;

    public Notification(String email, EventsEnum event) {
        this.email = email;
        this.event = event;
        this.date = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
