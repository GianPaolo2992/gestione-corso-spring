package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "docentetest")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String cognome;
    @OneToMany(mappedBy = "docente")

    private List<Corso> listaCorsi;


    public List<Corso> getListaCorsi(){
        return listaCorsi;

    }

    public void setListaCorsi(List<Corso> ListaCorso){
        this.listaCorsi = ListaCorso;
    }

    public void addCorso(Corso corso){
        if (!listaCorsi.contains(corso)){
            listaCorsi.add(corso);
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }



}
