package com.example.nuitdinfo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationDTO implements Serializable {
    private Object data;
    private Long total;
    private Integer page;
    private Integer limit;
}
