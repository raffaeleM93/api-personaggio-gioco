package it.alten.atf.apipersonaggigioco.model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class UtentePersonaggi extends Utente {

    List<Personaggio> personaggi;

    public UtentePersonaggi(String username, String nome, LocalDate registrazione, List<Personaggio> personaggi) {
        super(username, nome, registrazione);
        setPersonaggi(personaggi);
    }

    public List<Personaggio> getPersonaggi() {
        return personaggi;
    }

    public void setPersonaggi(List<Personaggio> personaggi) {
        Collections.copy(this.personaggi, personaggi);
    }

    @Override
    public String toString() {
        return "UtentePersonaggi{" +
                "personaggi=" + personaggi +
                '}';
    }
}
