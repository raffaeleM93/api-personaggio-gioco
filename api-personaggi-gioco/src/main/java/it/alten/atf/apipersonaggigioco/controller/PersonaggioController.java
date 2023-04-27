package it.alten.atf.apipersonaggigioco.controller;

import it.alten.atf.apipersonaggigioco.model.Personaggio;
import it.alten.atf.apipersonaggigioco.model.PersonaggioLevel;
import it.alten.atf.apipersonaggigioco.service.PersonaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/personaggio")
public class PersonaggioController {

    @Autowired
    PersonaggioService service;

    //CREATE
    @PostMapping("")
    public void creaPersonaggio(@RequestBody Personaggio p){
        service.savePersonaggio(p);
    }

    //READ
    @GetMapping("")
    public List<Personaggio> getPersonaggi(){
        return service.getAllPersonaggi();
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Personaggio> getPersonaggio(@PathVariable String nome){
        try{
            Personaggio p = service.getPersoggioByName(nome);
            return new ResponseEntity<Personaggio>(p, HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<Personaggio>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{nome}/{level}")
    public ResponseEntity<PersonaggioLevel> getPersonaggioLevel(@PathVariable String nome, int level){
        try{
            Personaggio p = service.getPersoggioByName(nome);
            PersonaggioLevel pl = new PersonaggioLevel(p, level);
            return new ResponseEntity<PersonaggioLevel>(pl, HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<PersonaggioLevel>(HttpStatus.NOT_FOUND);
        }
    }

    //UPDATE
    @PutMapping("/{nome}")
    public ResponseEntity<?> updatePersonaggio(@RequestBody Personaggio p, @PathVariable String nome){
        try{
            if(service.existByName(nome)){
                Personaggio p1 = service.getPersoggioByName(nome);
                p1.comparePersonaggio(p);
                service.savePersonaggio(p1);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                service.savePersonaggio(p);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //DELETE
    @DeleteMapping("/{nome}")
    public ResponseEntity<?> deletePersonaggio(@PathVariable String nome){
        try{
            service.deletePersonaggioByName(nome);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
