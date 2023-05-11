package it.alten.atf.apipersonaggigioco.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.alten.atf.apipersonaggigioco.model.partita.GiocatoriPartita;
import it.alten.atf.apipersonaggigioco.model.partita.InfoGioco;
import it.alten.atf.apipersonaggigioco.model.partita.Partita;
import it.alten.atf.apipersonaggigioco.model.personaggio.Personaggio;
import it.alten.atf.apipersonaggigioco.model.personaggio.PersonaggioData;
import it.alten.atf.apipersonaggigioco.model.personaggio.PersonaggioLevel;
import it.alten.atf.apipersonaggigioco.service.PartitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/partita")
public class PartitaController {

    @Autowired
    PartitaService service;

    // CREATE [ok]
    // Crea una nuova Partita e ritorna l'uuid della partita creata
    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Partita creata!", response = Personaggio.class)
    }) // --> ?
    public ResponseEntity<UUID> creaPartita(@RequestBody GiocatoriPartita gp){
        UUID saved = service.creaPartita(gp);
        return ResponseEntity.created(null).body(saved);
    }

    // READ [ok]
    // Ritorna un oggetto Partita con le info della Partita
    @GetMapping("/info/{uuid}")
    public ResponseEntity<Partita> infoPartita(@PathVariable UUID uuid){
        try{
            var info = service.getInfoPartita(uuid);
            return ResponseEntity.ok(info);
        }
        catch(IllegalArgumentException e){
            return new ResponseEntity<Partita>(HttpStatus.BAD_REQUEST);
        }
    }

    // UPDATE

    // gioca partita
    @PutMapping("/gioca/{uuid}")
    public ResponseEntity<Partita> giocaPartita(@PathVariable UUID uuid){
        try{
            Partita p = service.giocaPartita(uuid);
            return ResponseEntity.ok(p);
        }
        catch(IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // termina gioco
    @PutMapping("/termina")
    public ResponseEntity<InfoGioco> terminaGioco(){
        try{
            InfoGioco info = service.terminaGioco();
            return ResponseEntity.ok(info);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // ...to-do...
    @PutMapping("/{uuid}/{username}/{level}")
    public ResponseEntity<PersonaggioLevel> giocaPartita(@PathVariable UUID uuid, @PathVariable String username, @PathVariable int level){
        return new ResponseEntity<PersonaggioLevel>(HttpStatus.OK);
    }
}
