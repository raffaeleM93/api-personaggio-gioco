package it.alten.atf.apipersonaggigioco.repository;

import it.alten.atf.apipersonaggigioco.model.partita.PartitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PartitaRepository extends JpaRepository<PartitaEntity, UUID> {

}
