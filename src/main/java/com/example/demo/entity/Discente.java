package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "discente")

public class Discente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String cognome;
    private String matricola;
    private LocalDate data_nascita;
   @ManyToMany(
           mappedBy = "listaDiscenti"
   )
    private List<Corso> listaCorsi;

    public Discente(){
        this.listaCorsi = new ArrayList<>();
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

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setDataNascita(LocalDate data_nascita) {
        this.data_nascita = data_nascita;
    }

    public LocalDate getDataNascita() {
        return data_nascita;
    }

    public void setid(int id) {
        this.id = id;
    }

    public int getid() {
        return id;
    }

    public void setListaCorsi(List<Corso> listaCorsi){
        this.listaCorsi = listaCorsi;
    }

    public List<Corso> getListaCorsi(){
        return listaCorsi;
    }

//    public void aggiungiCorso (Corso corso){
//        if (!listaCorsi.contains(corso)){
//            listaCorsi.add(corso);
//            corso.aggiungiDiscente(this);
//        }
//    }

}
