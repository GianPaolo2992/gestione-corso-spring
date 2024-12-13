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
        docente.setId(docenteDTO.getId());
        docente.setNome(docenteDTO.getNome());
        docente.setCognome(docenteDTO.getCognome());
//       docente.setListaCorsi(docenteDTO.getListaCorsi());

        return docente;
    }

    public static DocenteDTO convertToDTO(Docente docente) {
        DocenteDTO docenteDTO = new DocenteDTO();
        docenteDTO.setId(docente.getId());
        docenteDTO.setNome(docente.getNome());
        docenteDTO.setCognome(docente.getCognome());
        if (docente.getListaCorsi() != null) {
            docenteDTO.setListaCorsi(CorsoNoDocConverter.convertListToDTONoDOC(docente.getListaCorsi()));
        }


        return docenteDTO;
    }


    public static DocenteDTO convertToDTOXCorso(Docente docente) {
        DocenteDTO docenteDTO = new DocenteDTO();
        docenteDTO.setId(docente.getId());
        docenteDTO.setNome(docente.getNome());
        docenteDTO.setCognome(docente.getCognome());



        return docenteDTO;
    }

    public static Docente convertToEntityXCorso(DocenteDTO docenteDTO) {
        Docente docente = new Docente();
        if (docenteDTO.getId() != null){
            docente.setId(docenteDTO.getId());
        }
        docente.setNome(docenteDTO.getNome());
        docente.setCognome(docenteDTO.getCognome());



        return docente;
    }
    public static Docente toEntity(DocenteDTO dto) {
        Docente docente = new Docente();
        if(dto.getId()!=null) {
            docente.setId(dto.getId());
        }
        docente.setNome(dto.getNome());
        docente.setCognome(dto.getCognome());
        return docente;
    }

    public static DocenteDTO toDTO(Docente docente) {
        if (docente!=null) {
            DocenteDTO dto = new DocenteDTO();
            if (docente.getId() != null) {
                dto.setId(docente.getId());
            }
            dto.setNome(docente.getNome());
            dto.setCognome(docente.getCognome());
            return dto;
        } else {
            return null;
        }
    }





}
