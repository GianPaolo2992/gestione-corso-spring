package com.example.demo.client;

import com.example.demo.DTO.ImmobileDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "immobiliClient",url = "http://localhost:8081/immobile")
public interface ImmobileClient {

    @GetMapping("/getAllImmobili")
    List<ImmobileDTO> getImmoobli();

    @GetMapping("/getImmobileById/{id}")
    ImmobileDTO getImmobileById(@PathVariable("id") Integer id);

    @PostMapping("/insertImmobile")
    ImmobileDTO InsertImmobile(@RequestBody ImmobileDTO immobileDTO);


    @PutMapping("/updateImmobile/{id}")
    ImmobileDTO updateImmobile(@PathVariable("id") Integer id,@RequestBody ImmobileDTO immobileDTO);

    @DeleteMapping("/deleteImmobileById/{id}")
    ImmobileDTO deleteImmobile(@PathVariable("id") Integer id);
}
