package it.alten.atf.apipersonaggigioco.model.partita;

import it.alten.atf.apipersonaggigioco.model.personaggio.PersonaggioLevel;
import it.alten.atf.apipersonaggigioco.model.personaggio.PersonaggioLevelPartita;
import it.alten.atf.apipersonaggigioco.model.utente.UtentePersonaggi;

public class Partita {

    private String giocatore1;
    private String giocatore2;
    private PersonaggioLevel personaggioLevel1;
    private PersonaggioLevel personaggioLevel2;
    private StatoPartita statoPartita;
    private String vincitore;

    public Partita(String giocatore1, String giocatore2, PersonaggioLevel personaggioLevel1, PersonaggioLevel personaggioLevel2, StatoPartita statoPartita, String vincitore) {
        setGiocatore1(giocatore1);
        setGiocatore2(giocatore2);
        setPersonaggioLevel1(personaggioLevel1);
        setPersonaggioLevel2(personaggioLevel2);
        setStatoPartita(statoPartita);
        setVincitore(vincitore);
    }

    public String getGiocatore1() {
        return giocatore1;
    }

    public void setGiocatore1(String giocatore1) {
        this.giocatore1 = giocatore1;
    }

    public String getGiocatore2() {
        return giocatore2;
    }

    public void setGiocatore2(String giocatore2) {
        this.giocatore2 = giocatore2;
    }

    public PersonaggioLevel getPersonaggioLevel1() {
        return personaggioLevel1;
    }

    public void setPersonaggioLevel1(PersonaggioLevel personaggioLevel1) {
        this.personaggioLevel1 = personaggioLevel1;
    }

    public PersonaggioLevel getPersonaggioLevel2() {
        return personaggioLevel2;
    }

    public void setPersonaggioLevel2(PersonaggioLevel personaggioLevel2) {
        this.personaggioLevel2 = personaggioLevel2;
    }

    public StatoPartita getStatoPartita() {
        return statoPartita;
    }

    public void setStatoPartita(StatoPartita statoPartita) {
        this.statoPartita = statoPartita;
    }

    public String getVincitore() {
        return vincitore;
    }

    public void setVincitore(String vincitore) {
        this.vincitore = vincitore;
    }
}
