package com.example.nuitdinfo.repository;

import com.example.nuitdinfo.entity.Boat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BoatRepository extends MongoRepository<Boat,String> {
}
