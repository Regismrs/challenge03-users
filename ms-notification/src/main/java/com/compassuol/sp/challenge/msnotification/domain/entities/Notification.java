package com.compassuol.sp.challenge.msnotification.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "notifications")
public class Notification{
    @Id
    private String id;
    private String email;
    private String event;
    private String date;
}
