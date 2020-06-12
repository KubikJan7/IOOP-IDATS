package korporatniFirma;

import ads.AbstrTable;
import ads.IAbstrTable;
import java.util.Iterator;
import java.util.stream.Stream;

public class Firma implements IFirma {

    public String nazev;
    private int pocet;
    private final IAbstrTable<String, IPobocka> tabulkaPobocek = new AbstrTable<>();

    @Override
    public IPobocka najdi(String nazev) {
        return tabulkaPobocek.najdi(nazev);
    }

    @Override
    public void vloz(String nazev, IPobocka pobocka) {
        tabulkaPobocek.vloz(nazev, pobocka);
    }

    @Override
    public IPobocka odeber(String nazev) {
        return tabulkaPobocek.odeber(nazev);
    }
    @Override
    public Stream<IPobocka> stream() {
        return tabulkaPobocek.stream();
    }

    @Override
    public Iterator<IPobocka> iterator() {
        return tabulkaPobocek.iterator();
    }

    @Override
    public void zrus() {
        tabulkaPobocek.zrus();
    }
}
