package com.example.demo.utils;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.CorsoDTONoDOC;
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
            corsoDTO.setId(corso.getid());
            corsoDTO.setNomeCorso(corso.getNomeCorso());
            corsoDTO.setDatainizio(corso.getDataInizio());
            corsoDTO.setDurata(corso.getDurata());
            corsoDTO.setDocenteDTO(DocenteConverter.convertToDTOXCorso(corso.getDocente()));
//            List<DiscenteDTO> discenteDTOList = DiscentiConverter.convertListToDTO(corso.getListaDiscenti());
            listaCorsoDTO.add(corsoDTO);
        }

        return listaCorsoDTO;
    }
    public static List<CorsoDTO> convertListToDTOXDiscenti(List<Corso> corsi) {
        List<CorsoDTO> listaCorsoDTO = new ArrayList<>();
        for (Corso corso : corsi) {
            CorsoDTO corsoDTO = new CorsoDTO();
            corsoDTO.setId(corso.getid());
            corsoDTO.setNomeCorso(corso.getNomeCorso());
            corsoDTO.setDatainizio(corso.getDataInizio());
            corsoDTO.setDurata(corso.getDurata());
            corsoDTO.setDocenteDTO(DocenteConverter.convertToDTOXCorso(corso.getDocente()));
            listaCorsoDTO.add(corsoDTO);
        }

        return listaCorsoDTO;
    }
    public static List<CorsoDTO> convertListToDTONoDOC(List<Corso> corsi) {
        List<CorsoDTO> listaCorsoDTO = new ArrayList<>();
        for (Corso corso : corsi) {
            CorsoDTO corsoDTO = new CorsoDTO();
            corsoDTO.setId(corso.getid());
            corsoDTO.setNomeCorso(corso.getNomeCorso());
            corsoDTO.setDatainizio(corso.getDataInizio());
            corsoDTO.setDurata(corso.getDurata());

            List<DiscenteDTO> discenteDTOList = DiscentiConverter.convertListToDTO(corso.getListaDiscenti());
            listaCorsoDTO.add(corsoDTO);
        }

        return listaCorsoDTO;
    }

    public static CorsoDTO convertToDTO(Corso corso) {
        CorsoDTO corsoDTO = new CorsoDTO();
        corsoDTO.setId(corso.getid());
        corsoDTO.setNomeCorso(corso.getNomeCorso());
        corsoDTO.setDatainizio(corso.getDataInizio());
        corsoDTO.setDurata(corso.getDurata());

        if (corso.getDocente() != null) {
            corsoDTO.setDocenteDTO(DocenteConverter.convertToDTOXCorso(corso.getDocente()));
        }

        List<Discente> listaDiscenti = corso.getListaDiscenti();
        if (listaDiscenti != null) {
            List<DiscenteDTO> listaDiscentiDTO = DiscentiConverter.convertListToDTO(listaDiscenti);
            corsoDTO.setListaDiscentiDTO(listaDiscentiDTO);
        }
        return corsoDTO;
    }

    public static Corso convertToEntity(CorsoDTO corsoDTO) {

        Corso corso = new Corso();
        corso.setid(corsoDTO.getId());
        corso.setNomeCorso(corsoDTO.getNomeCorso());
        corso.setDatainizio(corsoDTO.getDataInizio());
        corso.setDurata(corsoDTO.getDurata());
            if (corsoDTO.getDocenteDTO()!=null) {
                corso.setDocente(DocenteConverter.convertToEntityXCorso(corsoDTO.getDocenteDTO()));
            } else {
                throw new EntityNotFoundException("Docente not found");
            }

            List<DiscenteDTO> listaDiscentiDTO = corsoDTO.getListaDiscentiDTO();
            if (listaDiscentiDTO != null) {
                List<Discente> listaDiscenti = DiscentiConverter.convertListToEntity(listaDiscentiDTO);
                corso.setListaDiscenti(listaDiscenti);
            }

        return corso;
    }
}

