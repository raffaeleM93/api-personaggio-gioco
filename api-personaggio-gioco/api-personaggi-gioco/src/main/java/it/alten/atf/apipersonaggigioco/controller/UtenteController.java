package it.alten.atf.apipersonaggigioco.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.alten.atf.apipersonaggigioco.model.personaggio.Personaggio;
import it.alten.atf.apipersonaggigioco.model.utente.Utente;
import it.alten.atf.apipersonaggigioco.model.utente.UtenteData;
import it.alten.atf.apipersonaggigioco.model.utente.UtentePersonaggi;
import it.alten.atf.apipersonaggigioco.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    UtenteService service;

    // CREATE
    // POST: Creazione di un utente [ok]
    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Utente creato!", response = Utente.class)
    }) // --> ?
    @PreAuthorize("hasAuthority('group_admins')")
    public ResponseEntity<Utente> creaUtente(@RequestBody Utente u, Authentication authentication){
        try{
            System.out.println("auth: " + authentication);
            Utente saved = service.saveUtente(u);
            return ResponseEntity.created(null).body(saved);
        }
        catch(EntityNotFoundException e){
            return new ResponseEntity<Utente>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ
    // GET: Ritorna tutti gli utenti con i personaggi associati [ok]
    @GetMapping("")
    public ResponseEntity<List<UtentePersonaggi>> getUtentiPersonaggi(){
        try{
            var saved = service.getAllUtentiPersonaggi();
            return ResponseEntity.ok(saved);
        }
        catch(EntityNotFoundException e){
            return new ResponseEntity<List<UtentePersonaggi>>(HttpStatus.NOT_FOUND);
        }
    }

    // GET: Ritorna Utente by username con personaggi associati [ok]
    @GetMapping("/{username}")
    public ResponseEntity<UtentePersonaggi> getUtentePersonaggi(@PathVariable String username){
        try{
            UtentePersonaggi p = service.getUtentePersonaggiByUsername(username);
            return ResponseEntity.ok(p);
        }
        catch(EntityNotFoundException e){
            return new ResponseEntity<UtentePersonaggi>(HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE
    // PUT: aggiornamento o creazione Utente
    @PutMapping("/{username}")
    public ResponseEntity<Utente> updateUtente(@RequestBody UtenteData ud, @PathVariable String username){
        try{
            Utente u = new Utente(username, ud.getNome(), ud.getRegistrazione());
            Utente utente = service.updateUtente(u, username);
            return ResponseEntity.ok(utente);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // PUT: Assegna un personaggio a un utente [ok]
    @PutMapping("/{username}/{nome}")
    public ResponseEntity<Personaggio> assignPersonaggio(@PathVariable String username, @PathVariable String nome){
        try{
            Personaggio personaggio = service.addPersonaggio(username, nome);
            return ResponseEntity.ok(personaggio);
        }
        catch(EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE: elimina un Utente
    @DeleteMapping("/{username}")
    public ResponseEntity<Utente> deleteUtente(@PathVariable String username){
        try{
            service.deleteUtenteByUsername(username);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
