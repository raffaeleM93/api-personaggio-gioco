package it.alten.atf.apipersonaggigioco.service;
import it.alten.atf.apipersonaggigioco.model.partita.*;
import it.alten.atf.apipersonaggigioco.model.personaggio.Personaggio;
import it.alten.atf.apipersonaggigioco.model.personaggio.PersonaggioEntity;
import it.alten.atf.apipersonaggigioco.model.personaggio.PersonaggioLevel;
import it.alten.atf.apipersonaggigioco.model.utente.UtenteEntity;
import it.alten.atf.apipersonaggigioco.repository.InfoGiocoRepository;
import it.alten.atf.apipersonaggigioco.repository.PartitaRepository;
import it.alten.atf.apipersonaggigioco.repository.PersonaggioRepository;
import it.alten.atf.apipersonaggigioco.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class PartitaService {

    @Autowired
    UtenteRepository utenteRepository;
    @Autowired
    PersonaggioRepository personaggioRepository;
    @Autowired
    PartitaRepository partitaRepository;
    @Autowired
    InfoGiocoRepository infoGiocoRepository;

    @Autowired
    InfoGiocoEntity infoGiocoEntity;

    @Transactional
    public UUID creaPartita(GiocatoriPartita gp) {

        UtenteEntity ue1 = utenteRepository.getReferenceById(gp.getUtente1());
        UtenteEntity ue2 = utenteRepository.getReferenceById(gp.getUtente2());

        Set<PersonaggioEntity> pu1 = ue1.getPersonaggi();
        Set<PersonaggioEntity> pu2 = ue2.getPersonaggi();

        PersonaggioEntity pe1 = null;
        PersonaggioEntity pe2 = null;

        Iterator<PersonaggioEntity> value1 = pu1.iterator();
        boolean b = false;

        do{
            PersonaggioEntity pe = (PersonaggioEntity) value1.next();
            if(pe.getNome().equals(gp.getPersonaggio1())){
                pe1 = pe;
                b = true;
                break;
            }
        }
        while(value1.hasNext());

        if(!b){
            throw new IllegalArgumentException();
        }

        b = false;
        Iterator<PersonaggioEntity> value2 = pu2.iterator();

        do{
            PersonaggioEntity pe = (PersonaggioEntity) value2.next();
            if(pe.getNome().equals(gp.getPersonaggio2())){
                pe2 = pe;
                b = true;
                break;
            }
        }
        while(value2.hasNext());

        if(!b){
            throw new IllegalArgumentException();
        }

        PartitaEntity pe = new PartitaEntity(ue1, ue2, pe1, pe2);

        partitaRepository.save(pe);
        partitaRepository.flush();

        infoGiocoEntity.addPartita(pe);
        infoGiocoRepository.save(infoGiocoEntity);

        return pe.getId();
    }

    @Transactional
    public Partita getInfoPartita(UUID uuid) {

        PartitaEntity pe;

        if(partitaRepository.existsById(uuid)){
            // recupero PartitaEntity by uuid dal db
            pe = partitaRepository.getReferenceById(uuid);
        }
        else{
            throw new IllegalArgumentException();
        }

        String giocatore1 = pe.getUtente1().getUsername();
        String giocatore2 = pe.getUtente2().getUsername();

        // wrapping PersonaggioEntity -> Personaggio
        PersonaggioEntity pe1 = pe.getPersonaggio1();
        PersonaggioEntity pe2 = pe.getPersonaggio2();
        Personaggio p1 = new Personaggio(pe1.getNome(), pe1.getDescrizione(), pe1.getBase_atk(), pe1.getBase_def(), pe1.getInc_atk(), pe1.getInc_def());
        Personaggio p2 = new Personaggio(pe2.getNome(), pe2.getDescrizione(), pe2.getBase_atk(), pe2.getBase_def(), pe2.getInc_atk(), pe2.getInc_def());

        PersonaggioLevel pl1 = new PersonaggioLevel(p1, pe.getLevelP1());
        PersonaggioLevel pl2 = new PersonaggioLevel(p2, pe.getLevelP2());

        return new Partita(giocatore1, giocatore2, pl1, pl2, pe.getStato(), pe.getVincitore());
    }

    @Transactional
    public Partita giocaPartita(UUID uuid) {

        PartitaEntity pe;

        if(partitaRepository.existsById(uuid)){
            // recupero PartitaEntity by uuid dal db
             pe = partitaRepository.getReferenceById(uuid);
        }
        else{
            throw new IllegalArgumentException();
        }

        String giocatore1 = pe.getUtente1().getUsername();
        String giocatore2 = pe.getUtente2().getUsername();

        // wrapping PersonaggioEntity -> Personaggio
        PersonaggioEntity pe1 = pe.getPersonaggio1();
        PersonaggioEntity pe2 = pe.getPersonaggio2();
        Personaggio p1 = new Personaggio(pe1.getNome(), pe1.getDescrizione(), pe1.getBase_atk(), pe1.getBase_def(), pe1.getInc_atk(), pe1.getInc_def());
        Personaggio p2 = new Personaggio(pe2.getNome(), pe2.getDescrizione(), pe2.getBase_atk(), pe2.getBase_def(), pe2.getInc_atk(), pe2.getInc_def());

        PersonaggioLevel pl1 = new PersonaggioLevel(p1, pe.getLevelP1());
        PersonaggioLevel pl2 = new PersonaggioLevel(p2, pe.getLevelP2());

        // CREATA -> IN CORSO
        pe.incrementaStato();

        int n = (int) (Math.random() * 2 + 1);

        // Check valori level_atk e level_def dei 2 giocatori per determinare il vincitore
        if(n==1){
            if(pl1.getLevel_atk() > pl2.getLevel_def()){
                // Giocatore 1 vince su Giocatore 2
                // IN CORSO -> TERMINATA
                pe.incrementaStato();
                pe.setVincitore(pe.getUtente1().getUsername());
            }
            else if(pl2.getLevel_atk() > pl1.getLevel_def()){
                // Giocatore 2 vince su Giocatore 1
                // IN CORSO -> TERMINATA
                pe.incrementaStato();
                pe.setVincitore(pe.getUtente2().getUsername());
            }
            else{
                // pareggio
                // IN CORSO -> TERMINATA
                pe.incrementaStato();
                pe.setVincitore("pareggio");
            }
        }
        else{
            if(pl2.getLevel_atk() > pl1.getLevel_def()){
                // Giocatore 2 vince su Giocatore 1
                // IN CORSO -> TERMINATA
                pe.incrementaStato();
                pe.setVincitore(pe.getUtente2().getUsername());
            }
            else if(pl1.getLevel_atk() > pl2.getLevel_def()){
                // Giocatore 1 vince su Giocatore 2
                // IN CORSO -> TERMINATA
                pe.incrementaStato();
                pe.setVincitore(pe.getUtente1().getUsername());
            }
            else{
                // pareggio
                // IN CORSO -> TERMINATA
                pe.incrementaStato();
                pe.setVincitore("pareggio");
            }
        }

        // Aggiorn. num. KO utenti nel gioco
        if(pe.getVincitore().equals(giocatore1)){
            infoGiocoEntity.incKOutente1();
        }
        else{
            infoGiocoEntity.incKOutente2();
        }

        PartitaEntity pe_last = (PartitaEntity) infoGiocoEntity.getPartite().toArray()[infoGiocoEntity.getPartite().size()-1];
        pe_last.setVincitore(pe.getVincitore());
        pe_last.setStato(pe.getStato());

        partitaRepository.flush();
        infoGiocoRepository.saveAndFlush(infoGiocoEntity);

        return new Partita(giocatore1, giocatore2, pl1, pl2, pe.getStato(), pe.getVincitore());
    }

    @Transactional
    public InfoGioco terminaGioco() {

        InfoGioco info;

        if(infoGiocoEntity != null){

            Set<PartitaEntity> peSet = infoGiocoEntity.getPartite();
            Set<Partita> pSet = new HashSet<Partita>();

            for(var pe : peSet){
                PersonaggioEntity pe1 = pe.getPersonaggio1();
                PersonaggioEntity pe2 = pe.getPersonaggio2();

                Personaggio p1 = new Personaggio(pe1.getNome(), pe1.getDescrizione(), pe1.getBase_atk(), pe1.getBase_def(), pe1.getInc_atk(), pe1.getInc_def());
                Personaggio p2 = new Personaggio(pe2.getNome(), pe2.getDescrizione(), pe2.getBase_atk(), pe2.getBase_def(), pe2.getInc_atk(), pe2.getInc_def());

                PersonaggioLevel pl1 = new PersonaggioLevel(p1, pe.getLevelP1());
                PersonaggioLevel pl2 = new PersonaggioLevel(p2, pe.getLevelP2());

                Partita p = new Partita(
                        pe.getUtente1().getUsername(),
                        pe.getUtente2().getUsername(),
                        pl1,
                        pl2,
                        pe.getStato(),
                        pe.getVincitore());
                pSet.add(p);
            }

            info = new InfoGioco(infoGiocoEntity.getKoUtente1(), infoGiocoEntity.getKoUtente2(), pSet);
            infoGiocoEntity = new InfoGiocoEntity();
        }
        else{
            throw new NoSuchElementException("Non esiste un gioco avviato!");
        }

        return info;
    }
}
