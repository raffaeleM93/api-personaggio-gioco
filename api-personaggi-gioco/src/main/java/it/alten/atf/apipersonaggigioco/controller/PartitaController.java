package it.alten.atf.apipersonaggigioco.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.alten.atf.apipersonaggigioco.model.partita.GiocatoriPartita;
import it.alten.atf.apipersonaggigioco.model.partita.Partita;
import it.alten.atf.apipersonaggigioco.model.personaggio.Personaggio;
import it.alten.atf.apipersonaggigioco.service.PartitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/partita")
public class PartitaController {

    @Autowired
    PartitaService service;

    //CREATE
    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Partita creata!", response = Personaggio.class)
    }) // --> ?
    public ResponseEntity<UUID> creaPartita(@RequestBody GiocatoriPartita gp){
        UUID saved = service.creaPartita(gp);
        return ResponseEntity.created(null).body(saved);
    }

    //READ
    @GetMapping("/info/{uuid}")
    public ResponseEntity<List<Personaggio>> infoPartita(){
        var saved = service.getAllPersonaggi();
        return ResponseEntity.ok(saved);
    }


}
