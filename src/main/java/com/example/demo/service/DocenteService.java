package com.example.demo.service;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Docente;
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

    public DocenteService(DocenteRepository docenteRepository){
        this.docenteRepository = docenteRepository;

    }

    public DocenteDTO getDocenteById(Integer id) {

        Optional<Docente> docente =docenteRepository.findById(id);

        if (docente.isPresent()){

           DocenteDTO newDto = DocenteConverter.convertToDTO(docente.get());
//            List<DocenteDTO> dtos = docente.stream().map(myObj -> {
//                        newDto.setid(docente.get().getid());
//                        newDto.setNome(docente.get().getNome());
//                        newDto.setCognome(docente.get().getCognome());
//                        newDto.setListaCorsi(docente.get().getListaCorsi());
//                        return newDto;
//                    })
//                    .collect(Collectors.toList());
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
            DTO.setid(id);
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
            docenteRepository.deleteById(id);

        }else{

            throw new EntityNotFoundException();
        }

    }
}
