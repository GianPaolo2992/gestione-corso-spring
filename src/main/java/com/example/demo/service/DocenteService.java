package com.example.demo.service;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.utils.DocenteConverter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DocenteService {

    private final DocenteRepository docenteRepository;
    private final CorsoRepository corsoRepository;

    public DocenteService(DocenteRepository docenteRepository, CorsoRepository corsoRepository){
        this.docenteRepository = docenteRepository;
        this.corsoRepository = corsoRepository;

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
