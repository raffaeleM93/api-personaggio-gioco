package it.alten.atf.apipersonaggigioco.repository;

import it.alten.atf.apipersonaggigioco.model.utente.UtenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<UtenteEntity, String> {

}
