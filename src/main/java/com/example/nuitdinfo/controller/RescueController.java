package com.example.nuitdinfo.controller;

import com.example.nuitdinfo.dto.InputDTO;
import com.example.nuitdinfo.dto.PaginationDTO;
import com.example.nuitdinfo.dto.RescueDTO;
import com.example.nuitdinfo.entity.Rescue;
import com.example.nuitdinfo.exception.BadIdException;
import com.example.nuitdinfo.service.RescueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rescue")
@CrossOrigin
public class RescueController {

    @Autowired
    RescueService rescueService;

    @GetMapping("")
    private ResponseEntity<PaginationDTO> getAll(@RequestParam Integer page, @RequestParam Integer limit){
        return ResponseEntity.ok(rescueService.getRescues(page, limit));
    }

    @PostMapping("")
    private ResponseEntity<Rescue> create(@RequestBody RescueDTO rescueDTO) {
        return ResponseEntity.ok(rescueService.createRescue(rescueDTO));
    }


    @GetMapping("/{id}")
    private ResponseEntity<Rescue> create(@PathVariable String id) throws BadIdException {
        return ResponseEntity.ok(rescueService.getById(id));
    }

    @PutMapping("/{id}")
    private ResponseEntity<Rescue> update(@RequestBody RescueDTO rescueDTO,@PathVariable String id) {
        return ResponseEntity.ok(rescueService.createRescue(rescueDTO));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Rescue> delete(@PathVariable String id){
        return ResponseEntity.ok(rescueService.delete(id));
    }

    @PostMapping("/seed")
    private ResponseEntity<RescueDTO[]> seedData(@RequestParam MultipartFile file){
        return ResponseEntity.ok(rescueService.seed(file));
    }

    @PostMapping("/sound/{id}")
    private ResponseEntity<Rescue> updateSound(@PathVariable String id,@RequestParam MultipartFile file ) throws IOException, BadIdException {
        return ResponseEntity.ok(rescueService.addSound(id,file));
    }


}
