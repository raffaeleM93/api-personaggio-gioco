package it.alten.atf.apipersonaggigioco.model;

import java.time.LocalDate;
import java.util.Objects;


public class Utente {

    private String username;
    private String nome;
    private LocalDate registrazione;

    public Utente(String username, String nome, LocalDate registrazione){
        setUsername(username);
        setNome(nome);
        setRegistrazione(registrazione);
    }

    public Utente(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getRegistrazione() {
        return registrazione;
    }

    public void setRegistrazione(LocalDate registrazione) {
        this.registrazione = registrazione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utente)) return false;
        Utente utente = (Utente) o;
        return Objects.equals(username, utente.username) && Objects.equals(nome, utente.nome) && Objects.equals(registrazione, utente.registrazione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, nome, registrazione);
    }
}


