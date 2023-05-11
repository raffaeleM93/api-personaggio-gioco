package it.alten.atf.apipersonaggigioco.model.utente;

import java.time.LocalDate;

public class UtenteData {

    private String nome;
    private LocalDate registrazione;

    public UtenteData(String nome, LocalDate registrazione){
        setNome(nome);
        setRegistrazione(registrazione);
    }

    public UtenteData(){}

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
