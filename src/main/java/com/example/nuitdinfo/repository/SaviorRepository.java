package com.example.nuitdinfo.repository;

import com.example.nuitdinfo.entity.Rescue;
import com.example.nuitdinfo.entity.Savior;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SaviorRepository extends MongoRepository<Savior,String> {
    Optional<Savior> findSaviorByName(String name);

}
