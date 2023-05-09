package it.alten.atf.apipersonaggigioco.model;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Personaggio")
public class PersonaggioEntity {

    @Id
    private String nome;
    private String descrizione;
    @NotNull
    private int base_atk; // attacco base
    @NotNull
    private int base_def; // difesa base
    @NotNull
    private int inc_atk; // incremento attacco per livello
    @NotNull
    private int inc_def; // incremento difesa per livello

    @ManyToMany(mappedBy = "personaggi")
    Set<UtenteEntity> utenti;

    public PersonaggioEntity(String nome, String descrizione, int base_atk, int base_def, int inc_atk, int inc_def){
        setNome(nome);
        setDescrizione(descrizione);
        setBase_atk(base_atk);
        setBase_def(base_def);
        setInc_atk(inc_atk);
        setInc_def(inc_def);
    }

    public PersonaggioEntity(){

    }

    public Set<UtenteEntity> getUtenti() {
        return utenti;
    }

    public void setUtenti(Set<UtenteEntity> utenti) {
        this.utenti = utenti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getBase_atk() {
        return base_atk;
    }

    public void setBase_atk(int base_atk) {
        this.base_atk = base_atk;
    }

    public int getBase_def() {
        return base_def;
    }

    public void setBase_def(int base_def) {
        this.base_def = base_def;
    }

    public int getInc_atk() {
        return inc_atk;
    }

    public void setInc_atk(int inc_atk) {
        this.inc_atk = inc_atk;
    }

    public int getInc_def() {
        return inc_def;
    }

    public void setInc_def(int inc_def) {
        this.inc_def = inc_def;
    }

}
