package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.DTO.ImmobileDTO;
import com.example.demo.client.ImmobileClient;
import com.example.demo.entity.Corso;

import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.utils.CorsoConverter;

import com.example.demo.utils.DocenteConverter;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CorsoService {

    private final CorsoRepository corsoRepository;
    @Autowired
    private DocenteRepository docenteRepository;

    private final ImmobileClient immobileClient;

    public CorsoService(ImmobileClient immobileClient, CorsoRepository corsoRepository) {
        this.corsoRepository = corsoRepository;
        this.immobileClient = immobileClient;

    }

    //OPEN FEIGN--> inizio
    public List<ImmobileDTO> fetchAllImmobili() {
        return immobileClient.getImmoobli();
    }

    public ImmobileDTO getImmobileByid(Integer id) {
        return immobileClient.getImmobileById(id);

    }

    public ImmobileDTO insertImoobile(ImmobileDTO immobileDTO) {
        return immobileClient.InsertImmobile(immobileDTO);
    }

    public ImmobileDTO updateImmobile(Integer id, ImmobileDTO immobileDTO) {
        return immobileClient.updateImmobile(id, immobileDTO);
    }


    public ImmobileDTO deleteImmobile(Integer id) {
        return immobileClient.deleteImmobile(id);
    }
    //OPEN FEIGn<--fine

    public CorsoDTO getCorsoById(Integer id) {

        Optional<Corso> corso = corsoRepository.findById(id);

        return corso.map(CorsoConverter::convertToDTO).orElse(null);
    }


    public List<CorsoDTO> getAllCorsi() {
        List<Corso> listaCorsi = corsoRepository.findAll();
        List<CorsoDTO> DTOCorsi = new ArrayList<>();

        for (Corso corso : listaCorsi) {
            CorsoDTO newDTO = CorsoConverter.convertToDTO(corso);


            DTOCorsi.add(newDTO);
        }
        return DTOCorsi;
    }

    public CorsoDTO insertCorso(CorsoDTO corsoDTO) {
        Optional<Docente> doc = docenteRepository.findById(corsoDTO.getDocenteDTO().getId());

        Corso corso = CorsoConverter.convertToEntity(corsoDTO);

        if (doc.isPresent()) {
            Docente docente = doc.get();
            corso.setDocente(docente);
            //      docente.addCorso(corso);
        }
        Corso CorsoSaved = corsoRepository.save(corso);
        return CorsoConverter.convertToDTO(CorsoSaved);
    }

    public CorsoDTO updateCorso(Integer id, CorsoDTO DTO) {

        Optional<Corso> corso = corsoRepository.findById(id);
        if (corso.isPresent()) {
            DTO.setId(id);
            Corso corsoSaved = CorsoConverter.convertToEntity(DTO);
            corsoRepository.save(corsoSaved);
            return CorsoConverter.convertToDTO(corsoSaved);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public CorsoDTO deleteCorsoById(Integer id) {
        Optional<Corso> corsoOpt = corsoRepository.findById(id);

        if (corsoOpt.isPresent()) {
            Corso corso = corsoOpt.get();

            for (Discente discente : corso.getListaDiscenti()) {
                discente.getListaCorsi().remove(corso);
            }

            corso.getListaDiscenti().clear();
            Docente docente = corso.getDocente();
            docente.getListaCorsi().remove(corso);


            CorsoDTO corsoDTODeleted = CorsoConverter.convertToDTO(corso);

            corsoRepository.deleteById(id);
            return corsoDTODeleted;
        } else {

            throw new EntityNotFoundException();
        }

    }
}
