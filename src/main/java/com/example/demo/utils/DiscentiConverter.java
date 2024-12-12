package com.example.demo.utils;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;

import java.util.ArrayList;
import java.util.List;

public class DiscentiConverter {
    private int id;
//    private String nome;
//    private String cognome;
//    private String matricola;
//    private LocalDate data_nascita;
    public DiscenteDTO convertToEntity(Discente discente){
        DiscenteDTO discenteDTO = new DiscenteDTO();
        discenteDTO.setid(discente.getid());
        discenteDTO.setNome(discente.getNome());
        discenteDTO.setCognome(discente.getCognome());
        discenteDTO.setMatricola(discente.getMatricola());
        discenteDTO.setDataNascita(discente.getDataNascita());
        return discenteDTO;
    }
    public static List<DiscenteDTO> convertListToEntity(List<Discente> discentes) {
        List<DiscenteDTO> listaDiscenteDTO = new ArrayList<>();
        for(Discente discente :discentes){
            DiscenteDTO discenteDTO = new DiscenteDTO();
            discenteDTO.setid(discente.getid());
            discenteDTO.setCognome(discente.getCognome());
            discenteDTO.setNome(discente.getNome());
            discenteDTO.setMatricola(discente.getMatricola());
            discenteDTO.setDataNascita(discente.getDataNascita());

            listaDiscenteDTO.add(discenteDTO);

        }

        return listaDiscenteDTO;
    }
    public static List<Discente> convertListToDTO(List<DiscenteDTO> listadiscenteDTO) {
        List<Discente> listaDiscente = new ArrayList<>();
        for(DiscenteDTO DTO : listadiscenteDTO){
            Discente discente = new Discente();
            discente.setid(DTO.getid());
            discente.setCognome(DTO.getCognome());
            discente.setNome(DTO.getNome());
            discente.setMatricola(DTO.getMatricola());
            discente.setDataNascita(DTO.getDataNascita());

            listaDiscente.add(discente);
        }
        return listaDiscente;
    }

}