package it.alten.atf.apipersonaggigioco.model;

public class PersonaggioLevel {

    private String nome;
    private String descrizione;
    private int level_atk;
    private int level_def;

    public PersonaggioLevel(Personaggio p, int level){
        setNome(p.getNome());
        setDescrizione(p.getDescrizione());
        this.level_atk = p.attacco(level);
        this.level_def = p.difesa(level);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getLevel_atk() {
        return level_atk;
    }

    public void setLevel_atk(int level_atk) {
        this.level_atk = level_atk;
    }

    public int getLevel_def() {
        return level_def;
    }

    public void setLevel_def(int level_def) {
        this.level_def = level_def;
    }
}
