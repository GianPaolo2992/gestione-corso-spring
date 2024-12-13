package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscentiRepository;
import com.example.demo.utils.CorsoConverter;
import com.example.demo.utils.DiscentiConverter;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class DiscenteService {

    @Autowired
    private DiscentiRepository discentiRepository;

    @Autowired
    private CorsoRepository corsoRepository;

    public DiscenteService(DiscentiRepository discentiRepository,CorsoRepository corsoRepository) {
        this.discentiRepository = discentiRepository;
        this.corsoRepository = corsoRepository;

    }

    public DiscenteDTO getDiscenteById(Integer id){

        Optional<Discente> discente =discentiRepository.findById(id);

        if (discente.isPresent()){

            return DiscentiConverter.convertToDTOWithList(discente.get());
        }else{
            throw new EntityNotFoundException();
        }
    }

    public List<DiscenteDTO> getAllDiscenti(){
       List<Discente> listaDiscenti = discentiRepository.findAll();
       List<DiscenteDTO> listDTODiscenti = new ArrayList<>();
       for (Discente discente : listaDiscenti){
           DiscenteDTO discenteDTO = DiscentiConverter.convertToDTOWithList(discente);

           listDTODiscenti.add(discenteDTO);
       }
       return listDTODiscenti;
    }

    public DiscenteDTO insertDiscente(DiscenteDTO discenteDTO) {


        Discente discente = DiscentiConverter.convertToEntity(discenteDTO);
        Discente discenteSaved =discentiRepository.save(discente);
        return DiscentiConverter.convertToDTOWithList(discenteSaved);

    }
    @Transactional
    public DiscenteDTO associaCorso(Integer idDiscente, Integer idCorso){

        Optional<Discente> discente = discentiRepository.findById(idDiscente);
        Optional<Corso> corso = corsoRepository.findById(idCorso);
        if (discente.isPresent() && corso.isPresent() && (!corso.get().getListaDiscenti().contains(discente.get()))){

            corso.get().getListaDiscenti().add(discente.get());
            discente.get().getListaCorsi().add(corso.get());
            discentiRepository.save(discente.get());
            corsoRepository.save(corso.get());
            return DiscentiConverter.convertToDTOWithList(discente.get());

        }else{
            throw new EntityNotFoundException();
        }

    }

    //questo metodo non funziona come previsto dato che non cè una tabella entita che rappresenta la tabella jooin rel_corso_discenti
//    @Transactional
//    public DiscenteDTO updateAssociateCourso(Integer idDiscente, Integer idCorso){
//
//        Optional<Discente> discenteOpt = discentiRepository.findById(idDiscente);
//        Optional<Corso> corsoOpt = corsoRepository.findById(idCorso);
//
//        if (discenteOpt.isPresent() && corsoOpt.isPresent()) {
//            Discente discente = discenteOpt.get();
//            Corso corso = corsoOpt.get();
//
//            // Rimuovere le vecchie associazioni
//            for (Corso c : discente.getListaCorsi()) {
//                c.getListaDiscenti().remove(discente);
//            }
//            discente.getListaCorsi().clear();
//
//            for (Discente d : corso.getListaDiscenti()) {
//                d.getListaCorsi().remove(corso);
//            }
//            corso.getListaDiscenti().clear();
//
//            corso.getListaDiscenti().add(discente);
//            discente.getListaCorsi().add(corso);
//            discentiRepository.save(discente);
//            corsoRepository.save(corso);
//            return DiscentiConverter.convertToDTOWithList(discente);
//
//        }else{
//            throw new EntityNotFoundException();
//        }
//
//    }

//    public  DiscenteDTO updateDiscente(Integer id, DiscenteDTO discenteDTO) {
//        Optional<Discente> discente = discentiRepository.findById(id);
//
//        if (discente.isPresent()){
//            discente.get().setNome(discenteDTO.getNome());
//            discente.get().setCognome(discenteDTO.getCognome());
//            discente.get().setDataNascita(discenteDTO.getDataNascita());
//            discente.get().setMatricola(discenteDTO.getMatricola());
//            discentiRepository.save(discente.get());
//            return DiscentiConverter.convertToDTO(discente.get());
//        }else{
//            throw new EntityNotFoundException();
//        }
//    }
    public  DiscenteDTO updateDiscente(Integer id, DiscenteDTO discenteDTO) {
        Optional<Discente> discente = discentiRepository.findById(id);

        if (discente.isPresent()){

            discenteDTO.setid(id);
            Discente discenteSaved = DiscentiConverter.convertToEntity(discenteDTO);
            discentiRepository.save(discenteSaved);
            return DiscentiConverter.convertToDTO(discenteSaved);
        }else{
            throw new EntityNotFoundException();
        }
    }

    public DiscenteDTO deleteDiscenteById(Integer id) {
        Optional<Discente> discente = discentiRepository.findById(id);

        if (discente.isPresent()){
            DiscenteDTO discenteDeleted = DiscentiConverter.convertToDTO(discente.get());
            discentiRepository.deleteById(id);
            return  discenteDeleted;

        }else{

            throw new EntityNotFoundException();
        }
    }



}
