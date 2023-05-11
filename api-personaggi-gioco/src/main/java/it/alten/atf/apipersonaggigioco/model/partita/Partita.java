package it.alten.atf.apipersonaggigioco.model.partita;

import it.alten.atf.apipersonaggigioco.model.personaggio.PersonaggioLevelPartita;
import it.alten.atf.apipersonaggigioco.model.utente.UtentePersonaggi;

public class Partita {

    private UtentePersonaggi giocatore1;
    private UtentePersonaggi giocatore2;
    private PersonaggioLevelPartita personaggioGiocatore1;
    private PersonaggioLevelPartita personaggioGiocatore2;
    private StatoPartita statoPartita;

    public Partita(UtentePersonaggi giocatore1, UtentePersonaggi giocatore2) {
        setGiocatore1(giocatore1);
        setGiocatore2(giocatore2);
        setStatoPartita(StatoPartita.CREATA);
        setPersonaggioGiocatore1(
                new PersonaggioLevelPartita(giocatore1.getPersonaggi().iterator().next(), 1)
        );
        setPersonaggioGiocatore2(
                new PersonaggioLevelPartita(giocatore2.getPersonaggi().iterator().next(), 1)
        );
    }

    public UtentePersonaggi getGiocatore1() {
        return giocatore1;
    }

    public void setGiocatore1(UtentePersonaggi giocatore1) {
        this.giocatore1 = giocatore1;
    }

    public UtentePersonaggi getGiocatore2() {
        return giocatore2;
    }

    public void setGiocatore2(UtentePersonaggi giocatore2) {
        this.giocatore2 = giocatore2;
    }

    public PersonaggioLevelPartita getPersonaggioGiocatore1() {
        return personaggioGiocatore1;
    }

    public void setPersonaggioGiocatore1(PersonaggioLevelPartita personaggioGiocatore1) {
        this.personaggioGiocatore1 = personaggioGiocatore1;
    }

    public PersonaggioLevelPartita getPersonaggioGiocatore2() {
        return personaggioGiocatore2;
    }

    public void setPersonaggioGiocatore2(PersonaggioLevelPartita personaggioGiocatore2) {
        this.personaggioGiocatore2 = personaggioGiocatore2;
    }

    public StatoPartita getStatoPartita() {
        return statoPartita;
    }

    public void setStatoPartita(StatoPartita statoPartita) {
        this.statoPartita = statoPartita;
    }

    public StatoPartita incrementaStato(){
        if(this.statoPartita.ordinal() < 3){
            this.statoPartita = StatoPartita.values()[this.statoPartita.ordinal() + 1];
        }
        else{
            throw new IllegalArgumentException();
        }
        return statoPartita;
    }
}
