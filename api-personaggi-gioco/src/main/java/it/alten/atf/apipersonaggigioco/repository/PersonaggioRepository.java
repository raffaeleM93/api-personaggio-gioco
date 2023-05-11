package it.alten.atf.apipersonaggigioco.repository;

import it.alten.atf.apipersonaggigioco.model.personaggio.PersonaggioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaggioRepository extends JpaRepository<PersonaggioEntity, String> {

}
