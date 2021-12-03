package com.example.nuitdinfo.service;

import com.example.nuitdinfo.dto.PaginationDTO;
import com.example.nuitdinfo.entity.Boat;
import com.example.nuitdinfo.entity.Rescue;
import com.example.nuitdinfo.exception.BadIdException;
import com.example.nuitdinfo.repository.BoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoatService {

    @Autowired
    BoatRepository boatRepository;

    public PaginationDTO getAll(Integer page,Integer limit){
        Page<Boat> boats = boatRepository.findAll(PageRequest.of(page,limit));

        PaginationDTO paginationDTO = new PaginationDTO();

        paginationDTO.setTotal(boats.getTotalElements());
        paginationDTO.setPage(page);
        paginationDTO.setLimit(limit);
        paginationDTO.setData(boats.getContent());

        return paginationDTO;
    }

    public Boat getById(String id) throws BadIdException {
        Optional<Boat> boat =  boatRepository.findById(id);

        if (!boat.isPresent())
            throw new BadIdException("Boat not found");

        return boat.get();
    }




}
