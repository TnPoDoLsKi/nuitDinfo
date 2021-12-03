package com.example.nuitdinfo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Participation {
    @Id
    private String id;

    @DBRef
    private Rescue rescue;

    @DBRef
    private Savior savior;

    private String saviorType;
}
