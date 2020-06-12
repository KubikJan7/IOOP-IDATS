package ads;

import java.io.Serializable;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface IAbstrTree<E> extends Iterable<E>,Serializable{

    /**
     * Metoda zruší celý strom.
     * 
     */
    public void zrus();

    /**
     * Metoda zjistí, zda je strom prázdný.
     * 
     * @return
     */
    public boolean jePrazdny();

    /**
     * Metoda dodá počet prvků stromu.
     * 
     * @return
     */
    public int mohutnost();

    /**
     * Metoda vloží inicializační (kořen) stromu.
     * 
     * @param data
     */
    public void vlozKoren(E data);

    /**
     * Metoda vloží další list stromu jako syna aktivního prvku.
     * 
     * @param data
     */
    public void vlozList(E data);

    /**
     * Metoda odebere kořen (pouze když obsahuje jen kořen).
     * @return
     */
    public E odeberKoren();

    /**
     * Metoda odebere list aktivního uzlu, který je dán pořadím.
     * 
     * @param poradi
     * @return
     */
    public E odeberList(int poradi);

    /**
     * Metoda zpřístupní kořen stromu.
     * 
     * @return
     */
    public E zpristupniKoren();

    /**
     * Metoda zpřístupní syna aktivního uzlu, který je dán pořadím.
     * 
     * @param poradi
     * @return
     */
    public E zpristupniSyna(int poradi);

    /**
     * Metoda zpřístupní otce aktivního uzlu.
     * 
     * @return
     */
    public E zpristupniOtce();

    /**
     * Metoda vytvoří iterátor procházení stromu do hloubky.
     * 
     * @return
     */
    @Override
    Iterator<E> iterator();
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
