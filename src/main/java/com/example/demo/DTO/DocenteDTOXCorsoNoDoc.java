package com.example.demo.DTO;

import java.util.ArrayList;
import java.util.List;

public class DocenteDTOXCorsoNoDoc {

    private Integer id;

    private String nome;

    private String cognome;
    private List<CorsoDTONoDOC> listaCorsiNoDoc = new ArrayList<>();

    public void setListaCorsiNoDOC(List<CorsoDTONoDOC> listaCorsiNoDoc) {
        this.listaCorsiNoDoc = listaCorsiNoDoc;
    }
    public List<CorsoDTONoDOC> getListaCorsiNoDOC(){
        return listaCorsiNoDoc;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
