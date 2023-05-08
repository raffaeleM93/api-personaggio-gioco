package it.alten.atf.apipersonaggigioco.model;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Utente")
public class UtenteEntity {

    @Id
    private String username;
    @NotNull
    private String nome;
    @NotNull
    private LocalDate registrazione;

    @ManyToMany(mappedBy = "utenti")
    private Set<PersonaggioEntity> personaggi;

    public UtenteEntity(String username, String nome, LocalDate registrazione){
        setUsername(username);
        setNome(nome);
        setRegistrazione(registrazione);
    }

    public UtenteEntity(){

    }

    public PersonaggioEntity addPersonaggio(PersonaggioEntity pe){
        this.personaggi.add(pe);
        return pe;
    }

    public Set<PersonaggioEntity> getPersonaggi() {
        return personaggi;
    }

    public void setPersonaggi(Set<PersonaggioEntity> personaggi) {
        this.personaggi = personaggi;
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
