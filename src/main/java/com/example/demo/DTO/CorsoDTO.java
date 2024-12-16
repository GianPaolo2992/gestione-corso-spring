package com.example.demo.DTO;

import com.example.demo.entity.Discente;
import com.example.demo.DTO.DocenteDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CorsoDTO {


    private Integer id;

    private String nomeCorso;

    private LocalDate dataInizio;

    private String durata;


    private DocenteDTO docenteDTO;

    private List<DiscenteDTO> listaDiscenti;

    public CorsoDTO(){}


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setNomeCorso(String nome) {
        this.nomeCorso = nome;
    }

    public String getNomeCorso() {
        return nomeCorso;
    }

    public void setDatainizio(LocalDate data_inizio) {
        this.dataInizio = data_inizio;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public String getDurata() {
        return durata;
    }

    public void setDocenteDTO(DocenteDTO docenteDTO) {
        this.docenteDTO = docenteDTO;
    }


    public DocenteDTO getDocenteDTO() {
        return docenteDTO;
    }



    public void setListaDiscentiDTO(List<DiscenteDTO> listaDiscentiDTO){
        this.listaDiscenti = listaDiscentiDTO;
    }

    public List<DiscenteDTO> getListaDiscentiDTO(){
        return listaDiscenti;
    }

//    public void aggiungiDiscente(Discente discente){
//        if (!listaDiscenti.contains(discente)){
//            listaDiscenti.add(discente);
//            discente.aggiungiCorso(this);
//        }
//    }
}
