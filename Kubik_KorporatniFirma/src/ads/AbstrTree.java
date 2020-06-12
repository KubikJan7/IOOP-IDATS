package ads;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AbstrTree<E> implements IAbstrTree<E>, Serializable {

    private int pocetUzlu;
    private Uzel koren;
    private Uzel aktualni;

    @Override
    public void zrus() {
        koren = null;
        aktualni = null;
        pocetUzlu = 0;
    }

    @Override
    public boolean jePrazdny() {
        return pocetUzlu == 0;
    }

    @Override
    public int mohutnost() {
        return pocetUzlu;
    }

    @Override
    public void vlozKoren(E data) {
        if (koren != null) {
            throw new UnsupportedOperationException("Kořen je již vytvořen.");
        }
        koren = new Uzel(null, null, data);
        pocetUzlu++;
    }

    @Override
    public void vlozList(E data) {
        if (aktualni == null) {
            throw new NullPointerException("Není nastaven aktuální uzel");
        }
        Uzel syn = new Uzel(aktualni, null, data);
        if (aktualni.synove == null) {
            aktualni.synove = new AbstrDoubleList<>();
        }
        aktualni.synove.vlozPosledni(syn);
        pocetUzlu++;
    }

    @Override
    public E odeberKoren() {
        if (pocetUzlu > 1) {
            throw new UnsupportedOperationException("Nelze odstranit kořen, "
                    + "jestliže obsahuje potomky.");
        }
        Uzel u = koren;
        zrus();
        return u.data;
    }

    @Override
    public E odeberList(int poradi) {
        E data = zpristupniSyna(poradi);
        if (aktualni.synove != null) {
            throw new UnsupportedOperationException("Nelze odstranit uzel, "
                    + "jestliže obsahuje potomky.");
        }
        zpristupniOtce();
        aktualni.synove.stream()
                .filter(t -> t.data == data)
                .forEach(t -> aktualni.synove.odeber(t));
        if (aktualni.synove.jePrazdny()) {
            aktualni.synove = null;
        }
        pocetUzlu = (int) stream().count();
        return data;
    }

    @Override
    public E zpristupniKoren() {
        if (jePrazdny()) {

        }
        aktualni = koren;
        return koren.data;
    }

    @Override
    public E zpristupniSyna(int poradi) {
        if (aktualni == null) {
            throw new NullPointerException("Není nastaven aktivní uzel.");
        } else if (aktualni.synove.jePrazdny()) {
            throw new NoSuchElementException("Aktivní uzel nemá potomky");
        } else if (poradi == 0 || poradi > aktualni.synove.getPocet()) {
            throw new NoSuchElementException("Bylo zadáno chybné pořadí");
        }

        Uzel u;
        Iterator<Uzel> itr = aktualni.synove.iterator();
        int i = 0;
        while (itr.hasNext()) {
            u = itr.next();
            i++;
            if (i == poradi) {
                aktualni = u;
                return u.data;
            }
        }
        throw new NullPointerException("Prvek s požadovaným pořadím neexistuje.");
    }

    @Override
    public E zpristupniOtce() {
        if (aktualni.otec == null) {
            throw new NullPointerException("Aktuální prvek nemá předka");
        }
        aktualni = aktualni.otec;
        return aktualni.data;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final IAbstrDoubleList<Uzel> zasobnik = new AbstrDoubleList<>();
            private Uzel uzel = koren;
            private E data;

            @Override
            public boolean hasNext() {
                if (uzel == koren) {
                    return true;
                }
                return !zasobnik.jePrazdny();
            }

            @Override
            public E next() {
                if (uzel == koren) {
                    zasobnik.vlozPosledni(uzel);
                }
                uzel = zasobnik.odeberPosledni();
                data = uzel.data;
                if (uzel.synove != null) {
                    uzel.synove.zpristupniPrvni();
                    Iterator<Uzel> itr = uzel.synove.iterator();
                    while (itr.hasNext()) {
                        try {
                            zasobnik.vlozPosledni(uzel.synove.zpristupniPredchudce());
                        } catch (NoSuchElementException | IAbstrDoubleList.ListException ex) {
                        }
                        itr.next();
                    }
                }
                uzel = null;
                if (!zasobnik.jePrazdny()) {
                    uzel = zasobnik.zpristupniPosledni();
                }
                return data;
            }
        };
    }

    public class Uzel implements Serializable {

        E data;
        Uzel otec;
        IAbstrDoubleList<Uzel> synove;

        public Uzel(Uzel otec, IAbstrDoubleList<Uzel> synove, E data) {
            this.data = data;
            this.otec = otec;
            this.synove = synove;
        }

    }
}
