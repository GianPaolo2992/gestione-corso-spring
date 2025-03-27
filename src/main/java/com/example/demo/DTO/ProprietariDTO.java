package com.example.demo.DTO;

import lombok.Data;

import java.util.List;
@Data
public class ProprietariDTO {
    private Integer id;


    private String cognome;


    private String nome;

    private List<ImmobileDTO> listaImmobiliDTO;
}
