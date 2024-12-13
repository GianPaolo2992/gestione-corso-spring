package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CorsoDTONoDOC {

        private Integer id;

        private String nomeCorso;

        private LocalDate dataInizio;

        private String durata;



        private List<DiscenteDTO> listaDiscenti;

        public CorsoDTONoDOC(){
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
