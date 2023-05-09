package it.alten.atf.apipersonaggigioco.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.alten.atf.apipersonaggigioco.model.Personaggio;
import it.alten.atf.apipersonaggigioco.model.PersonaggioData;
import it.alten.atf.apipersonaggigioco.model.PersonaggioLevel;
import it.alten.atf.apipersonaggigioco.service.PersonaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/personaggio")
public class PersonaggioController {

    @Autowired
    PersonaggioService service;

    //CREATE
    @PostMapping("")
    @ApiResponses(value = {
                  @ApiResponse(code = 201, message = "Personaggio creato!", response = Personaggio.class)
    }) // --> ?
    public ResponseEntity<Personaggio> creaPersonaggio(@RequestBody Personaggio p){
        Personaggio saved = service.savePersonaggio(p);
        return ResponseEntity.created(null).body(saved);
    }

    //READ
    @GetMapping("")
    public ResponseEntity<List<Personaggio>> getPersonaggi(){
        var saved = service.getAllPersonaggi();
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Personaggio> getPersonaggio(@PathVariable String nome){
        try{
            Personaggio p = service.getPersonaggioByName(nome);
            return ResponseEntity.ok(p);
        }
        catch(EntityNotFoundException e){
            return new ResponseEntity<Personaggio>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{nome}/{level}")
    public ResponseEntity<PersonaggioLevel> getPersonaggioLevel(@PathVariable String nome, @PathVariable int level){
        try{
            Personaggio p = service.getPersonaggioByName(nome);
            PersonaggioLevel pl = new PersonaggioLevel(p, level);
            return new ResponseEntity<PersonaggioLevel>(pl, HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<PersonaggioLevel>(HttpStatus.NOT_FOUND);
        }
    }

    //UPDATE
    @PutMapping("/{nome}")
    public ResponseEntity<Personaggio> updatePersonaggio(@RequestBody PersonaggioData pd, @PathVariable String nome){
        try{
            Personaggio p = new Personaggio(nome, pd.getDescrizione(), pd.getBase_atk(), pd.getBase_def(), pd.getInc_atk(), pd.getInc_def());
            Personaggio personaggio = service.updatePersonaggio(p, nome);
            return ResponseEntity.ok(personaggio);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //DELETE
    @DeleteMapping("/{nome}")
    public ResponseEntity<Personaggio> deletePersonaggio(@PathVariable String nome){
        try{
            service.deletePersonaggioByName(nome);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
