package it.alten.atf.apipersonaggigioco.controller;

import it.alten.atf.apipersonaggigioco.model.Personaggio;
import it.alten.atf.apipersonaggigioco.model.PersonaggioLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/personaggio")
public class PersonaggioController {

    @PostMapping("/")
    public void creaPersonaggio(@RequestBody Personaggio p){

    }

    @GetMapping("/{nome}")
    public ResponseEntity<Personaggio> getPersonaggio(@PathVariable String nome){
        try{
            return new ResponseEntity<Personaggio>(HttpStatus.NOT_FOUND);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<Personaggio>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{nome}/{level}") // parametri multipli ?
    public ResponseEntity<PersonaggioLevel> getPersonaggioLevel(@PathVariable String nome, int level){
        try{
            // Personaggio p = service.getPersonaggio(nome);
            // PersonaggioLevel pl = new PersonaggioLevel(p, level);
            return new ResponseEntity<PersonaggioLevel>(HttpStatus.NOT_FOUND);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<PersonaggioLevel>(HttpStatus.NOT_FOUND);
        }
    }




}
