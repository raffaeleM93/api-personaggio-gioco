package it.alten.atf.apipersonaggigioco.model;

public class PersonaggioLevel {

    private String nome;
    private String descrizione;
    private int level_atk;
    private int level_def;

    public PersonaggioLevel(Personaggio p, int level){
        setNome(p.getNome());
        setDescrizione(p.getDescrizione());
        this.level_atk = this.attacco(level, p.getBase_atk(), p.getInc_atk());
        this.level_def = this.difesa(level, p.getBase_def(), p.getInc_def());
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

    public int attacco(int level, int base_atk, int inc_atk){
        if(level < 1){
            throw new IllegalArgumentException();
        }
        return base_atk + (level-1) * inc_atk;
    }

    public int difesa(int level, int base_def, int inc_def){
        if(level < 1){
            throw new IllegalArgumentException();
        }
        return base_def + (level-1) * inc_def;
    }
}
