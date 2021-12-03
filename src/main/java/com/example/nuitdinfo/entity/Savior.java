package com.example.nuitdinfo.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
public class Savior {
    @Id
    private String id;
    private String name;
    private String birthDate;
    private String deathDate;
    private String job;
    private String description;
}
