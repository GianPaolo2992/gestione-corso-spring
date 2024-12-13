package com.example.demo.controller;

import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discente")
public class DiscenteController {
    private final DiscenteService discenteService;
    @Autowired
    public DiscenteController(DiscenteService discenteService){
        this.discenteService = discenteService;
    }
    @GetMapping("/getDiscenteById/{id}")
    public DiscenteDTO  getDiscenteById(@PathVariable("id")Integer id) {
        return discenteService.getDiscenteById(id);
    }

    @GetMapping("/getAllDiscenti")
    public List<DiscenteDTO>  getAllDiscenti() {
        return discenteService.getAllDiscenti();
    }


    @PostMapping("/insertDiscente")
    public DiscenteDTO insertDiscente(@RequestBody DiscenteDTO discenteDTO) {
        return discenteService.insertDiscente(discenteDTO);
    }

    @PostMapping("/associateCourse/Discente/{idDiscente}/Corso/{idCorso}")
    public DiscenteDTO associateCourse(@PathVariable("idDiscente")Integer idDiscente, @PathVariable("idCorso")Integer idCorso) {
        return  discenteService.associaCorso(idDiscente,idCorso);
    }

    @PutMapping("/updateDiscente/{id}")
    public DiscenteDTO updateDiscente(@PathVariable("id") Integer id , @RequestBody DiscenteDTO discenteDTO) {
        return discenteService.updateDiscente(id,discenteDTO);
    }

    //questo metodo non funziona come previsto dato che non cè una tabella entita che rappresenta la tabella jooin rel_corso_discenti
//    @PutMapping("/updateAssociateCourse/Discente/{idDiscente}/Corso/{idCorso}")
//    public DiscenteDTO updateAsspciateCouse(@PathVariable("idDiscente")Integer idDiscenti, @PathVariable("idCorso") Integer idCorso) {
//        return discenteService.updateAssociateCourso(idDiscenti,idCorso);
//    }

    @DeleteMapping("/deleteDiscenteById/{id}")
    public DiscenteDTO deleteDiscenteById(@PathVariable("id") Integer id) {
        return discenteService.deleteDiscenteById(id);

    }
}
