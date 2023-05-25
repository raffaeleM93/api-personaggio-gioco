package it.alten.atf.apipersonaggigioco.model.partita;

import it.alten.atf.apipersonaggigioco.model.personaggio.PersonaggioEntity;
import it.alten.atf.apipersonaggigioco.model.personaggio.PersonaggioLevelPartita;
import it.alten.atf.apipersonaggigioco.model.utente.UtenteEntity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Partita")
public class PartitaEntity {

    @Id
    @Type(type="uuid-char")
    private UUID id = UUID.randomUUID();

    @ManyToOne
    private UtenteEntity utente1;
    @ManyToOne
    private UtenteEntity utente2;
    @ManyToOne
    private PersonaggioEntity personaggio1;
    @ManyToOne
    private PersonaggioEntity personaggio2;
    private int levelP1;
    private int levelP2;
    private String vincitore;
    private StatoPartita stato;

    public PartitaEntity(UtenteEntity utente1, UtenteEntity utente2, PersonaggioEntity personaggio1, PersonaggioEntity personaggio2) {
        setUtente1(utente1);
        setUtente2(utente2);
        setPersonaggio1(personaggio1);
        setPersonaggio2(personaggio2);
        setLevelP1(1);
        setLevelP2(1);
        setVincitore("");
        setStato(StatoPartita.CREATA);
    }

    public PartitaEntity(){

    }

    public UUID getId() {
        return id;
    }

    public UtenteEntity getUtente1() {
        return utente1;
    }

    public void setUtente1(UtenteEntity utente1) {
        this.utente1 = utente1;
    }

    public UtenteEntity getUtente2() {
        return utente2;
    }

    public void setUtente2(UtenteEntity utente2) {
        this.utente2 = utente2;
    }

    public PersonaggioEntity getPersonaggio1() {
        return personaggio1;
    }

    public void setPersonaggio1(PersonaggioEntity personaggio1) {
        this.personaggio1 = personaggio1;
    }

    public PersonaggioEntity getPersonaggio2() {
        return personaggio2;
    }

    public void setPersonaggio2(PersonaggioEntity personaggio2) {
        this.personaggio2 = personaggio2;
    }

    public int getLevelP1() {
        return levelP1;
    }

    public void setLevelP1(int levelP1) {
        this.levelP1 = levelP1;
    }

    public int getLevelP2() {
        return levelP2;
    }

    public void setLevelP2(int levelP2) {
        this.levelP2 = levelP2;
    }

    public String getVincitore() {
        return vincitore;
    }

    public void setVincitore(String vincitore) {
        this.vincitore = vincitore;
    }

    public StatoPartita getStato() {
        return stato;
    }

    public void setStato(StatoPartita stato) {
        this.stato = stato;
    }

    public StatoPartita incrementaStato(){
        if(this.stato.ordinal() < 3){
            this.stato = StatoPartita.values()[this.stato.ordinal() + 1];
        }
        else{
            throw new IllegalArgumentException();
        }
        return stato;
    }
}
