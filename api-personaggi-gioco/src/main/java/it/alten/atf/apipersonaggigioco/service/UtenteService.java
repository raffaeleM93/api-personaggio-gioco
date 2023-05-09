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
    private PersonaggioRepository personaggioRepository; // --> ? iniettato un'istanza di PersonaggioRepository all'interno di UtenteService

    @Transactional
    public Utente saveUtente(Utente u) {
        Utente u1 = new Utente(u.getUsername(), u.getNome(), u.getRegistrazione());
        UtenteEntity ue = new UtenteEntity(u1.getUsername(), u1.getNome(), u1.getRegistrazione());
        UtenteEntity ue2 = utenteRepository.save(ue);
        utenteRepository.flush();
        return new Utente(ue2.getUsername(), ue2.getNome(), ue2.getRegistrazione());
    }

    @Transactional
    public Utente updateUtente(Utente u, String username){
        // aggiornamento
        if(utenteRepository.existsById(username)){
            UtenteEntity ue = utenteRepository.getReferenceById(username);
            ue.setUsername(u.getUsername());
            ue.setNome(u.getNome());
            ue.setRegistrazione(u.getRegistrazione());
            utenteRepository.flush();
            return new Utente(ue.getUsername(), ue.getNome(), ue.getRegistrazione());
        }
        else{ // creazione
            return saveUtente(u);
        }
    }

    @Transactional
    public Personaggio addPersonaggio(String username, String nome){
        // controllo se utente esiste
        if(utenteRepository.existsById(username)){
            UtenteEntity ue = utenteRepository.getReferenceById(username);
            // controllo se personaggio esiste
            if(personaggioRepository.existsById(nome)){
                PersonaggioEntity pe = personaggioRepository.getReferenceById(nome);
                PersonaggioEntity pe_add = ue.addPe(pe);
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

    @Transactional
    public List<UtentePersonaggi> getAllUtentiPersonaggi() {

        // Recupero tutti gli utenti
        List<UtenteEntity> ueList = utenteRepository.findAll();
        // Lista vuota che dovrò ritornare al metodo
        List<UtentePersonaggi> upList = new ArrayList<>();

        // Per ogni utente costruisco UtentePersonaggi
        for(var ue : ueList){

            Set<Personaggio> setP = new HashSet<Personaggio>();

            // wrapping PersonaggioEntity -> Personaggio
            for(var pe : ue.getPersonaggi()){
                setP.add(new Personaggio(pe.getNome(), pe.getDescrizione(), pe.getBase_atk(), pe.getBase_def(), pe.getInc_atk(), pe.getInc_def()));
            }

            UtentePersonaggi up = new UtentePersonaggi(ue.getUsername(), ue.getNome(), ue.getRegistrazione(), setP);
            upList.add(up);
        }
        return upList;
    }

    public UtentePersonaggi getUtentePersonaggiByUsername(String username) {
        // controllo se esiste l'utente
        if(utenteRepository.existsById(username)){
            UtenteEntity ue = utenteRepository.getReferenceById(username);

            Set<Personaggio> setP = new HashSet<Personaggio>();

            // wrapping PersonaggioEntity -> Personaggio
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
        utenteRepository.deleteById(username);
    }
}
