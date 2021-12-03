package com.example.nuitdinfo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class Rescue {
    @Id
    private String id;
    private String title;
    private String date ;
    private Integer savednumber;
    private Integer savednrewnumber;
    private String description ;
    private String source ;
    private String location ;
    private Integer death;
    private Boolean approved = false;

    @DBRef
    private Boat boat;



}
