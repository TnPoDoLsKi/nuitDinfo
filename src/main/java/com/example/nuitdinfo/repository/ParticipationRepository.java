package com.example.nuitdinfo.repository;

import com.example.nuitdinfo.entity.Participation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParticipationRepository extends MongoRepository<Participation,String> {

    void deleteByRescueIdAndSaviorId(String rescueId,String saviorId);
}
