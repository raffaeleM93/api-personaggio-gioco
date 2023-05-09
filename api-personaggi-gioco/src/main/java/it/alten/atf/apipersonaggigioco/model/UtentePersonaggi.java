package it.alten.atf.apipersonaggigioco.model;

import java.time.LocalDate;
import java.util.*;

public class UtentePersonaggi extends Utente {

    Set<Personaggio> personaggi;

    public UtentePersonaggi(String username, String nome, LocalDate registrazione, Set<Personaggio> personaggi) {
        super(username, nome, registrazione);
        this.personaggi = new HashSet<Personaggio>();
        setPersonaggi(personaggi);
    }

    public Set<Personaggio> getPersonaggi() {
        return personaggi;
    }

    public void setPersonaggi(Set<Personaggio> personaggi) {
        this.personaggi.addAll(personaggi);
    }

    @Override
    public String toString() {
        return "UtentePersonaggi{" +
                "personaggi=" + personaggi +
                '}';
    }
}
