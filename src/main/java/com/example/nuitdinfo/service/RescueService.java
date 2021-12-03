package com.example.nuitdinfo.service;

import com.example.nuitdinfo.dto.InputDTO;
import com.example.nuitdinfo.dto.PaginationDTO;
import com.example.nuitdinfo.dto.RescueDTO;
import com.example.nuitdinfo.entity.Boat;
import com.example.nuitdinfo.entity.Participation;
import com.example.nuitdinfo.entity.Rescue;
import com.example.nuitdinfo.entity.Savior;
import com.example.nuitdinfo.exception.BadIdException;
import com.example.nuitdinfo.repository.BoatRepository;
import com.example.nuitdinfo.repository.ParticipationRepository;
import com.example.nuitdinfo.repository.RescueRepository;
import com.example.nuitdinfo.repository.SaviorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.json.JsonObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RescueService {


    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SaviorRepository saviorRepository;

    @Autowired
    RescueRepository rescueRepository;

    @Autowired
    BoatRepository boatRepository;

    @Autowired
    ParticipationRepository participationRepository;

    public Rescue getById(String id) throws BadIdException {
        Optional<Rescue> rescue =  rescueRepository.findById(id);

        if (rescue.isEmpty())
            throw new BadIdException("Rescue not found");

        return rescue.get();
    }

    public Rescue createFromScrappingRescue(RescueDTO rescueDTO){

        Rescue rescue = modelMapper.map(rescueDTO,Rescue.class);

        Boat boat = new Boat();

        if (rescueDTO.getBoat() != null){
            boat.setName(rescueDTO.getBoat());
        }

        boatRepository.save(boat);

        rescue.setBoat(boat);

        rescueRepository.save(rescue);

        if (rescueDTO.getNames() != null)
            rescueDTO.getNames().forEach(person -> {
                Optional<Savior> savior = saviorRepository.findSaviorByName(person);

                if (savior.isPresent()){
                    Participation participation = new Participation();
                    participation.setSavior(savior.get());
                    participation.setRescue(rescue);
                    participationRepository.save(participation);
                }else{
                    Savior savior1 = new Savior();
                    savior1.setName(person);
                    saviorRepository.save(savior1);
                    Participation participation = new Participation();
                    participation.setSavior(savior1);
                    participation.setRescue(rescue);
                    participationRepository.save(participation);
                }


            });

        return  rescue;
    }


    public Rescue createRescue(RescueDTO rescueDTO){

        Rescue rescue = modelMapper.map(rescueDTO,Rescue.class);

        Optional<Boat> boat = boatRepository.findById(rescueDTO.getBoat());

        boat.ifPresent(rescue::setBoat);

        rescueRepository.save(rescue);
        if (rescueDTO.getNames() != null)
            rescueDTO.getNames().forEach(person -> {
                Optional<Savior> savior = saviorRepository.findById(person);

                if (savior.isPresent()){
                    Participation participation = new Participation();
                    participation.setSavior(savior.get());
                    participation.setRescue(rescue);
                    participationRepository.save(participation);
                }


            });

        return  rescue;
    }


    public Rescue updateRescue(RescueDTO rescueDTO){

        Rescue rescue = modelMapper.map(rescueDTO,Rescue.class);
        Optional<Boat> boat = boatRepository.findById(rescueDTO.getBoat());
        boat.ifPresent(rescue::setBoat);
        rescueRepository.save(rescue);


        rescueDTO.getNames().forEach(person -> {
            Optional<Savior> savior = saviorRepository.findById(person);
            if (savior.isPresent()){
                participationRepository.deleteByRescueIdAndSaviorId(rescue.getId(),savior.get().getId());
                Participation participation = new Participation();
                participation.setSavior(savior.get());
                participation.setRescue(rescue);
                participationRepository.save(participation);
            }


        });

        return  rescue;
    }

    public PaginationDTO getRescues (Integer page , Integer limit){

        Page<Rescue> rescues = rescueRepository.findAll(PageRequest.of(page,limit));
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPage(page);
        paginationDTO.setLimit(limit);
        paginationDTO.setTotal(rescues.getTotalElements());
        paginationDTO.setData(rescues.getContent());

        return paginationDTO;

    }


    public Rescue delete(String id){
        return rescueRepository.deleteRescueById(id);
    }

    public RescueDTO[] seed(MultipartFile file) {

        ObjectMapper objectMapper = new ObjectMapper();

        RescueDTO[] rescueDTOS = null;


        try {
            rescueDTOS = objectMapper.readValue(file.getBytes(), RescueDTO[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


        Arrays.stream(rescueDTOS).forEach(rescueDTO -> {
            createFromScrappingRescue(rescueDTO);
        });

        return rescueDTOS;
    }
}
