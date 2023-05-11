package it.alten.atf.apipersonaggigioco.model.partita;

public class GiocatoriPartita {

    private String utente1;
    private String utente2;
    private String personaggio1;
    private String personaggio2;

    public GiocatoriPartita(String utente1, String utente2, String personaggio1, String personaggio2) {
        setUtente1(utente1);
        setUtente2(utente2);
        setPersonaggio1(personaggio1);
        setPersonaggio2(personaggio2);
    }

    public String getUtente1() {
        return utente1;
    }

    public void setUtente1(String utente1) {
        this.utente1 = utente1;
    }

    public String getUtente2() {
        return utente2;
    }

    public void setUtente2(String utente2) {
        this.utente2 = utente2;
    }

    public String getPersonaggio1() {
        return personaggio1;
    }

    public void setPersonaggio1(String personaggio1) {
        this.personaggio1 = personaggio1;
    }

    public String getPersonaggio2() {
        return personaggio2;
    }

    public void setPersonaggio2(String personaggio2) {
        this.personaggio2 = personaggio2;
    }
}
