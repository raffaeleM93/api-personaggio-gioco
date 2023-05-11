package it.alten.atf.apipersonaggigioco.service;
import it.alten.atf.apipersonaggigioco.model.partita.GiocatoriPartita;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class PartitaService {

    @Transactional
    public UUID creaPartita(GiocatoriPartita gp) {
        return null;
    }
}
