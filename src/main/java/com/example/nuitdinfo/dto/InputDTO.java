package com.example.nuitdinfo.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InputDTO {
    private String title;
    private String date ;
    private Integer savednumber;
    private Integer savedcrewnumber;
    private String description ;
    private String source ;
    private Integer death;
    private String location ;
    private List<String> names;
    private String boat;}
