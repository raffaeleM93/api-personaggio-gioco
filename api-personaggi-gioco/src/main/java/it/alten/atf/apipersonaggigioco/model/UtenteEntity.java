package it.alten.atf.apipersonaggigioco.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
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

    @ManyToMany
    @JoinTable(
            name = "utente_personaggio",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "nome_personaggio"))
    private Set<PersonaggioEntity> personaggi;

    public UtenteEntity(String username, String nome, LocalDate registrazione){
        setUsername(username);
        setNome(nome);
        setRegistrazione(registrazione);
    }

    public UtenteEntity(){

    }

    public PersonaggioEntity addPe(PersonaggioEntity pe){
        Set<PersonaggioEntity> peSet = new HashSet<PersonaggioEntity>();
        peSet.add(pe);
        this.setPersonaggi(peSet);
        return peSet.iterator().next();
    }

    public Set<PersonaggioEntity> getPersonaggi() {
        return personaggi;
    }

    public void setPersonaggi(Set<PersonaggioEntity> personaggi) {
        this.personaggi.addAll(personaggi);
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
