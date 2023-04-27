package it.alten.atf.apipersonaggigioco.service;

import it.alten.atf.apipersonaggigioco.model.Personaggio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaggioService {


public void savePersonaggio(Personaggio p){

}

public List<Personaggio> getAllPersonaggi(){
    throw new UnsupportedOperationException();
}

public Personaggio getPersoggioByName(String nome){
    throw new UnsupportedOperationException();
}

public void deletePersonaggioByName(String nome){

}

public boolean existByName(String nome){
    throw new UnsupportedOperationException();
}

}
