package kolekce;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstrDoubleList<E> implements IAbstrDoubleList<E> {

    private int pocet = 0;
    private Prvek prvni;
    private Prvek posledni;
    private Prvek aktualni;

    public class Prvek {

        E data;
        Prvek predchudce;
        Prvek naslednik;

        public Prvek(Prvek predchudce, Prvek naslednik, E data) {
            this.data = data;
            this.predchudce = predchudce;
            this.naslednik = naslednik;
        }

        public Prvek(E data) {
            this(null, null, data);
        }

    }

    public AbstrDoubleList() {
        zrus();
    }

    public int getPocet() {
        return pocet;
    }

    @Override
    public final void zrus() {
        this.pocet = 0;
        this.prvni = null;
        this.posledni = null;
        this.aktualni = null;
    }

    @Override
    public boolean jePrazdny() {
        return pocet == 0;
    }

    private void nastavJedinyPrvekVSeznamu(Prvek novy) {
        prvni = novy;
        posledni = prvni;
        posledni.naslednik = prvni;
        prvni.predchudce = posledni;
        posledni.predchudce = prvni;
        prvni.naslednik = posledni;
    }

    private void nastavPosledniPrvek(Prvek novy) {
        novy.naslednik = prvni;
        novy.predchudce = posledni;
        posledni.naslednik = novy;
        posledni = posledni.naslednik;
        posledni.naslednik = prvni;
        prvni.predchudce = posledni;
        if (pocet == 1) {
            prvni.naslednik = novy;

        }
    }

    private void nastavPrvniPrvek(Prvek novy) {
        novy.naslednik = prvni;
        novy.predchudce = posledni;
        prvni.predchudce = novy;
        prvni = prvni.predchudce;
        prvni.predchudce = posledni;
        posledni.naslednik = prvni;
        if (pocet == 1) {
            posledni.predchudce = novy;
        }
    }

    private E odstranPrvniPrvek() {
        Prvek p = prvni;
        prvni = p.naslednik;
        prvni.naslednik = p.naslednik.naslednik;
        prvni.predchudce = posledni;
        posledni.naslednik = prvni;
        return p.data;
    }

    private E odstranPosledniPrvek() {
        Prvek p = posledni;
        posledni = p.predchudce;
        posledni.predchudce = p.predchudce.predchudce;
        posledni.naslednik = prvni;
        prvni.predchudce = posledni;
        return p.data;
    }

    @Override
    public void vlozPrvni(E data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException("Prvek má nulový parametr");
        }
        Prvek novy = new Prvek(data);
        if (jePrazdny()) {
            nastavJedinyPrvekVSeznamu(novy);
        } else {
            nastavPrvniPrvek(novy);
        }
        pocet++;
    }

    @Override
    public void vlozPosledni(E data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException("Prvek má nulový parametr");
        }
        Prvek novy = new Prvek(data);
        if (jePrazdny()) {
            nastavJedinyPrvekVSeznamu(novy);
        } else {
            nastavPosledniPrvek(novy);
        }
        pocet++;
    }

    @Override
    public void vlozNaslednika(E data) throws NullPointerException, ListException {
        if (data == null) {
            throw new NullPointerException("Prvek má nulový parametr");
        }
        if (aktualni == null) {
            throw new ListException();
        }

        if (aktualni == posledni) {
            Prvek novy = new Prvek(data);
            nastavPosledniPrvek(novy);
            aktualni = posledni.predchudce;
            pocet++;
            return;
        }
        Prvek zbytek = aktualni.naslednik;
        aktualni.naslednik = new Prvek(aktualni, zbytek, data);
        zbytek.predchudce = aktualni.naslednik;
        Prvek p = prvni;
        pocet++;
        int index = 1;
        while (p != null) {
            if (index == pocet - 2 && p == aktualni) {
                posledni.predchudce = aktualni.naslednik;
                aktualni.naslednik = posledni.predchudce;
                if (pocet == 3) {
                    posledni.predchudce = prvni.naslednik;
                }
            }
            if (p == posledni) {
                break;
            }
            index++;
            p = p.naslednik;
        } 
    }

    @Override
    public void vlozPredchudce(E data) throws ListException, NoSuchElementException {
        if (data == null) {
            throw new NullPointerException("Prvek má nulový parametr");
        }
        if (aktualni == null) {
            throw new ListException();
        }
        if (aktualni == prvni) {
            Prvek novy = new Prvek(data);
            nastavPrvniPrvek(novy);
            aktualni = prvni.naslednik;
            pocet++;
            return;
        }
        Prvek novy = new Prvek(aktualni.predchudce, aktualni, data);
        Prvek p = prvni;
        pocet++;
        int index = 1;
        while (p != null) {
            if (index == 2 && p == aktualni) {
                prvni.naslednik = novy;
                if (pocet == 3) {
                    posledni.predchudce = prvni.naslednik;
                }
            }
            if (p == posledni) {
                break;
            }
            index++;
            p = p.naslednik;
        }
        aktualni.predchudce = novy;
        aktualni.predchudce.predchudce.naslednik = novy;
    }

    @Override
    public E zpristupniAktualni() throws NoSuchElementException, ListException {
        if (jePrazdny()) {
            throw new NoSuchElementException("V seznamu se nenachází žádný prvek");
        }
        if (aktualni == null) {
            throw new ListException();
        }
        return aktualni.data;
    }

    @Override
    public E zpristupniPrvni() throws NoSuchElementException {
        if (jePrazdny()) {
            throw new NoSuchElementException("V seznamu se nenachází žádný prvek");
        }
        aktualni = prvni;
        return aktualni.data;
    }

    @Override
    public E zpristupniPosledni() throws NoSuchElementException {
        if (jePrazdny()) {
            throw new NoSuchElementException("V seznamu se nenachází žádný prvek");
        }
        aktualni = posledni;
        return aktualni.data;
    }

    @Override
    public E zpristupniNaslednika() throws NoSuchElementException, ListException {
        if (aktualni == null) {
            throw new ListException();
        }
        if (aktualni.naslednik == null) {
            throw new NoSuchElementException("Následník aktuálního prvku neexistuje");
        }
        aktualni = aktualni.naslednik;
        return aktualni.data;
    }

    @Override
    public E zpristupniPredchudce() throws NoSuchElementException, ListException {
        if (aktualni == null) {
            throw new ListException();
        }
        if (aktualni.predchudce == null) {
            throw new NoSuchElementException("Předchůdce aktuálního prvku neexistuje");
        }
        aktualni = aktualni.predchudce;
        return aktualni.data;
    }

    @Override
    public E odeberAktualni() throws ListException, NoSuchElementException {
        if (jePrazdny()) {
            throw new NoSuchElementException("V seznamu se nenachází žádný prvek");
        }
        if (aktualni == null) {
            throw new ListException();
        }
        if (prvni.predchudce == prvni) {
            Prvek p = prvni;
            zrus();
            return p.data;
        }
        if (posledni == aktualni) {
            odstranPosledniPrvek();
        }
        if (prvni == aktualni) {
            odstranPrvniPrvek();
        }
        pocet--;
        Prvek p = aktualni;
        aktualni.predchudce.naslednik=aktualni.naslednik;
        aktualni = p.naslednik;
        aktualni.predchudce=p.predchudce;
        aktualni = prvni;
        return p.data;
    }

    @Override
    public E odeberPrvni() {
        if (jePrazdny()) {
            throw new NoSuchElementException("V seznamu se nenachází žádný prvek");
        }
        if (aktualni == prvni && prvni != null) {
            if (prvni.naslednik == prvni) {
                E p = prvni.data;
                zrus();
                return p;
            }
            aktualni = prvni.naslednik;
        }
        pocet--;
        return odstranPrvniPrvek();
    }

    @Override
    public E odeberPosledni() throws NoSuchElementException {
        if (jePrazdny()) {
            throw new NoSuchElementException("V seznamu se nenachází žádný prvek");
        }
        if (aktualni == posledni && posledni != null) {
            if (prvni.naslednik == prvni) {
                E p = posledni.data;
                zrus();
                return p;
            }
            aktualni = posledni.predchudce;
        }
        pocet--;
        return odstranPosledniPrvek();
    }

    @Override
    public E odeberNaslednika() throws ListException, NoSuchElementException {
        if (jePrazdny()) {
            throw new NoSuchElementException("V seznamu se nenachází žádný prvek.");
        }
        if (aktualni == null) {
            throw new ListException();
        }
        if (aktualni.naslednik == null) {
            throw new NoSuchElementException("Neexistuje následník aktuálního prvku.");
        }
        if (aktualni == posledni && posledni != null) {
            if (prvni.naslednik == prvni) {
                E p = aktualni.data;
                zrus();
                return p;
            }
        }
        pocet--;
        Prvek p = aktualni.naslednik;
        aktualni.naslednik = p.naslednik;
        if (posledni == aktualni) {
            odstranPrvniPrvek();
        }
        return p.data;
    }

    @Override
    public E odeberPredchudce() throws ListException, NoSuchElementException {
        if (jePrazdny()) {
            throw new NoSuchElementException("V seznamu se nenachází žádný prvek.");
        }
        if (aktualni == null) {
            throw new ListException();
        }
        if (aktualni.predchudce == null) {
            throw new NoSuchElementException("Neexistuje přechůdce aktuálního prvku.");
        }
        if (aktualni == posledni && posledni != null) {
            if (prvni.naslednik == prvni) {
                E p = aktualni.data;
                zrus();
                return p;
            }
        }
        pocet--;
        Prvek p = aktualni.predchudce;
        aktualni.predchudce = p.predchudce;
        if (prvni == aktualni) {
            odstranPosledniPrvek();
        }
        return p.data;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Prvek index = prvni;
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < getPocet();
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E data = index.data;
                    index = index.naslednik;
                    i++;
                    return data;
                }
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                try {
                    odeberAktualni();

                } catch (ListException | NoSuchElementException ex) {
                    Logger.getLogger(AbstrDoubleList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
    }

}
