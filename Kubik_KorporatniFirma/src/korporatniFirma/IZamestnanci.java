package korporatniFirma;

import java.util.stream.Stream;

public interface IZamestnanci extends Iterable {

    /**
     * Metoda nalezne zaměstnance podle jeho osobního čísla.
     *
     * @param id
     * @return
     */
    Zamestnanec najdi(int id);

    /**
     * Metoda vloží zaměstnance do seznamu zaměstnanců podle osobního čísla
     * zaměstnance.
     *
     * @param id
     * @param zamestnanec
     */
    void vloz(int id, Zamestnanec zamestnanec);

    /**
     * Metoda odebere zaměstnance ze seznamu.
     *
     * @param id
     * @return
     */
    Zamestnanec odeber(int id);

    /**
     *
     * @return
     */
    Stream<Zamestnanec> stream();
}
