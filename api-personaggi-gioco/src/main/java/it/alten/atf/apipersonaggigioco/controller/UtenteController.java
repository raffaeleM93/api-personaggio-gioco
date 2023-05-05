package it.alten.atf.apipersonaggigioco.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.alten.atf.apipersonaggigioco.model.Personaggio;
import it.alten.atf.apipersonaggigioco.model.PersonaggioLevel;
import it.alten.atf.apipersonaggigioco.model.Utente;
import it.alten.atf.apipersonaggigioco.model.UtentePersonaggi;
import it.alten.atf.apipersonaggigioco.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    UtenteService service;

    //CREATE
    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Utente creato!", response = Utente.class)
    }) // --> ?
    public ResponseEntity<Utente> creaPersonaggio(@RequestBody Utente u){
        Utente saved = service.saveUtente(u);
        return ResponseEntity.created(null).body(saved);
    }

    //READ
    @GetMapping("")
    public ResponseEntity<List<UtentePersonaggi>> getUtenti(){
        var saved = service.getAllUtentePersonaggi();
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Utente> getUtente(@PathVariable String username){
        try{
            Utente p = service.getUtenteByUsername(username);
            return ResponseEntity.ok(p);
        }
        catch(EntityNotFoundException e){
            return new ResponseEntity<Utente>(HttpStatus.NOT_FOUND);
        }
    }

    //UPDATE
    @PutMapping("/{username}")
    public ResponseEntity<Utente> updateUtente(@RequestBody Utente u, @PathVariable String username){
        try{
            Utente utente = service.updateUtente(u, username);
            return ResponseEntity.ok(utente);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //DELETE
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
