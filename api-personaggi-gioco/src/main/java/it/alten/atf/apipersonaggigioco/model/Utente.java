package it.alten.atf.apipersonaggigioco.model;

import java.time.LocalDate;


public class Utente {

    private String username;
    private String nome;
    private LocalDate registrazione;

    public Utente(String username, String nome, LocalDate registrazione){
        setUsername(username);
        setNome(nome);
        setRegistrazione(registrazione);
    }

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
}


