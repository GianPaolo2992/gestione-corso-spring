package com.example.demo.DTO;


import com.example.demo.entity.Corso;

import java.util.ArrayList;
import java.util.List;

public class DocenteDTO {

    private Integer id;

    private String nome;

    private String cognome;

    private List<CorsoDTO> listaCorsi = new ArrayList<>();


    public List<CorsoDTO> getListaCorsi(){
        return listaCorsi;

    }

    public void setListaCorsi(List<CorsoDTO> ListaCorso){
        this.listaCorsi = ListaCorso;
    }

    public void addListaCorsi(CorsoDTO corsoDTO){
        if (!listaCorsi.contains(corsoDTO)){
            listaCorsi.add(corsoDTO);
        }
    }


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

    public void setid(Integer id) {
        this.id = id;
    }

    public Integer getid() {
        return id;
    }

}
