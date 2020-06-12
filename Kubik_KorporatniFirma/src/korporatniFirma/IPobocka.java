package korporatniFirma;

import java.io.Serializable;
import java.util.stream.Stream;

public interface IPobocka extends Serializable, Iterable{

    /**
     * Metoda zpřístupní ředitele pobočky.
     * 
     * @return
     */
    Pozice zpristupniReditele();

    /**
     * Metoda zpřístupní n-tou podřízenou pozici aktuální (nadřazené) pozice.
     * ¨
     * @param n
     * @return
     */
    Pozice zpristupniPodrizenouPozici(int n);

    /**
     * Metoda zpřístupní nadřízenou pozici aktuální pozice.
     * 
     * @return
     */
    Pozice zpristupniNadrizenouPozici();

    /**
     * Metoda vloží novou pozici jako list k aktuální pozici.
     * @param pozice
     */
    void vlozPozici(Pozice pozice);

    /**
     * Metoda odebere n-tou pozici za předpokladu, že nemá podřízené.
     * 
     * @param n
     * @return
     */
    Pozice odeberPozici(int n);

    /**
     *
     * @return
     */
    Stream<Pozice> stream();

    /**
     *
     * @return 
     */
    String getNazev();
}
