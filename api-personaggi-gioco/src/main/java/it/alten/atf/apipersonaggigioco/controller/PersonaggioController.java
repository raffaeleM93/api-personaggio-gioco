package it.alten.atf.apipersonaggigioco.controller;

import it.alten.atf.apipersonaggigioco.model.Personaggio;
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

//    @GetMapping("/{nome}")
//    public ResponseEntity<Personaggio> getPersonaggio(@PathVariable String nome){
//        try{
//
//        }
//        catch(NoSuchElementException e){
//            return new ResponseEntity<Personaggio>(HttpStatus.NOT_FOUND);
//        }
//    }

}
