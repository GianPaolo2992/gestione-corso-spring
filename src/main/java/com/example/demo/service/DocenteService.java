package com.example.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.AnnessiDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.utils.DocenteConverter;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DocenteService {

    private final DocenteRepository docenteRepository;
    private final CorsoRepository corsoRepository;
    private final WebClient webClient;

    public DocenteService(DocenteRepository docenteRepository, CorsoRepository corsoRepository,WebClient webClient){
        this.docenteRepository = docenteRepository;
        this.corsoRepository = corsoRepository;
        this.webClient = webClient;

    }

    public DocenteDTO getDocenteById(Integer id) {

        Optional<Docente> docente =docenteRepository.findById(id);

        if (docente.isPresent()){

           DocenteDTO newDto = DocenteConverter.convertToDTO(docente.get());

            return newDto ;
        } else {
            throw new EntityNotFoundException();
        }
    }
    //WEB CLIENT -->
    //  Recuperare TUTTI gli annessi
    public Flux<AnnessiDTO> getAllAnnessi() {
        String url = "/annessi/getAllAnnessi";

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(AnnessiDTO.class); // Converte la risposta JSON in una lista reattiva
    }
    //  Recuperare un annesso

    public Mono<AnnessiDTO> getAnnessoById(Integer annessoId) {
        String url = "/annessi/getAllAnnessiById/" + annessoId;

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(AnnessiDTO.class); // Converte la risposta JSON in una lista reattiva
    }

    // inserire un annesso
    public Mono<AnnessiDTO> insertAnnesso(AnnessiDTO annesso){
        String url = "/annessi/insertAnnessi";
        return webClient.post()
                .uri(url)
                .bodyValue(annesso)
                .retrieve()
                .bodyToMono(AnnessiDTO.class);
    }

    // aggiorna un annesso
    public  Mono<AnnessiDTO> updateAnnnesso(Integer id, AnnessiDTO annessiDTO) {
        String url =  "/annessi/updateAnnessi/" + id;
        return webClient.put()
                .uri(url )
                .bodyValue(annessiDTO)
                .retrieve()
                .bodyToMono(AnnessiDTO.class);
    }

    //delete annesso
    public Mono<AnnessiDTO> deleteAnnessoById(Integer annessoId) {
        String url = "/annessi/deleteAnnessi/" + annessoId;

        return webClient.delete()
                .uri(url)
                .retrieve()
                .bodyToMono(AnnessiDTO.class); // Converte la risposta JSON in una lista reattiva
    }

    //WEB CLIENT <--

    public DocenteDTO InsertDocente(DocenteDTO DTO){
        Docente docente = DocenteConverter.convertToEntity(DTO);
        Docente DocenteSaved = docenteRepository.save(docente);
        return DocenteConverter.convertToDTO(DocenteSaved);

    }



    public List<DocenteDTO> getAll( ) {
        List<Docente> docenteList = docenteRepository.findAll();
        List<DocenteDTO> ListDTO = new ArrayList<>();
       for(Docente doc : docenteList){

           DocenteDTO newDTO = DocenteConverter.convertToDTO(doc);

           ListDTO.add(newDTO);
       }

       return ListDTO;
    }


    public DocenteDTO updateDocente(Integer id, DocenteDTO DTO) {

       Optional<Docente>  doc = docenteRepository.findById(id);
        if(doc.isPresent()){
            DTO.setId(id);
            Docente DocenteSaved = DocenteConverter.convertToEntity(DTO);
            docenteRepository.save(DocenteSaved);
            return DocenteConverter.convertToDTO(DocenteSaved);
        }else {
            throw new EntityNotFoundException();
        }

    }


    public void deleteDocenteById(Integer id ) {
        Optional<Docente> doc = docenteRepository.findById(id);

        if(doc.isPresent()) {

            for(Corso corso : doc.get().getListaCorsi()){
                corso.setDocente(null);
                corsoRepository.save(corso);
                //gestire eliminzione corsi in docente

            }
            docenteRepository.deleteById(id);

        }else{

            throw new EntityNotFoundException();
        }

    }
}
