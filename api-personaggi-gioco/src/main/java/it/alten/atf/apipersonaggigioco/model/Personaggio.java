package it.alten.atf.apipersonaggigioco.model;

public class Personaggio {

    private String nome;
    private String descrizione;
    private int base_atk;
    private int base_def;
    private int inc_atk;
    private int inc_def;

    public Personaggio(String nome, String descrizione, int base_atk, int base_def, int inc_atk, int inc_def){
        this.nome = nome;
        this.descrizione = descrizione;
        setBase_atk(base_atk);
        setBase_def(base_def);
        setInc_atk(inc_atk);
        setInc_def(inc_def);
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

    public int getBase_atk() {
        return base_atk;
    }

    public void setBase_atk(int base_atk) {
        if(base_atk < 1){
            throw new IllegalArgumentException();
        }
        else{
            this.base_atk = base_atk;
        }
    }

    public int getBase_def() {
        return base_def;
    }

    public void setBase_def(int base_def) {
        if(base_def < 1){
            throw new IllegalArgumentException();
        }
        else{
            this.base_def = base_def;
        }
    }

    public int getInc_atk() {
        return inc_atk;
    }

    public void setInc_atk(int inc_atk) {
        if(inc_atk < 1){
            throw new IllegalArgumentException();
        }
        else{
            this.inc_atk = inc_atk;
        }
    }

    public int getInc_def() {
        return inc_def;
    }

    public void setInc_def(int inc_def) {
        if(inc_def < 1){
            throw new IllegalArgumentException();
        }
        else{
            this.inc_def = inc_def;
        }
    }

    public int attacco(int level){
        if(level < 1){
            throw new IllegalArgumentException();
        }
        return this.base_atk + (level-1) * this.inc_atk;
    }

    public int difesa(int level){
        if(level < 1){
            throw new IllegalArgumentException();
        }
        return this.base_def + (level-1) * this.inc_def;
    }
}
