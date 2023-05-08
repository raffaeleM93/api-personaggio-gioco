package it.alten.atf.apipersonaggigioco.service;

import it.alten.atf.apipersonaggigioco.model.*;
import it.alten.atf.apipersonaggigioco.repository.PersonaggioRepository;
import it.alten.atf.apipersonaggigioco.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PersonaggioRepository personaggioRepository; // --> ?

    public Utente saveUtente(Utente u) {
        Utente u1 = new Utente(u.getUsername(), u.getNome(), u.getRegistrazione());
        UtenteEntity ue = new UtenteEntity(u1.getUsername(), u1.getNome(), u1.getRegistrazione());
        UtenteEntity ue2 = utenteRepository.save(ue);
        utenteRepository.flush();
        return new Utente(ue2.getUsername(), ue2.getNome(), ue2.getRegistrazione());
    }

    @Transactional
    public Utente updateUtente(Utente u, String username){
        if(utenteRepository.existsById(username)){
            UtenteEntity ue = utenteRepository.getReferenceById(username);
            ue.setUsername(u.getUsername());
            ue.setNome(u.getNome());
            ue.setRegistrazione(u.getRegistrazione());
            utenteRepository.flush();
            return new Utente(ue.getUsername(), ue.getNome(), ue.getRegistrazione());
        }
        else{
            return saveUtente(u);
        }
    }

    @Transactional
    public Personaggio addPersonaggio(String username, String nome){
        if(utenteRepository.existsById(username)){
            UtenteEntity ue = utenteRepository.getReferenceById(username);
            if(personaggioRepository.existsById(nome)){
                PersonaggioEntity pe = personaggioRepository.getReferenceById(nome);
                PersonaggioEntity pe_add = ue.addPersonaggio(pe);
                utenteRepository.flush();
                return new Personaggio(pe_add.getNome(), pe_add.getDescrizione(), pe_add.getBase_atk(), pe_add.getBase_def(), pe_add.getInc_atk(), pe_add.getInc_def());
            }
            else{
                throw new EntityNotFoundException("Personaggio non trovato!");
            }
        }
        else{
            throw new EntityNotFoundException("Utente non trovato!");
        }
    }

    public ArrayList<UtentePersonaggi> getAllUtentiPersonaggi() {

        List<PersonaggioEntity> pe_array = personaggioRepository.findAll();
        List<UtentePersonaggi> up = new ArrayList<>();

        for(var el : pe_array){
            Set<Personaggio> setP = new HashSet<Personaggio>();

            for(var pe2 : el.g()){
                setP.add(new Personaggio(pe2.getNome(), pe2.getDescrizione(), pe2.getBase_atk(), pe2.getBase_def(), pe2.getInc_atk(), pe2.getInc_def()));
            }
            UtentePersonaggi temp = new UtentePersonaggi(ue.getUsername(), ue.getNome(), ue.getRegistrazione(), setP);
            up.add(temp);
        }
    }

    public UtentePersonaggi getUtenteByUsername(String username) {
        if(utenteRepository.existsById(username)){
            UtenteEntity ue = utenteRepository.getReferenceById(username);

            Set<Personaggio> setP = new HashSet<Personaggio>();

            for(var pe2 : ue.getPersonaggi()){
                setP.add(new Personaggio(pe2.getNome(), pe2.getDescrizione(), pe2.getBase_atk(), pe2.getBase_def(), pe2.getInc_atk(), pe2.getInc_def()));
            }
            return new UtentePersonaggi(ue.getUsername(), ue.getNome(), ue.getRegistrazione(), setP);
        }
        else{
            throw new EntityNotFoundException("Utente non trovato!");
        }
    }

    public void deleteUtenteByUsername(String username) {

    }
}
