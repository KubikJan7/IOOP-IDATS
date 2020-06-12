package automobily;

import java.util.stream.Stream;
import kolekce.ISeznam;
import kolekce.KolekceException;
import kolekce.Seznam;

public class SeznamVozidel {

    private static final int VELIKOST = 40;
    private static ISeznam seznam;
    private static SeznamVozidel seznamVozidel = new SeznamVozidel(VELIKOST);

    public static SeznamVozidel getInstance() {
        return seznamVozidel;
    }

    private SeznamVozidel(int velikost){
        try {
            seznam = new Seznam<>(velikost);
        } catch (KolekceException ex) {
        }
    }

    public void pridej(Vozidlo vozidlo) {
        try {
            seznam.pridej(vozidlo);
        } catch (KolekceException ex) {
        }
    }

    public void odeber(Vozidlo vozidlo) {
        seznam.odeber(vozidlo);
    }

    public Stream<Vozidlo> stream() {
        return seznam.stream();
    }

    public void zrus() {
        seznam.zrus();
    }

    public int getPocet() {
        return seznam.getPocet();
    }

    public int getVelikost() {
        return seznam.getVelikost();
    }

    public Object[] toArray() {
        return seznam.toArray();
    }
}
