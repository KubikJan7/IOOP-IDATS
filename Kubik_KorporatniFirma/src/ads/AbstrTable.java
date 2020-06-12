package ads;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AbstrTable<K, V> implements IAbstrTable<K, V> {

    private final IAbstrDoubleList<Prvek> table = new AbstrDoubleList<>();

    @Override
    public void zrus() {
        table.zrus();
    }

    @Override
    public boolean jePrazdny() {
        return table.jePrazdny();
    }

    @Override
    public V najdi(K key) {
        Prvek p;
        try {
            p = table.stream().filter(t -> t.key == key).findAny().get();
        } catch (NoSuchElementException e) {
            return null;
        }
        return p.value;

    }

    @Override
    public void vloz(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException("Prvek má nulový parametr");
        }
        if (najdi(key) == null) {
            table.vlozPosledni(new Prvek(key, value));
        }
    }

    @Override
    public V odeber(K key) {
        V v;
        v = najdi(key);
        if (v == null) {
            throw new NullPointerException("Zadaný klíč se nenachází v seznamu");
        } else {
        }
        Iterator itr = iterator();
        while (itr.hasNext()) {
            if (itr.next() == v) {
                itr.remove();
                return v;
            }
        }
        return null;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            Iterator<Prvek> itr = table.iterator();

            @Override
            public boolean hasNext() {
                return itr.hasNext();
            }

            @Override
            public V next() {
                if (hasNext()) {
                    V value = itr.next().value;
                    return value;
                }
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                itr.remove();
            }
        };
    }

    public class Prvek {

        K key;
        V value;

        public Prvek(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
