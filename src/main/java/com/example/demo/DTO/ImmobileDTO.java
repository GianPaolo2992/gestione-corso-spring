package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;


@Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class ImmobileDTO {


        private Integer id;


        private ProprietariDTO proprietariDTO;


        private String tipo;


        private Integer vani;


        private Integer costo;

        private Integer superfice;


        private Integer anno;

        private List<AnnessiDTO> listaAnnessiDTO;


    }
