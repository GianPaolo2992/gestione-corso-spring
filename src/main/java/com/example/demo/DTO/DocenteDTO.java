package com.example.demo.DTO;


import com.example.demo.entity.Corso;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocenteDTO {

    private Integer id;

    private String nome;

    private String cognome;

    private List<CorsoDTONoDOC> listaCorsi ;


    public List<CorsoDTONoDOC> getListaCorsi(){
        return listaCorsi;

    }

//    private List<CorsoDTO> listaCorsi ;
//
//
//    public List<CorsoDTO> getListaCorsi(){
//        return listaCorsi;
//
//    }


    public void setListaCorsi(List<CorsoDTONoDOC> ListaCorso){
        this.listaCorsi = ListaCorso;
    }

//    public void setListaCorsi(List<CorsoDTO> ListaCorso) {
//        this.listaCorsi = ListaCorso;
//    }

    public void addListaCorsi(CorsoDTONoDOC corsoDTO){
        if (!listaCorsi.contains(corsoDTO)){
            listaCorsi.add(corsoDTO);
        }
    }
//    public void addListaCorsi(CorsoDTO corsoDTO){
//        if (!listaCorsi.contains(corsoDTO)){
//            listaCorsi.add(corsoDTO);
//        }
//    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
