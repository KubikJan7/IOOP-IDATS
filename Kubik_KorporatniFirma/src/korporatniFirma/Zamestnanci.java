package korporatniFirma;

import ads.AbstrTable;
import ads.IAbstrTable;
import java.util.Iterator;
import java.util.stream.Stream;

public class Zamestnanci implements IZamestnanci{
    private final IAbstrTable<Integer,Zamestnanec> tabulkaZamestnancu = new AbstrTable<>();

    @Override
    public Zamestnanec najdi(int id) {
        return tabulkaZamestnancu.najdi(id);
    }

    @Override
    public void vloz(int id, Zamestnanec zamestnanec) {
        tabulkaZamestnancu.vloz(id, zamestnanec);}

    @Override
    public Zamestnanec odeber(int id) {
        return tabulkaZamestnancu.odeber(id);
    }

    @Override
    public Stream<Zamestnanec> stream() {
        return tabulkaZamestnancu.stream();
    }

    @Override
    public Iterator iterator() {
        return tabulkaZamestnancu.iterator();
    }


}
