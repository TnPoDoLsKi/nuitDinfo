package com.example.nuitdinfo.service;

import com.example.nuitdinfo.dto.PaginationDTO;
import com.example.nuitdinfo.dto.RescueDTO;
import com.example.nuitdinfo.entity.Rescue;
import com.example.nuitdinfo.entity.Savior;
import com.example.nuitdinfo.exception.BadIdException;
import com.example.nuitdinfo.repository.SaviorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Service
public class SaviorService {

    @Autowired
    SaviorRepository saviorRepository;

    @Autowired
    ModelMapper modelMapper;

    public Savior create(Savior savior){
        return  saviorRepository.save(savior);
    }

    public PaginationDTO getAll(Integer page, Integer limit){
        Page<Savior> saviorPage =  saviorRepository.findAll(PageRequest.of(page,limit));

        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setData(saviorPage.getContent());
        paginationDTO.setPage(page);
        paginationDTO.setLimit(limit);
        paginationDTO.setTotal(saviorPage.getTotalElements());

        return paginationDTO;
    }

    public Savior update(String id , Savior savior) throws BadIdException {
        Savior savior1 = getById(id);
        savior1.setId(savior.getId());
        return  saviorRepository.save(savior1);
    }


    public Savior getById(String id) throws BadIdException {
        Optional<Savior> savior =  saviorRepository.findById(id);

        if (savior.isEmpty())
            throw new BadIdException("savior not found");

        return savior.get();
    }

    public Rescue delete(String id){
        return saviorRepository.deleteSaviorById(id);
    }



    public Savior[] seed(MultipartFile file) {

        ObjectMapper objectMapper = new ObjectMapper();

        Savior[] saviors = null;

        try {
            saviors = objectMapper.readValue(file.getBytes(), Savior[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


        Arrays.stream(saviors).forEach(this::create);

        return saviors;
    }

}
