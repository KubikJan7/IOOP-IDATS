package kolekce;

public class AbstrLifo<E> implements IAbstrLifo<E> {

    private IAbstrDoubleList zasobnik;
    private static AbstrLifo seznam = new AbstrLifo();
    
    public static AbstrLifo getInstance() {
        return seznam;
    }
    private AbstrLifo(){
        zasobnik = new AbstrDoubleList<>();
    }

    @Override
    public void zrus() {
       zasobnik.zrus(); ;
    }

    @Override
    public boolean jePrazdny() {
        return zasobnik.jePrazdny();
    }

    @Override
    public void vloz(E data) {
        zasobnik.vlozPosledni(data);
    }

    @Override
    public E odeber() {
        return (E) zasobnik.odeberPosledni();
    }

}
