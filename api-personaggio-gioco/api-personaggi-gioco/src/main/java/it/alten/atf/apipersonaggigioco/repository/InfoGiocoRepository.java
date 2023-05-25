package it.alten.atf.apipersonaggigioco.repository;
import it.alten.atf.apipersonaggigioco.model.partita.InfoGiocoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InfoGiocoRepository extends JpaRepository<InfoGiocoEntity, UUID> {
}
