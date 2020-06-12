package ads;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface IAbstrTable<K,V> extends Iterable<V>{

    /**
     * Metoda zruší celou tabulku.
     */
    public void zrus();

    /**
     * Metoda zjistí, zda je tabulka prázdná.
     * 
     * @return
     */
    public boolean jePrazdny();

    /**
     * Metoda vyhledá prvek dle klíče.
     * 
     * @param key
     * @return
     */
    public V najdi(K key);

    /**
     * Metoda vloží prvek s klíčem do tabulky.
     * 
     * @param key
     * @param value
     */
    public void vloz(K key, V value);

    /**
     * Metoda odebere prvek dle klíčce z tabulky.
     * 
     * @param key
     * @return
     */
    public V odeber(K key);

    /**
     * Metoda vytvoří iterátor, který umožňuje procházení tabulky.
     * 
     * @return
     */
    @Override
    public Iterator<V> iterator();
    default Stream<V> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
