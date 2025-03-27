package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.DTO.ProprietariDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscentiRepository;
import com.example.demo.utils.CorsoConverter;
import com.example.demo.utils.DiscentiConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DiscenteService {

    private final DiscentiRepository discentiRepository;


    private final CorsoRepository corsoRepository;

    private final RestTemplate restTemplate;

    private final EntityManager entityManager;

    public DiscenteService(DiscentiRepository discentiRepository,
                           CorsoRepository corsoRepository,
                           EntityManager entityManager,
                           RestTemplate restTemplate)
    {
        this.discentiRepository = discentiRepository;
        this.corsoRepository = corsoRepository;
        this.entityManager = entityManager;
        this.restTemplate = restTemplate;

    }

    public DiscenteDTO getDiscenteById(Integer id) {

        Optional<Discente> discente = discentiRepository.findById(id);

        if (discente.isPresent()) {

            return DiscentiConverter.convertToDTOWithList(discente.get());
        } else {
            throw new EntityNotFoundException();
        }
    }
// REST TEMPLATE-->
    public List<ProprietariDTO> getAllProp(){

        String url = "http://localhost:8081/proprietari/getAllProprietari ";
        ProprietariDTO[] propList =  restTemplate.getForObject(url,ProprietariDTO[].class);
        assert propList != null;
        return Arrays.asList(propList);

    }
    public ResponseEntity<ProprietariDTO> deletePropById(Integer id) {
        String url = "http://localhost:8081/proprietari/deletePropById/" + id;
        HttpEntity<String> requestEntity = new HttpEntity<>(null);
        return restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, ProprietariDTO.class);

    }


    public ProprietariDTO getPropById(Integer id){
        String url = "http://localhost:8081/proprietari/getProprietariById/" + id;
        try {
            return restTemplate.getForObject(url, ProprietariDTO.class);
        } catch (HttpClientErrorException e) {
            System.err.println("Errore HTTP: " + e.getStatusCode());
            return null; // O puoi lanciare un'eccezione personalizzata
        } catch (RestClientException e) {
            System.err.println("Errore generico: " + e.getMessage());
            return null;
        }

    }

    public ProprietariDTO insertProprietario(ProprietariDTO proprietario) {
        String url = "http://localhost:8081/proprietari/insertProp";
        return restTemplate.postForObject(url, proprietario, ProprietariDTO.class);
    }
    public ProprietariDTO updateProprietario(Integer id, ProprietariDTO proprietario) {
        String url = "http://localhost:8081/proprietari/updatePropById/" + id;



        HttpEntity<ProprietariDTO> requestEntity = new HttpEntity<>(proprietario);

        ResponseEntity<ProprietariDTO> response = restTemplate.exchange(
                url, HttpMethod.PUT, requestEntity, ProprietariDTO.class);

        return response.getBody();
    }


    //REST TEMPLATE <--

    public List<DiscenteDTO> getAllDiscenti() {
        List<Discente> listaDiscenti = discentiRepository.findAll();
        List<DiscenteDTO> listDTODiscenti = new ArrayList<>();
        for (Discente discente : listaDiscenti) {
            DiscenteDTO discenteDTO = DiscentiConverter.convertToDTOWithList(discente);

            listDTODiscenti.add(discenteDTO);
        }
        return listDTODiscenti;
    }

    public DiscenteDTO insertDiscente(DiscenteDTO discenteDTO) {


        Discente discente = DiscentiConverter.convertToEntity(discenteDTO);
        Discente discenteSaved = discentiRepository.save(discente);
        return DiscentiConverter.convertToDTOWithList(discenteSaved);

    }

    @Transactional
    public DiscenteDTO associaCorso(Integer idDiscente, Integer idCorso) {

        Optional<Discente> discenteOpt = discentiRepository.findById(idDiscente);
        Optional<Corso> corsoOpt = corsoRepository.findById(idCorso);
        if (discenteOpt.isPresent() && corsoOpt.isPresent() && (!corsoOpt.get().getListaDiscenti().contains(discenteOpt.get()))) {
            Discente discente = discenteOpt.get();
            Corso corso = corsoOpt.get();
            corso.getListaDiscenti().add(discente);
            discente.getListaCorsi().add(corso);
            discentiRepository.save(discente);
            corsoRepository.save(corso);
            return DiscentiConverter.convertToDTOWithList(discente);

        } else {
            throw new EntityNotFoundException();
        }

    }



    public DiscenteDTO updateDiscente(Integer id, DiscenteDTO discenteDTO) {
        Optional<Discente> discente = discentiRepository.findById(id);

        if (discente.isPresent()) {

            discenteDTO.setid(id);
            Discente discenteSaved = DiscentiConverter.convertToEntity(discenteDTO);
            discentiRepository.save(discenteSaved);
            return DiscentiConverter.convertToDTO(discenteSaved);
        } else {
            throw new EntityNotFoundException();
        }
    }
    @Transactional
    public DiscenteDTO deleteDiscenteById(Integer id) {
        Optional<Discente> d = discentiRepository.findById(id);

        if (d.isPresent()) {
            Discente discente = d.get();
            // Rimuovere le associazioni nei corsi
            for (Corso corso : discente.getListaCorsi()) {
                corso.getListaDiscenti().remove(discente);
//                entityManager.merge(corso);
            }
            discente.getListaCorsi().clear();

            DiscenteDTO discenteDeleted = DiscentiConverter.convertToDTO(discente);
            discentiRepository.deleteById(id);
            return discenteDeleted;

        } else {

            throw new EntityNotFoundException();

        }
    }


}
