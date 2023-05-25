package it.alten.atf.apipersonaggigioco.model.partita;

import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

@Component
@Entity
@Table(name = "InfoGioco")
public class InfoGiocoEntity {

    @Id
    @Type(type="uuid-char")
    private UUID id = UUID.randomUUID();

    private int koUtente1;
    private int koUtente2;

    @OneToMany
    Set<PartitaEntity> partite;

    public InfoGiocoEntity() {
        this.partite = new HashSet<PartitaEntity>();
        setKoUtente1(0);
        setKoUtente2(0);
    }

    public UUID getId() {
        return id;
    }

    public int getKoUtente1() {
        return koUtente1;
    }

    private void setKoUtente1(int koUtente1) {
        this.koUtente1 = koUtente1;
    }

    public int getKoUtente2() {
        return koUtente2;
    }

    private void setKoUtente2(int koUtente2) {
        this.koUtente2 = koUtente2;
    }

    public Set<PartitaEntity> getPartite() {
        return partite;
    }

    private void setPartite(Set<PartitaEntity> partite) {
        this.partite = partite;
    }

    public void addPartita(PartitaEntity pe){
        if(pe!=null){
            this.partite.add(pe);
        }
        else{
            throw new NoSuchElementException();
        }
    }

    public void incKOutente1(){
        this.koUtente1++;
    }

    public void incKOutente2(){
        this.koUtente2++;
    }
}
