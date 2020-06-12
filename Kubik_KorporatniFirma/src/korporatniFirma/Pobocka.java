package korporatniFirma;

import ads.AbstrTree;
import ads.IAbstrTree;
import java.io.Serializable;
import java.util.Iterator;
import java.util.stream.Stream;

public class Pobocka implements Serializable, IPobocka {

    public String nazev;
    public int pocetPozic;
    public String mesto;
    private final IAbstrTree<Pozice> stromPozic = new AbstrTree<>();

    public Pobocka(String nazev, String mesto) {
        this.nazev = nazev;
        this.mesto = mesto;
    }

    @Override
    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public int getPocetPozic() {
        return pocetPozic;
    }

    public void setPocetPozic(int pocetPozic) {
        this.pocetPozic = pocetPozic;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    @Override
    public Pozice zpristupniReditele() {
        return stromPozic.zpristupniKoren();
    }

    @Override
    public Pozice zpristupniPodrizenouPozici(int n) {
        return stromPozic.zpristupniSyna(n);
    }

    @Override
    public Pozice zpristupniNadrizenouPozici() {
        return stromPozic.zpristupniOtce();
    }

    @Override
    public void vlozPozici(Pozice pozice) {
        if (pozice.typ == TypPoziceEnum.REDITEL_POBOCKY) {
            stromPozic.vlozKoren(pozice);
        } else {
            stromPozic.vlozList(pozice);
        }

    }

    @Override
    public Pozice odeberPozici(int n) {
        return stromPozic.odeberList(n);
    }

    @Override
    public Iterator<Pozice> iterator() {
        return stromPozic.iterator();
    }

    @Override
    public Stream<Pozice> stream() {
        return stromPozic.stream();
    }

    @Override
    public String toString() {
        return "Pobočka: " + nazev + ", Počet pozic: " + pocetPozic + ", Město: " + mesto;
    }

}
