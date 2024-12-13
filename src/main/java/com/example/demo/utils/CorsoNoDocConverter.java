package com.example.demo.utils;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.CorsoDTONoDOC;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Corso;

import java.util.ArrayList;
import java.util.List;

public class CorsoNoDocConverter {
    public static List<CorsoDTONoDOC> convertListToDTONoDOC(List<Corso> corsi) {
        List<CorsoDTONoDOC> listaCorsoDTO = new ArrayList<>();
        for (Corso corso : corsi) {
            CorsoDTONoDOC corsoDTO = new CorsoDTONoDOC();
            corsoDTO.setid(corso.getid());
            corsoDTO.setNomeCorso(corso.getNomeCorso());
            corsoDTO.setDatainizio(corso.getDataInizio());
            corsoDTO.setDurata(corso.getDurata());

            List<DiscenteDTO> discenteDTOList = DiscentiConverter.convertListToDTO(corso.getListaDiscenti());
            listaCorsoDTO.add(corsoDTO);
        }

        return listaCorsoDTO;
    }
//    public static List<CorsoDTO> convertListToDTONoDOC(List<Corso> corsi) {
//        List<CorsoDTO> listaCorso = new ArrayList<>();
//        for (Corso corso : corsi) {
//            CorsoDTO corsoDTO = new CorsoDTO();
//            corsoDTO.setid(corso.getid());
//            corsoDTO.setNomeCorso(corso.getNomeCorso());
//            corsoDTO.setDatainizio(corso.getDataInizio());
//            corsoDTO.setDurata(corso.getDurata());
//
//            List<DiscenteDTO> discenteDTOList = DiscentiConverter.convertListToEntity(corso.getListaDiscenti());
//            listaCorso.add(corsoDTO);
//        }
//
//        return listaCorso;
//    }


}
