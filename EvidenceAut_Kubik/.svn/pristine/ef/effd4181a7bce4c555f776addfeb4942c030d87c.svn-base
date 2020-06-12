package kolekce;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;

public class Seznam<E> implements ISeznam<E> {

    private final int velikost;
    private int pocet;
    private Prvek prvni;
    private Prvek posledni;
    private Prvek predchozi;
    private Prvek aktualni;

    public class Prvek {

        E data;
        Prvek dalsi;

        Prvek(Prvek dalsi, E data) {
            this.dalsi = dalsi;
            this.data = data;

        }

        Prvek(E data) {
            this(null, data);
        }
    }

    public Seznam(int velikost) throws KolekceException {
        if (velikost <= 0) {
            throw new KolekceException("");
        }
        this.velikost = velikost;
        zrus();
    }

    @Override
    public int getVelikost() {
        return velikost;
    }

    @Override
    public int getPocet() {
        return pocet;
    }

    @Override
    public boolean jePrazdny() {
        return pocet == 0;
    }

    @Override
    public boolean jePlny() {
        return pocet >= velikost;
    }

    @Override
    public void pridej(E data) throws KolekceException {
        Objects.requireNonNull(data);
        if (jePlny()) {
            throw new KolekceException("Dosažena maximální velikost");
        } else {
            pocet++;
            Prvek p = new Prvek(data);
            if (prvni == null) {
                prvni = p;
                posledni = prvni;
                return;
            }
            posledni.dalsi = p;
            posledni = p;
        }
    }

    @Override
    public E najdi(E klic) {
        aktualni = prvni;
        predchozi = null;
        while (aktualni != null) {
            if (aktualni.data.equals(klic)) {
                return aktualni.data;
            }
            predchozi = aktualni;
            aktualni = aktualni.dalsi;
        }
        return null;
    }

    @Override
    public E odeber(E klic) {
        Objects.requireNonNull(klic);
        if (prvni == null) {
            return null;
        }
        if (najdi(klic) == null) {
            return null;
        }
        E data = aktualni.data;
        pocet--;
        if (predchozi != null) {
            if (aktualni == posledni) {
                predchozi.dalsi = null;
                posledni = predchozi;
            } else {
                predchozi.dalsi = aktualni.dalsi;
            }
        } else {
            prvni = prvni.dalsi;
        }
        return data;
    }

    @Override
    public void nastavPrvni() throws KolekceException {
        if (prvni == null) {
            throw new KolekceException("Kolekce neobsahuje žádné prvky");
        }
        aktualni = prvni;
        predchozi = null;
    }

    @Override
    public void prejdiNaDalsi() throws KolekceException {
        if (prvni == null) {
            throw new KolekceException("Kolekce neobsahuje žádné prvky");
        }
        if (aktualni == null) {
            throw new KolekceException("Není nastaven aktuální prvek");
        }

        predchozi = aktualni;
        aktualni = predchozi.dalsi;
    }

    @Override
    public E zpristupni() throws KolekceException {
        if (prvni == null) {
            throw new KolekceException("Kolekce neobsahuje žádné prvky");
        }
        if (aktualni == null) {
            throw new KolekceException("Není nastaven aktuální prvek");
        }

        return aktualni.data;
    }

    @Override
    public boolean jeDalsi() throws KolekceException {
        if (prvni == null) {
            throw new KolekceException("Kolekce neobsahuje žádné prvky");
        }
        if (aktualni == null) {
            throw new KolekceException("Není nastaven aktuální prvek");
        }

        if (aktualni.dalsi != null) {
            return true;
        }
        return false;
    }

    @Override
    public void vlozZa(E data) throws KolekceException {
        if (prvni == null) {
            throw new KolekceException("Kolekce neobsahuje žádné prvky");
        }
        if (aktualni == null) {
            throw new KolekceException("Není nastaven aktuální prvek");
        }
        Prvek novy = aktualni.dalsi;
        aktualni.dalsi = new Prvek(novy, data);
        pocet++;
    }

    @Override
    public void vlozPred(E data) throws KolekceException {
        if (prvni == null) {
            throw new KolekceException("Kolekce neobsahuje žádné prvky");
        }
        if (aktualni == null) {
            throw new KolekceException("Není nastaven aktuální prvek");
        }
        Prvek novy = new Prvek(aktualni, data);
        predchozi.dalsi = novy;
        pocet++;
    }

    @Override
    public void zrus() {
        this.pocet = 0;
        this.prvni = null;
        this.posledni = null;
        this.predchozi = null;
        this.aktualni = null;
    }

    @Override
    public E[] toArray() {
        E[] pole = (E[]) new Object[pocet];
        Prvek p = prvni;
        for (int i = 0; i < pocet; i++) {
            if (p == null) {
                break;
            }
            pole[i] = p.data;
            p = p.dalsi;
        }
        return pole;
    }

    @Override
    public E[] toArray(E[] array) throws IllegalArgumentException {
        if (array.length < pocet) {
            throw new IllegalArgumentException("Pole nemá validní počet objektů");
        }
        Prvek p = prvni;
        for (int i = 0; i < array.length; i++) {
            if (p == null) {
                break;
            }
            array[i] = p.data;
            p = p.dalsi;
        }
        return array;
    }

    @Override
    public E[] toArray(Function<Integer, E[]> createFunction) {
        E[] vys = createFunction.apply(pocet);
        aktualni = prvni;
        for (E vy : vys) {
            if (aktualni == null) {
                break;
            }
            vy = aktualni.data;
            aktualni = aktualni.dalsi;
        }
        return vys;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Prvek index = prvni;

            @Override
            public boolean hasNext() {
                return index != null;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E data = index.data;
                    index = index.dalsi;
                    return data;
                }

                throw new NoSuchElementException();
            }
        };
    }
}
