package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "corso")
public class Corso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome_corso")
    private String nomeCorso;
    @Column(name = "data_inizio")
    private LocalDate dataInizio;
    private String durata;
    @ManyToOne
    @JoinColumn(name = "id_docente")

    private Docente docente;
    @ManyToMany
    @JoinTable(
            name = "rel_corso_discenti",
            joinColumns = @JoinColumn (name = "id_corso"),
            inverseJoinColumns = @JoinColumn(name = "id_discente")
    )
    private List<Discente> listaDiscenti;

    public Corso(){
        this.listaDiscenti = new ArrayList<>();
    }

    public void setid(Integer id) {
        this.id = id;
    }

    public Integer getid() {
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

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setListaDiscenti(List<Discente> listaDiscenti){
        this.listaDiscenti = listaDiscenti;
    }

    public List<Discente> getListaDiscenti(){
        return listaDiscenti;
    }

    public void addDiscente(Discente discente){
        if (!listaDiscenti.contains(discente)){
            listaDiscenti.add(discente);
            discente.addCorso(this);
        }
    }
}
