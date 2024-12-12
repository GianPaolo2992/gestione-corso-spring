package com.example.demo.controller;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.repository.CorsoRepository;

import com.example.demo.service.CorsoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/corso")
public class CorsoController {

    private  CorsoService corsoService;

    public CorsoController(CorsoService corsoService) {
        this.corsoService = corsoService;
    }

    @GetMapping("/getCorsoById/{id}")
    public CorsoDTO getCorsoById(@PathVariable("id") Integer id ){

        return corsoService.getCorsoById(id);

    }

    @GetMapping("/getAllCorsi")
    public List<CorsoDTO> getAllCorsi(){

        return corsoService.getAllCorsi();

    }

    @PostMapping("/insertCorso")
    public CorsoDTO insertCorso(@RequestBody CorsoDTO DTO) {
        return corsoService.insertCorso(DTO);
    }


    @PutMapping("/updateCorso/{id}")
    public CorsoDTO updateCorso(@PathVariable("id") Integer id , @RequestBody CorsoDTO DTO) {
        return corsoService.updateCorso(id,DTO);
    }

    @DeleteMapping("/deleteCorsoById/{id}")
    public CorsoDTO deleteCorsoById(@PathVariable("id")Integer id){
       return corsoService.deleteCorsoById(id);

    }

}
