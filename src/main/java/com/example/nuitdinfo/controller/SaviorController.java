package com.example.nuitdinfo.controller;

import com.example.nuitdinfo.dto.PaginationDTO;
import com.example.nuitdinfo.dto.RescueDTO;
import com.example.nuitdinfo.entity.Savior;
import com.example.nuitdinfo.exception.BadIdException;
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
    public ResponseEntity<Savior[]> seedData(@RequestParam MultipartFile file){
        return ResponseEntity.ok(saviorService.seed(file));
    }

    @GetMapping("")
    public ResponseEntity<PaginationDTO> getAll(@RequestParam Integer page, @RequestParam Integer limit){
        return ResponseEntity.ok(saviorService.getAll(page, limit));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Savior> getOne(@PathVariable String id) throws BadIdException {
        return ResponseEntity.ok(saviorService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<Savior> create(@RequestBody Savior savior){
        return ResponseEntity.ok(saviorService.create(savior));
    }



    @PutMapping("/{id}")
    public ResponseEntity<Savior> update(@PathVariable String id,@RequestBody Savior savior) throws BadIdException {
        return ResponseEntity.ok(saviorService.update(id, savior));
    }



}
