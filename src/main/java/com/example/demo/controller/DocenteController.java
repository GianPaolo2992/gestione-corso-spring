package com.example.demo.controller;


import com.example.demo.DTO.AnnessiDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Docente;
import com.example.demo.service.DocenteService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/docente")
public class DocenteController {

    private final DocenteService docenteService;

    public DocenteController(DocenteService docenteService) {
        this.docenteService = docenteService;
    }


    //GET
    @GetMapping("/getDocenteById/{idDocente}")
    public DocenteDTO getDocenteById(@PathVariable("idDocente") Integer id) {


        return docenteService.getDocenteById(id);
    }

    @GetMapping("/getAllDocenti")
    public List<DocenteDTO> getAllDocente() {

        return docenteService.getAll();
    }

    //WEB CLIENT -->
    @GetMapping("/getAllAnnessi")
    public Flux<AnnessiDTO> getAllAnnessi() {
        return docenteService.getAllAnnessi();
    }

    @GetMapping("/getAnnessoById/{id}")
    public Mono<AnnessiDTO> getAnnessoById(@PathVariable("id") Integer id) {
        return docenteService.getAnnessoById(id);
    }

    @PostMapping("insertAnnesso")
    public Mono<AnnessiDTO> inserAnnesso(@RequestBody AnnessiDTO annessoDTO) {
        return docenteService.insertAnnesso(annessoDTO);
    }

    @PutMapping("/updateAnnesso/{id}")
    public Mono<AnnessiDTO> updateAnnesso(@PathVariable("id") Integer id, @RequestBody AnnessiDTO annessoDTO) {
        return docenteService.updateAnnnesso(id, annessoDTO);
    }

    @DeleteMapping("/delteAnnesso/{id}")
    public Mono<AnnessiDTO> deleteAnnesso(@PathVariable("id") Integer id) {
        return docenteService.deleteAnnessoById(id);
    }

    // WEB CLIENT<--

    //INSERT
    @PostMapping("/insDocente")
    public DocenteDTO insDocente(@RequestBody DocenteDTO DTO) {

        return docenteService.InsertDocente(DTO);
    }


    @PutMapping("/updateDocente/{idDocente}")
    public DocenteDTO updateDocente(@PathVariable("idDocente") Integer id, @RequestBody DocenteDTO DTO) {
        return docenteService.updateDocente(id, DTO);
    }

    @DeleteMapping("deleteDocenteById/{idDocente}")
    public void deleteDocenteById(@PathVariable("idDocente") Integer id) {
        docenteService.deleteDocenteById(id);
    }


}
