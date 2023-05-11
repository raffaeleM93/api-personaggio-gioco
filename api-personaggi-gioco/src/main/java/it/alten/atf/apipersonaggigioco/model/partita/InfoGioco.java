package it.alten.atf.apipersonaggigioco.model.partita;
import java.util.HashSet;
import java.util.Set;

public class InfoGioco {

    private int koUtente1;
    private int koUtente2;
    Set<Partita> partite;

    public InfoGioco(int koUtente1, int koUtente2, Set<Partita> partite) {
        this.koUtente1 = koUtente1;
        this.koUtente2 = koUtente2;
        this.partite = new HashSet<>();
        setPartite(partite);
    }

    public int getKoUtente1() {
        return koUtente1;
    }

    public void setKoUtente1(int koUtente1) {
        this.koUtente1 = koUtente1;
    }

    public int getKoUtente2() {
        return koUtente2;
    }

    public void setKoUtente2(int koUtente2) {
        this.koUtente2 = koUtente2;
    }

    public Set<Partita> getPartite() {
        return partite;
    }

    public void setPartite(Set<Partita> partite) {
        this.partite.addAll(partite);
    }
}
