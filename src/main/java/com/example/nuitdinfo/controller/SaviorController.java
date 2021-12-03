package com.example.nuitdinfo.controller;

import com.example.nuitdinfo.dto.RescueDTO;
import com.example.nuitdinfo.entity.Savior;
import com.example.nuitdinfo.service.SaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/savior")
@CrossOrigin
public class SaviorController {

    @Autowired
    SaviorService saviorService;
    @PostMapping("/seed")
    private ResponseEntity<Savior[]> seedData(@RequestParam MultipartFile file){
        return ResponseEntity.ok(saviorService.seed(file));
    }
}
