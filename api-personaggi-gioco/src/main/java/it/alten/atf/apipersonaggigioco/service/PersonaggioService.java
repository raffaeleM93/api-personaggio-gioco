package it.alten.atf.apipersonaggigioco.service;

import it.alten.atf.apipersonaggigioco.model.Personaggio;
import it.alten.atf.apipersonaggigioco.model.PersonaggioEntity;
import it.alten.atf.apipersonaggigioco.repository.PersonaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PersonaggioService {

    @Autowired
    private PersonaggioRepository personaggioRepository;

    public Personaggio savePersonaggio(Personaggio p) {
        PersonaggioEntity pe = new PersonaggioEntity(p.getNome(), p.getDescrizione(), p.getBase_atk(), p.getBase_def(), p.getInc_atk(), p.getInc_def());
        personaggioRepository.save(pe);
        return p;
    }

    public Personaggio updatePersonaggio(Personaggio p, String nome){
        if(existByName(nome)){
            Personaggio p1 = getPersonaggioByName(nome);
            p1.comparePersonaggio(p);
            return p1;
        }
        else{
            savePersonaggio(p);
            return p;
        }

    }

    public List<Personaggio> getAllPersonaggi() {
        List<PersonaggioEntity> listPe = personaggioRepository.findAll();
        List<Personaggio> listP = new ArrayList<Personaggio>();
        for(var pe : listPe){
            Personaggio p = new Personaggio(pe.getNome(), pe.getDescrizione(), pe.getBase_atk(), pe.getBase_def(), pe.getInc_atk(), pe.getInc_def());
            listP.add(p);
        }
        return listP;
    }

    public Personaggio getPersonaggioByName(String nome) {
        PersonaggioEntity pe = personaggioRepository.getReferenceById(nome);
        return new Personaggio(pe.getNome(), pe.getDescrizione(), pe.getBase_atk(), pe.getBase_def(), pe.getInc_atk(), pe.getInc_def());
    }

    public void deletePersonaggioByName(String nome) {
        personaggioRepository.deleteById(nome);
    }

    private boolean existByName(String nome) {
        try{
            PersonaggioEntity pe = personaggioRepository.getReferenceById(nome);
            return true;
        }
        catch(EntityNotFoundException e){
            return false;
        }
    }
}
