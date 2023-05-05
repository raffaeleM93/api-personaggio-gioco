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
import java.util.NoSuchElementException;

@Service
public class PersonaggioService {

    @Autowired
    private PersonaggioRepository personaggioRepository;

    public Personaggio savePersonaggio(Personaggio p) {
        Personaggio p1 = new Personaggio(p.getNome(), p.getDescrizione(), p.getBase_atk(), p.getBase_def(), p.getInc_atk(), p.getInc_def());
        PersonaggioEntity pe = new PersonaggioEntity(p1.getNome(), p1.getDescrizione(), p1.getBase_atk(), p1.getBase_def(), p1.getInc_atk(), p1.getInc_def());
        personaggioRepository.save(pe);
        return p;
    }

    @Transactional
    public Personaggio updatePersonaggio(Personaggio p, String nome){
        if(personaggioRepository.existsById(nome)){
            PersonaggioEntity pe = personaggioRepository.getReferenceById(nome);
            Personaggio p1 = new Personaggio(pe.getNome(), pe.getDescrizione(), pe.getBase_atk(), pe.getBase_def(), pe.getInc_atk(), pe.getInc_def());
            p1.comparePersonaggio(p);
            pe.setNome(p.getNome());
            pe.setDescrizione(p.getDescrizione());
            pe.setBase_atk(p.getBase_atk());
            pe.setBase_def(p.getBase_def());
            pe.setInc_atk(p.getInc_atk());
            pe.setInc_def(p.getInc_def());
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

//    private boolean existByName(String nome) {
//        try{
//            PersonaggioEntity pe = personaggioRepository.find(nome);
//            if(pe == null){
//                return false;
//            }
//            else{
//                return true;
//            }
//        }
//        catch(EntityNotFoundException e){
//            return false;
//        }
//    }
}
