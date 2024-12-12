package com.example.demo.controller;


import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Docente;
import com.example.demo.service.DocenteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/docente")
public class DocenteController {

    private final DocenteService docenteService;

    public DocenteController(DocenteService docenteService){
        this.docenteService = docenteService;
    }



    //GET
    @GetMapping("/getDocenteById/{idDocente}")
    public DocenteDTO getDocenteById(@PathVariable("idDocente") Integer id){


      return docenteService.getDocenteById(id);
    }

    @GetMapping("/getAllDocenti")
    public List<DocenteDTO> getAllDocente(){

        return docenteService.getAll();
    }

    //INSERT
    @PostMapping("/insDocente")
    public DocenteDTO insDocente(@RequestBody DocenteDTO DTO){

       return docenteService.InsertDocente(DTO);
    }

    @PutMapping("/updateDocente/{idDocente}")
    public DocenteDTO updateDocente(@PathVariable("idDocente")Integer id, @RequestBody DocenteDTO DTO){
        return docenteService.updateDocente(id,DTO);
    }
    @DeleteMapping("deleteDocenteById/{idDocente}")
    public void deleteDocenteById(@PathVariable("idDocente")Integer id){
          docenteService.deleteDocenteById(id);
    }


}
