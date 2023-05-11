package it.alten.atf.apipersonaggigioco.model.partita;

import it.alten.atf.apipersonaggigioco.model.personaggio.PersonaggioEntity;
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
    Set<UtenteEntity> utenti;
    Set<PersonaggioEntity> personaggi;

}
