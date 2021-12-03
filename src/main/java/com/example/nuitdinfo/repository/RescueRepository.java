package com.example.nuitdinfo.repository;

import com.example.nuitdinfo.entity.Rescue;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RescueRepository extends MongoRepository<Rescue,String> {
    Rescue deleteRescueById(String id);
}
