package com.example.demo.client;

import com.example.demo.DTO.ImmobileDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "immobiliClient",url = "http://localhost:8081/immobile")
public interface ImmobileClient {

    @GetMapping("/getAllImmobili")
    List<ImmobileDTO> getImmoobli();
}
