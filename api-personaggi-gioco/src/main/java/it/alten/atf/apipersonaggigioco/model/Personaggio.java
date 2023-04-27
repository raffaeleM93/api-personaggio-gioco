package it.alten.atf.apipersonaggigioco.model;

import java.util.Objects;

public class Personaggio {

    private String nome;
    private String descrizione;
    private int base_atk; // attacco base
    private int base_def; // difesa base
    private int inc_atk; // incremento attacco per livello
    private int inc_def; // incremento difesa per livello

    public Personaggio(String nome, String descrizione, int base_atk, int base_def, int inc_atk, int inc_def){
        setNome(nome);
        setDescrizione(descrizione);
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

    private String compareString(String strAggiornamento, String strDatabase){
        if(strAggiornamento == null){
            return strDatabase;
        }
        else{
            return strAggiornamento;
        }
    }

    private int compareInt(int intAggiornamento, int intDatabase){
        if(intAggiornamento != 0){
            return intDatabase;
        }
        else{
            return intAggiornamento;
        }
    }

    public void comparePersonaggio(Personaggio p){
        String descrizione = p.getDescrizione();
        int base_atk = p.getBase_atk();
        int base_def = p.getBase_def();
        int inc_atk = p.getInc_atk();
        int inc_def = p.getInc_def();

        setDescrizione(this.compareString(descrizione ,this.getDescrizione()));
        setBase_atk(this.compareInt(base_atk, this.getBase_atk()));
        setBase_def(this.compareInt(base_def, this.getBase_def()));
        setInc_atk(this.compareInt(inc_atk, this.getInc_atk()));
        setInc_def(this.compareInt(inc_def, this.getInc_def()));
    }
}
