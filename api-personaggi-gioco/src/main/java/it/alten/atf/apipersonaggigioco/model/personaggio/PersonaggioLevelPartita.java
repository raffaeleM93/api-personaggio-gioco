package it.alten.atf.apipersonaggigioco.model.personaggio;

public class PersonaggioLevelPartita extends PersonaggioLevel{

    private int level;
    private int countKO;

    public PersonaggioLevelPartita(Personaggio p, int level) {
        super(p, level);
        this.level = level;
        this.countKO = 0;
    }


}
