package it.alten.atf.apipersonaggigioco.service;

import it.alten.atf.apipersonaggigioco.model.Utente;
import it.alten.atf.apipersonaggigioco.model.UtentePersonaggi;
import it.alten.atf.apipersonaggigioco.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public Utente saveUtente(Utente p) {
        return new Utente();
    }

    @Transactional
    public Utente updateUtente(Utente u, String username){
        return new Utente();
    }

    public List<UtentePersonaggi> getAllUtentePersonaggi() {
        List<UtentePersonaggi> listU = new ArrayList<UtentePersonaggi>();
        return listU;
    }

    public Utente getUtenteByUsername(String username) {
        return new Utente();
    }

    public void deleteUtenteByUsername(String username) {

    }
}
