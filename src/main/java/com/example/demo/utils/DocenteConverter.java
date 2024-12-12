package com.example.demo.utils;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;

import java.util.List;
import java.util.Optional;

public class DocenteConverter {

    public static Docente convertToEntity(DocenteDTO docenteDTO) {
        Docente docente = new Docente();
        docente.setid(docenteDTO.getid());
        docente.setNome(docenteDTO.getNome());
        docente.setCognome(docenteDTO.getCognome());
//       docente.setListaCorsi(docenteDTO.getListaCorsi());

        return docente;
    }

    public static DocenteDTO convertToDTO(Docente docente) {
        DocenteDTO docenteDTO = new DocenteDTO();
        docenteDTO.setid(docente.getid());
        docenteDTO.setNome(docente.getNome());
        docenteDTO.setCognome(docente.getCognome());
        List<Corso> listaCorsi = docente.getListaCorsi();

        if (listaCorsi != null) {
            List<CorsoDTO> listacorsiDTO = CorsoConverter.convertListToDTO(docente.getListaCorsi());
            docenteDTO.setListaCorsi(listacorsiDTO);
        }


        return docenteDTO;
    }
    public static DocenteDTO convertToDTOXCorso(Docente docente) {
        DocenteDTO docenteDTO = new DocenteDTO();
        docenteDTO.setid(docente.getid());
        docenteDTO.setNome(docente.getNome());
        docenteDTO.setCognome(docente.getCognome());



        return docenteDTO;
    }
    public static Docente convertToEntityXCorso(DocenteDTO docenteDTO) {
        Docente docente = new Docente();
        if (docenteDTO.getid() != null){
            docente.setid(docenteDTO.getid());
        }
        docente.setNome(docenteDTO.getNome());
        docente.setCognome(docenteDTO.getCognome());



        return docente;
    }

    public static DocenteDTO toDTO(Docente docente) {
        if (docente!=null) {
            DocenteDTO dto = new DocenteDTO();
            if (docente.getid() != null) {
                dto.setid(docente.getid());
            }
            dto.setNome(docente.getNome());
            dto.setCognome(docente.getCognome());
            return dto;
        } else {
            return null;
        }
    }


    public static Docente toEntity(DocenteDTO dto) {
        Docente docente = new Docente();
        if(dto.getid()!=null) {
            docente.setid(dto.getid());
        }
        docente.setNome(dto.getNome());
        docente.setCognome(dto.getCognome());
        return docente;
    }


}
