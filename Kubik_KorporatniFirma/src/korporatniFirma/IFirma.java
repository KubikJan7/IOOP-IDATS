package korporatniFirma;

import java.util.stream.Stream;

public interface IFirma extends Iterable{

    /**
     * Metoda najde pobočku podle jejího jména.
     * 
     * @param nazev
     * @return
     */
    IPobocka najdi(String nazev);

    /**
     * Metoda vloží pobočku do seznamu poboček podle jména pobočky.
     * 
     * @param nazev
     * @param pobocka
     */
    void vloz(String nazev, IPobocka pobocka);

    /**
     * Metoda odebere pobočku ze seznamu.
     * @param nazev
     * @return
     */
    IPobocka odeber(String nazev);

    /**
     *
     * @return
     */
    Stream<IPobocka> stream();
    void zrus();
}
