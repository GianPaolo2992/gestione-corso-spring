package com.example.demo.utils;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.service.CorsoService;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CorsoConverter {


    public static List<CorsoDTO> convertListToDTO(List<Corso> corsi) {
        List<CorsoDTO> listaCorsoDTO = new ArrayList<>();
        for (Corso corso : corsi) {
            CorsoDTO corsoDTO = new CorsoDTO();
            corsoDTO.setid(corso.getid());
            corsoDTO.setNomeCorso(corso.getNomeCorso());
            corsoDTO.setDatainizio(corso.getDataInizio());
            corsoDTO.setDurata(corso.getDurata());
//            corsoDTO.setDocente(corso.getDocente());
            List<DiscenteDTO> discenteDTOList = DiscentiConverter.convertListToEntity(corso.getListaDiscenti());


        }

        return listaCorsoDTO;
    }

    public static CorsoDTO convertToDTO(Corso corso) {
        CorsoDTO corsoDTO = new CorsoDTO();
        corsoDTO.setid(corso.getid());
        corsoDTO.setNomeCorso(corso.getNomeCorso());
        corsoDTO.setDatainizio(corso.getDataInizio());
        corsoDTO.setDurata(corso.getDurata());
        if (corso.getDocente() != null) {
            corsoDTO.setDocenteDTO(DocenteConverter.convertToDTO(corso.getDocente()));
        }else {
            throw new EntityNotFoundException("Docente not found");
        }

        List<Discente> listaDiscenti = corso.getListaDiscenti();
        if (listaDiscenti != null) {
            List<DiscenteDTO> listaDiscentiDTO = DiscentiConverter.convertListToEntity(listaDiscenti);
            corsoDTO.setListaDiscentiDTO(listaDiscentiDTO);
        }
        return corsoDTO;
    }

    public static Corso convertToEntity(CorsoDTO corsoDTO) {

        Corso corso = new Corso();
        corso.setid(corsoDTO.getid());
        corso.setNomeCorso(corsoDTO.getNomeCorso());
        corso.setDatainizio(corsoDTO.getDataInizio());
        corso.setDurata(corsoDTO.getDurata());


            if (corsoDTO.getDocenteDTO()!=null) {
                corso.setDocente(DocenteConverter.toEntity(corsoDTO.getDocenteDTO()));
            } else {
                throw new EntityNotFoundException("Docente not found");
            }

            List<DiscenteDTO> listaDiscentiDTO = corsoDTO.getListaDiscentiDTO();
            if (listaDiscentiDTO != null) {
                List<Discente> listaDiscenti = DiscentiConverter.convertListToDTO(listaDiscentiDTO);
                corso.setListaDiscenti(listaDiscenti);
            }

        return corso;
    }
}

